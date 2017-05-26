package com.taotao.portal.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.TaoTaoResult;
import util.HttpUtil;

/**
 * Created by wgt on 2017/5/25.
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Value("${ORDER_BASE_URL}")
    private String ORDER_BASE_URL;
    @Value("${ORDER_CREATE_URL}")
    private String ORDER_CREATE_URL;


    @Override
    public String createOrder(Order order) {
        //调用taotao-order的服务提交订单。
        String json = HttpUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, JSON.toJSONString(order));
        //把json转换成taotaoResult
        TaoTaoResult taotaoResult = JSON.parseObject(json,new TypeReference<TaoTaoResult<Long>>(){});
        if (taotaoResult.getStatus() == 200) {
            Long orderId = (Long) taotaoResult.getData();
            return orderId.toString();
        }
        return "";

    }



}
