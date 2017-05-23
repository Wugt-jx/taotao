package com.taotao.rest.service.impl;

import com.alibaba.fastjson.JSON;
import com.taotao.dao.TbItemDescMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.rest.redis.JedisClient;
import com.taotao.rest.service.ItemDescService;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.TaoTaoResult;

/**
 * Created by wgt on 2017/5/22.
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {


    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private JedisClient client;


    @Value("${REDIS_ITEM_KEY_DESC}")
    private String REDIS_ITEM_KEY_DESC;

    @Value("${REDIS_ITEM_EXPIRE}")
    private String REDIS_ITEM_EXPIRE;


    @Override
    public TaoTaoResult selectByItemId(Long itemId) {
        try {
            String result = client.get(REDIS_ITEM_KEY_DESC+itemId.toString());
            if (!StringUtils.isBlank(result)){
                return TaoTaoResult.ok(result);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        String itemDesc = itemDescMapper.findDescById(itemId);
        try {
            client.set(REDIS_ITEM_KEY_DESC+itemId.toString(),itemDesc);
            client.expire(REDIS_ITEM_KEY_DESC+itemId.toString(),Integer.parseInt(REDIS_ITEM_EXPIRE));
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaoTaoResult.ok(itemDesc);
    }
}
