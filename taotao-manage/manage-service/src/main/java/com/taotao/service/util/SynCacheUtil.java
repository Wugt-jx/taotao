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

/**
 * 更新调用同步缓存
 */
public class SynCacheUtil {

    private static Logger logger = LoggerFactory.getLogger(SynCacheUtil.class);

    public static void synCache(String hkey,String key) throws IOException, HttpException {
        if (key==null){throw new NullPointerException("lack of parameter !");}

        Map<String,Object> params = new HashMap();
        if (hkey!=null){
            params.put(RedisSynConstant.HKEY,hkey);
        }
        params.put(RedisSynConstant.KEY,key);

        String result = HttpUtil.doPost(RedisSynConstant.SYN_CACHE_URL,params);
        TaoTaoResult taoTaoResult = JSON.parseObject(result,TaoTaoResult.class);
        if (taoTaoResult.getStatus()==RedisSynConstant.FAIL_CODE) {
            logger.info("redis synchronization failed of cache ");
        }else if (taoTaoResult.getStatus()==RedisSynConstant.SUCCESS_CODE) {
            logger.info("redis synchronization success of cache ");
        }else{
            logger.error(" redis synchronization throw Exception of cache,Please contact administrator");
        }
    }
}
