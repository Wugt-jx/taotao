package com.taotao.order.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.order.vo.Order;
import com.taotao.order.service.OrderService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.TaoTaoResult;

/**
 * Created by wgt on 2017/5/25.
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;


    @ResponseBody
    @RequestMapping("/create")
    public String createOrder(@RequestBody Order order) {
        try {
            TaoTaoResult result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());

            return JSON.toJSONString(result);
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(TaoTaoResult.build(500, ExceptionUtils.getStackTrace(e)));
        }
    }

}
