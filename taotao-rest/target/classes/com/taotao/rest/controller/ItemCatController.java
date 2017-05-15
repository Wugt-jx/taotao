package com.taotao.rest.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.rest.service.ItemCatService;
import com.taotao.rest.util.MappingJsonUtil;
import com.taotao.rest.vo.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/5/15.
 */
@Controller
@RequestMapping("/itemcat")
public class ItemCatController {


    @Autowired
    private ItemCatService itemCatService;

    @ResponseBody
    @RequestMapping(value = "/list",produces = "application/json;charset=UTF-8")
    public String getItemCatList(String callback){
        CatResult catResult = itemCatService.getItemCatList();
        String result = MappingJsonUtil.setJsonpFunciton(JSON.toJSONString(catResult),callback);
        return result;
    }

}
