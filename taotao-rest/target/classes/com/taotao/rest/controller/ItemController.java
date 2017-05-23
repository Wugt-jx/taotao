package com.taotao.rest.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.rest.service.ItemDescService;
import com.taotao.rest.service.ItemParamService;
import com.taotao.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wgt on 2017/5/21.
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemDescService itemDescService;
    @Autowired
    private ItemParamService itemParamService;
    /**
     * 获取商品基本信息
     * @param itemId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/info/{itemId}",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String getItemInfo(@PathVariable Long itemId){
        String result = JSON.toJSONString(itemService.selectById(itemId));
        return result;
    }

    /**
     * 获取商品描述信息
     * @param itemId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/desc/{itemId}",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String getItemDesc(@PathVariable Long itemId){
        String result =JSON.toJSONString(itemDescService.selectByItemId(itemId));
        return result;
    }

    /**
     * 获取商品规格参数
     * @param itemId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/param/{itemId}",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public String getItemParam(@PathVariable Long itemId){
        String result = JSON.toJSONString(itemParamService.getItemParamByItemId(itemId));
        return result;
    }
}
