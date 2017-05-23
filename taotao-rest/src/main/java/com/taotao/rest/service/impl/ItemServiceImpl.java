package com.taotao.rest.service.impl;

import com.alibaba.fastjson.JSON;
import com.taotao.dao.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.rest.redis.JedisClient;
import com.taotao.rest.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.TaoTaoResult;

/**
 * Created by wgt on 2017/5/21.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private JedisClient client;

    @Value("${REDIS_ITEM_KEY_BASE}")
    private String REDIS_ITEM_KEY_BASE;

    @Value("${REDIS_ITEM_EXPIRE}")
    private String REDIS_ITEM_EXPIRE;

    /** 查询商品基本信息
     * 缓存键格式：REDIS_ITEM_KEY:商品id:base=json
     * @param itemId
     * @return
     */
    @Override
    public TaoTaoResult selectById(Long itemId) {
        if (itemId==null){
            throw new NullPointerException("itemId is null !");
        }
        try {
            String result = client.get(REDIS_ITEM_KEY_BASE+itemId.toString());
            if (!StringUtils.isBlank(result)){
                return TaoTaoResult.ok(JSON.parseObject(result,TbItem.class));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        TbItem item = itemMapper.findById(itemId);
        try {
            client.set(REDIS_ITEM_KEY_BASE+itemId.toString(),JSON.toJSONString(item));
            client.expire(REDIS_ITEM_KEY_BASE+itemId.toString(),Integer.parseInt(REDIS_ITEM_EXPIRE));
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaoTaoResult.ok(item);
    }
}
