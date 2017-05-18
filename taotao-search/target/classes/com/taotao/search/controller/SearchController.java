package com.taotao.search.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.ItemSearchService;
import com.taotao.search.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.TaoTaoResult;

/**
 * Created by Administrator on 2017/5/18.
 */
@Controller
public class SearchController {

    @Autowired
    private ItemSearchService searchService;

    @RequestMapping(value="/query", method= RequestMethod.GET)
    @ResponseBody
    public String search(@RequestParam("q")String queryString,
                               @RequestParam(defaultValue="1")Integer page,
                               @RequestParam(defaultValue="60")Integer rows) {
        //查询条件不能为空
        if (StringUtils.isBlank(queryString)) {
            return JSON.toJSONString(TaoTaoResult.build(400, "查询条件不能为空"));
        }
        SearchResult searchResult = null;
        try {
            queryString = new String(queryString.getBytes("iso8859-1"),"utf-8");

            searchResult = searchService.search(queryString, page, rows);
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(TaoTaoResult.build(500, ExceptionUtils.getStackTrace(e)));
        }

        return JSON.toJSONString(TaoTaoResult.ok(searchResult));

    }
}
