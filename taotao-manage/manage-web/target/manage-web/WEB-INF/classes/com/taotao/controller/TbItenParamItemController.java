package com.taotao.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.service.TbItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.TaoTaoResult;

/**
 * 商品规格参数查询
 */
@Controller
@RequestMapping("/item/param/item")
public class TbItenParamItemController {

    @Autowired
    private TbItemParamItemService itemParamItemService;


    @ResponseBody
    @RequestMapping(value = "/query/{itemId}",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8" )
    public String queryByItemId(@PathVariable Long itemId){
        TaoTaoResult taoTaoResult = itemParamItemService.selectByItemId(itemId);
        String result = JSON.toJSONString(taoTaoResult);
        return result;
    }

}
