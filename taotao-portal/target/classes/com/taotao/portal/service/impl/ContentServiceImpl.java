package com.taotao.portal.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.taotao.pojo.TbContent;
import com.taotao.portal.service.ContentService;
import exception.TaoException;
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


    @Override
    public String getContentList() {
        try {
            String httpResult = HttpUtil.doGet(REST_BASE_URL+REST_INDEX_AD_URL);
            TaoTaoResult taoTaoResult = JSON.parseObject(httpResult,new TypeReference<TaoTaoResult>(){});
            if (taoTaoResult.getStatus()==200) {
                List<Map> resultList = new ArrayList<>();
                List<Map<String,Object>> contents = (List<Map<String, Object>>) taoTaoResult.getData();
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
