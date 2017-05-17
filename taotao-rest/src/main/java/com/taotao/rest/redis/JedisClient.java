package com.taotao.rest.redis;

/**
 * Created by Administrator on 2017/5/17.
 */
public interface JedisClient {
    //字符串
    public String get(String key);
    public String set(String key,String value);
    public Long del(String key);

    //hash
    public String hget(String hkey,String key);
    public Long  hset(String hkey,String key,String value);
    public Long hdel(String hkey,String key);

    //自增长
    public Long incr(String key);

    //设置key的过期时间
    public Long expire(String key,int overtime);
    public Long ttl(String key);
}
