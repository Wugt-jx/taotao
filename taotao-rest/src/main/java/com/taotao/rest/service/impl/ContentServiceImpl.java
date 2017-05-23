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

    //查询缓存的键
    @Value("INDEX_CONTENT_REDIS_KEY")
    private String INDEX_CONTENT_REDIS_KEY;


    /**
     * 获取广告内容
     * 想查询缓存中是否有，如果没有则查数据库，并添加到缓存中
     * @param categoryId 广告分类id
     * @return
     */
    @Override
    @Transactional
    public String getContentList(Long categoryId) {
        if (categoryId==null){return JSON.toJSONString(TaoTaoResult.build(4000,"lack of parameter!"));}
        String result = null;
        try {
            result =jedisClient.hget(INDEX_CONTENT_REDIS_KEY,categoryId+"");
            if (!StringUtils.isBlank(result)){
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        List<TbContent> contents = contentMapper.selectLimit(categoryId);
        result =JSON.toJSONString(TaoTaoResult.ok(contents));

        try {
            jedisClient.hset(INDEX_CONTENT_REDIS_KEY,categoryId+"",result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
