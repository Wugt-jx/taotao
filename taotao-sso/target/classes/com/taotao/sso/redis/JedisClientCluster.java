package com.taotao.sso.redis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * Created by Administrator on 2017/5/17.
 */
public class JedisClientCluster implements JedisClient {

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key,value);
    }

    @Override
    public Long del(String key) {
        return jedisCluster.del(key);
    }

    @Override
    public String hget(String hkey, String key) {
        return jedisCluster.hget(hkey,key);
    }

    @Override
    public Long hset(String hkey, String key, String value) {
        return jedisCluster.hset(hkey,key,value);
    }

    @Override
    public Long hdel(String hkey, String key) {
        return jedisCluster.hdel(hkey,key);
    }

    @Override
    public Long incr(String key) {
        return jedisCluster.incr(key);
    }

    @Override
    public Long expire(String key, int overtime) {
        return jedisCluster.expire(key,overtime);
    }

    @Override
    public Long ttl(String key) {
        return jedisCluster.ttl(key);
    }
}
