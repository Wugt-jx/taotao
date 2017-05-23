package com.taotao.rest.service.impl;

import com.alibaba.fastjson.JSON;
import com.taotao.dao.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.rest.redis.JedisClient;
import com.taotao.rest.service.ItemParamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.TaoTaoResult;

/**
 * Created by wgt on 2017/5/22.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService{

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Value("${REDIS_ITEM_KEY_PARAM}")
    private String REDIS_ITEM_KEY_PARAM;
    @Value("${REDIS_ITEM_EXPIRE}")
    private String REDIS_ITEM_EXPIRE;

    @Autowired
    private JedisClient client;


    @Override
    public TaoTaoResult getItemParamByItemId(Long itemId) {
        if (itemId==null){throw new NullPointerException("itemId is null !");}
        try{
            String result = client.get(REDIS_ITEM_KEY_PARAM+itemId.toString());
            if (!StringUtils.isBlank(result)) {
                return TaoTaoResult.ok(JSON.parseObject(result, TbItemParamItem.class));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TbItemParamItem paramItem = itemParamItemMapper.selectByItemId(itemId);
        try {
            client.set(REDIS_ITEM_KEY_PARAM+itemId.toString(),JSON.toJSONString(paramItem));
            client.expire(REDIS_ITEM_KEY_PARAM+itemId.toString(),Integer.parseInt(REDIS_ITEM_EXPIRE));
        }catch (Exception e){
            e.printStackTrace();
        }

        return TaoTaoResult.ok(paramItem);
    }
}
