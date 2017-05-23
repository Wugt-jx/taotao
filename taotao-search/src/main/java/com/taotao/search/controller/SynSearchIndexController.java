package com.taotao.search.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.search.service.ItemService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.TaoTaoResult;

/**
 * Created by wgt on 2017/5/22.
 */
@Controller
@RequestMapping("/syn")
public class SynSearchIndexController {

    @Autowired
    private ItemService itemService;
    //http://localhost:8083/search/syn/solrIndex?itemId=149545439970677
    @ResponseBody
    @RequestMapping("/solrIndex")
    public String importOneSearchIndex(@RequestParam Long itemId){
        TaoTaoResult taoResult = null;
        try {
            taoResult = itemService.importOneItemIndex(itemId);
        }catch (Exception e){
            e.printStackTrace();
            taoResult=TaoTaoResult.build(50000, ExceptionUtils.getStackTrace(e));
        }
        return JSON.toJSONString(taoResult);
    }
}
