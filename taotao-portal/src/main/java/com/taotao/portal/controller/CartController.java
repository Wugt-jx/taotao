package com.taotao.portal.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.TaoTaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wgt on 2017/5/25.
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @RequestMapping("/add/{itemId}")
    public String addCartItem(@PathVariable Long itemId, @RequestParam(defaultValue = "1")Integer num,
                              HttpServletRequest request, HttpServletResponse response){
        TaoTaoResult result = cartService.addCartItem(itemId,num,request,response);
        return "cartSuccess";
    }



    @RequestMapping("/cart")
    public String showCart(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<CartItem> list = cartService.getCartItemList(request, response);
        System.out.println(JSON.toJSONString(list));
        model.addAttribute("cartList", list);
        return "cart";
    }

    @ResponseBody
    @RequestMapping("/update/num/{itemId}/{num}")
    public String updateNum(@PathVariable Long itemId,@PathVariable Integer num,
                            HttpServletRequest request,HttpServletResponse response){
        TaoTaoResult taoResult = cartService.updateNum(itemId,num,request,response);
        return JSON.toJSONString(taoResult);
    }


    @RequestMapping("/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
        cartService.deleteCartItem(itemId, request, response);
        return "redirect:/cart/cart.html";
    }

}
