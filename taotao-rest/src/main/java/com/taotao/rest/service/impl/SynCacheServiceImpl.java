package com.taotao.rest.service.impl;

import com.taotao.rest.redis.JedisClient;
import com.taotao.rest.service.SynCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.TaoTaoResult;

/**
 * Created by Administrator on 2017/5/17.
 */
@Service
public class SynCacheServiceImpl implements SynCacheService {

    @Autowired
    private JedisClient jedisClient;

    @Override
    public TaoTaoResult synCache(String hkey, String key) {
        if (key==null){
            return TaoTaoResult.build(40000,"lack of parameter !");
        }
        if (hkey!=null){
            try {
                jedisClient.hdel(hkey,key);
                return TaoTaoResult.ok();
            }catch (Exception e){
                e.printStackTrace();
                return TaoTaoResult.build(40000,"Redis synchronization fail of cache ! ");
            }
        }
        try {
            jedisClient.del(key);
        }catch (Exception e){
            e.printStackTrace();
            return TaoTaoResult.build(40000,"Redis synchronization fail of cache !");
        }

        return TaoTaoResult.ok();
    }
}
