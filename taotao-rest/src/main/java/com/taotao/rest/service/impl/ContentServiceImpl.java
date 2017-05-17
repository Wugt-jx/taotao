package com.taotao.rest.service.impl;

import com.alibaba.fastjson.JSON;
import com.taotao.dao.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.rest.redis.JedisClient;
import com.taotao.rest.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.TaoTaoResult;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */
@Service
public class ContentServiceImpl implements ContentService{

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("INDEX_CONTENT_REDIS_KEY")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    @Transactional
    public String getContentList(Long contentCid) {
        if (contentCid==null){return JSON.toJSONString(TaoTaoResult.build(4000,"lack of parameter!"));}
        String result = null;
        try {
            result =jedisClient.hget(INDEX_CONTENT_REDIS_KEY,contentCid+"");
            if (!StringUtils.isBlank(result)){
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        List<TbContent> contents = contentMapper.selectLimit(contentCid);
        result =JSON.toJSONString(TaoTaoResult.ok(contents));

        try {
            jedisClient.hset(INDEX_CONTENT_REDIS_KEY,contentCid+"",result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
