package com.taotao.service.util;

import com.alibaba.fastjson.JSON;
import constant.RedisSynConstant;
import org.apache.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.TaoTaoResult;
import util.HttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/17.
 */
public class SynCacheUtil {

    private static Logger logger = LoggerFactory.getLogger(SynCacheUtil.class);

    public static TaoTaoResult synCache(String hkey,String key) throws IOException, HttpException {
        if (hkey==null||key==null){throw new NullPointerException("lack of parameter !");}

        Map<String,Object> params = new HashMap();
        params.put(RedisSynConstant.HKEY,hkey);
        params.put(RedisSynConstant.KEY,key);

        String result = HttpUtil.doPost(RedisSynConstant.SYN_CACHE_URL,params);
        TaoTaoResult taoTaoResult = JSON.parseObject(result,TaoTaoResult.class);
        logger.info("redis synchronization successfully of cache ");
        return taoTaoResult;
    }
}
