package com.taotao.rest.controller;

import com.taotao.rest.service.ItemCatService;
import util.MappingJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/5/15.
 */

/**
 * 商品分类
 */
@Controller
@RequestMapping("/itemcat")
public class ItemCatController {


    @Autowired
    private ItemCatService itemCatService;

    /**
     * 查询商品分类列表
     * @param callback
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list",produces = "application/json;charset=UTF-8")
    public String getItemCatList(String callback){
        String catResult = itemCatService.getItemCatList();
        String result = MappingJsonUtil.setJsonpFunciton(catResult,callback);
        System.gc();
        return result;
    }

}
