package com.taotao.portal.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.TaoTaoResult;
import util.CookieUtils;
import util.HttpUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wgt on 2017/5/25.
 */
@Service
public class CartServiceImpl implements CartService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_ITEM_INFO_URL}")
    private String REST_ITEM_INFO_URL;

    @Override
    public TaoTaoResult addCartItem(Long itemId, Integer num,
                                    HttpServletRequest request, HttpServletResponse response) {
        if (itemId==null||num==null){
            throw new NullPointerException("lack of parameter !");
        }
        CartItem cartItem = null;
        List<CartItem> itemList = getCartItemList(request);

        for (CartItem cItem : itemList) {
            //如果存在此商品
            if (cItem.getId() == itemId) {
                //增加商品数量
                cItem.setNum(cItem.getNum() + num);
                cartItem = cItem;
                break;
            }
        }

        if (cartItem == null) {
            cartItem = new CartItem();
            //根据商品id查询商品基本信息。
            String json = null;
            try {
                json = HttpUtil.doGet(REST_BASE_URL + REST_ITEM_INFO_URL + itemId);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (HttpException e) {
                e.printStackTrace();
            }
            //把json转换成java对象
            TaoTaoResult<TbItem> taotaoResult = JSON.parseObject(json,new TypeReference<TaoTaoResult<TbItem>>(){});
            if (taotaoResult.getStatus() == 200) {
                TbItem item = (TbItem) taotaoResult.getData();
                cartItem.setId(item.getId());
                cartItem.setTitle(item.getTitle());
                cartItem.setImage(item.getImage() == null ? "":item.getImage().split(",")[0]);
                cartItem.setNum(num);
                cartItem.setPrice(item.getPrice());
            }
            //添加到购物车列表
            itemList.add(cartItem);
        }
        //把购物车列表写入cookie
        CookieUtils.setCookie(request, response, "TT_CART", JSON.toJSONString(itemList), true);

        return TaoTaoResult.ok();
    }

    @Override
    public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> itemList = getCartItemList(request);
        return itemList;
    }


    @Override
    public TaoTaoResult updateNum(Long itemId,Integer num,HttpServletRequest request,HttpServletResponse response){
        if (itemId==null||num==null){
            throw new NullPointerException("lack of parameter !");
        }

        List<CartItem> itemList = getCartItemList(request);
        for (CartItem cItem : itemList) {
            //如果存在此商品
            if (cItem.getId() == itemId) {
                //增加商品数量
                cItem.setNum(num);
                break;
            }
        }
        CookieUtils.setCookie(request,response,"TT_CART",JSON.toJSONString(itemList));
        return TaoTaoResult.ok();
    }

    @Override
    public TaoTaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> itemList = getCartItemList(request);
        //从列表中找到此商品
        for (CartItem cartItem : itemList) {
            if (cartItem.getId() == itemId) {
                itemList.remove(cartItem);
                break;
            }
        }
        //把购物车列表重新写入cookie
        CookieUtils.setCookie(request, response, "TT_CART", JSON.toJSONString(itemList), true);

        return TaoTaoResult.ok();

    }

    public void cleanCartItemList(HttpServletRequest request,HttpServletResponse response){
        CookieUtils.setCookie(request,response,"TT_CART","");
    }


    private List<CartItem> getCartItemList(HttpServletRequest request) {
        //从cookie中取商品列表
        String cartJson = CookieUtils.getCookieValue(request, "TT_CART", true);
        if (cartJson == null) {
            return new ArrayList<>();
        }
        //把json转换成商品列表
        try {
            List<CartItem> list = JSON.parseObject(cartJson,new TypeReference<List<CartItem>>(){});
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
