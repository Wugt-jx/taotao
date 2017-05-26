package com.taotao.portal.service;

import com.taotao.portal.pojo.CartItem;
import pojo.TaoTaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wgt on 2017/5/25.
 */
public interface CartService {
    public TaoTaoResult addCartItem(Long itemId, Integer num,
                                    HttpServletRequest request, HttpServletResponse response);


    public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);

    public TaoTaoResult updateNum(Long itemId,Integer num,HttpServletRequest request,HttpServletResponse response);

    public TaoTaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);

    public void cleanCartItemList(HttpServletRequest request,HttpServletResponse response);
}
