package com.taotao.portal.controller;

import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order-cart")
    public String showOrderCart(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<CartItem> list = cartService.getCartItemList(request, response);
        //传递给页面
        model.addAttribute("cartList", list);

        return "order-cart";
    }


    @RequestMapping("/create")
    public String createOrder(HttpServletRequest request, HttpServletResponse response,Order order, Model model) {
        String result = orderService.createOrder(order);

        if (!StringUtils.isBlank(result)) {
            model.addAttribute("orderId", result);
            model.addAttribute("payment", order.getPayment());
            model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
            //成功后清空购物车
            cartService.cleanCartItemList(request,response);
            return "success";
        }else{
            return "/error/exception";
        }
    }

}
