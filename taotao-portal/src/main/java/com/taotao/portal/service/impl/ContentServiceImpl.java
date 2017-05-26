package com.taotao.portal.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.taotao.portal.service.ContentService;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.TaoTaoResult;
import util.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/16.
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_INDEX_AD_URL}")
    private String REST_INDEX_AD_URL;

    /**
     * 请求taotao-rest服务，查询广告内容
     * @return
     */
    @Override
    public String getContentList() {
        try {
            String httpResult = HttpUtil.doGet(REST_BASE_URL+REST_INDEX_AD_URL);
            TaoTaoResult taoTaoResult = JSON.parseObject(httpResult,new TypeReference<TaoTaoResult<List<Map<String,Object>>>>(){});
            if (taoTaoResult.getStatus()==200) {
                List<Map<String,Object>> contents = (List<Map<String, Object>>) taoTaoResult.getData();
                List<Map> resultList = new ArrayList<>();
                for (Map map :contents) {
                    Map resultMap = new HashMap<>();
                    resultMap.put("src", map.get("pic"));
                    resultMap.put("height", 240);
                    resultMap.put("width", 670);
                    resultMap.put("srcB", map.get("pic2"));
                    resultMap.put("widthB", 550);
                    resultMap.put("heightB", 240);
                    resultMap.put("href", map.get("url"));
                    resultMap.put("alt", map.get("subTitle"));
                    resultList.add(resultMap);
                }
                return JSON.toJSONString(resultList);
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (HttpException e) {
            throw new RuntimeException(e);
        }
    }
}
