package com.taotao.portal.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.TaoTaoResult;
import util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/18.
 */
@Service
public class ItemSearchServiceImpl implements ItemSearchService {


    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;


    @Override
    public SearchResult search(String queryString, int page) {

        // 调用taotao-search的服务
        //查询参数
        Map<String, Object> param = new HashMap<>();
        param.put("q", queryString);
        param.put("page", page + "");
        try {
            //调用服务
            String json = HttpUtil.doGet(SEARCH_BASE_URL, param);
            System.out.println(json);
            //把字符串转换成java对象
            TaoTaoResult taotaoResult = JSON.parseObject(json,new TypeReference<TaoTaoResult<SearchResult>>(){});
            if (taotaoResult.getStatus() == 200) {
                SearchResult result = (SearchResult) taotaoResult.getData();
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
