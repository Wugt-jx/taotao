package com.taotao.service.util;

import com.alibaba.fastjson.JSON;
import constant.SolrSybConstant;
import org.apache.http.HttpException;
import org.omg.CORBA.OBJ_ADAPTER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.TaoTaoResult;
import util.HttpUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wgt on 2017/5/22.
 */
public class SynSearchIndexUtil {

    private static Logger logger = LoggerFactory.getLogger(SynSearchIndexUtil.class);

    public static void synSearchIndex(Long itemId) throws URISyntaxException, IOException, HttpException {
        if (itemId==null){throw new NullPointerException("itemId is null!");}

        Map<String,Object>params = new HashMap();
        String result = HttpUtil.doGet(SolrSybConstant.SYN_SOLRINDEX_URL,params);
        TaoTaoResult taoTaoResult = JSON.parseObject(result,TaoTaoResult.class);
        if (taoTaoResult.getStatus()==SolrSybConstant.FAIL_CODE){
            logger.info("solrIndex synchronization failed of cache.");
        }else if (taoTaoResult.getStatus()==SolrSybConstant.SUCCESS_CODE) {
            logger.info("solrIndex synchronization success of cache");
        }else{
            logger.error("solrIndex synchronization throw exception of cache,please contact administrator");
        }
    }
}
