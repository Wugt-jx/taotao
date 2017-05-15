package com.taotao.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.pojo.TbContentCategory;
import com.taotao.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.EasyUITreeNodeResult;
import pojo.TaoTaoResult;

import java.util.List;

/**
 * Created by Administrator on 2017/5/15.
 */
@Controller
@RequestMapping("/content/category")
public class TbContentCategoryController {

    @Autowired
    private TbContentCategoryService contentCategoryService;


    @ResponseBody
    @RequestMapping(value = "/list",produces = "application/json;charset=UTF-8")
    public String getContentCategoryList(@RequestParam(value = "id",defaultValue = "0") Long parentId ){
        List<EasyUITreeNodeResult> results = contentCategoryService.getContentCategoryList(parentId);
        return JSON.toJSONString(results);
    }

    @ResponseBody
    @RequestMapping(value = "/create",produces = "application/json;charset=UTF-8")
    public String createContentCategory(@RequestParam("parentId") Long parentId,@RequestParam("name") String name){
        TaoTaoResult taoTaoResult = contentCategoryService.save(parentId,name);
        return JSON.toJSONString(taoTaoResult);
    }

    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String deleteContentCategory(Long parentId,Long id){
        TaoTaoResult taoTaoResult = contentCategoryService.delete(parentId,id);
        return JSON.toJSONString(taoTaoResult);
    }
}
