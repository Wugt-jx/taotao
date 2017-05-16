package com.taotao.rest.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.TaoTaoResult;

/**
 * Created by Administrator on 2017/5/16.
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;


    @ResponseBody
    @RequestMapping(value = "/list/{contentId}",produces = "application/json;charset=UTF-8")
    public String getContentList(@PathVariable Long contentId){
        TaoTaoResult taoTaoResult = contentService.getContentList(contentId);
        return JSON.toJSONString(taoTaoResult);
    }


}
