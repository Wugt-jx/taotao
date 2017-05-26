package com.taotao.rest.controller;

import com.taotao.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/5/16.
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 查询广告内容
     * @param categoryId 广告分类Id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list/{categoryId}",produces = "application/json;charset=UTF-8")
    public String getContentList(@PathVariable Long categoryId){

        String result = contentService.getContentList(categoryId);

        return result;
    }


}
