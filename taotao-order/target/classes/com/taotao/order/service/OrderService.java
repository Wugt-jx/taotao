package com.taotao.order.service;

import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
import pojo.TaoTaoResult;

import java.util.List;

/**
 * Created by wgt on 2017/5/25.
 */
public interface OrderService {
    public TaoTaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
}
