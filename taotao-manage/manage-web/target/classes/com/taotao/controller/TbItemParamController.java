package com.taotao.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.TbItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojo.EasyUIDataGridResult;
import pojo.TaoTaoResult;


@Controller
@RequestMapping("/item/param")
public class TbItemParamController {

    @Autowired
    private TbItemParamService itemParamService;

    @ResponseBody
    @RequestMapping("/query/itemcatid/{itemCatId}")
    public String queryByItemCatId(@PathVariable Long itemCatId){
        TaoTaoResult<TbItemParam> taoTaoResult=itemParamService.selectByItemCatId(itemCatId);
        String result = JSON.toJSONString(taoTaoResult);
        System.out.println(result);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public String queryList(@RequestParam(defaultValue="1")Integer page,
                            @RequestParam(defaultValue="30")Integer rows){
        EasyUIDataGridResult<TbItemParam> result = itemParamService.selectByLimit(page,rows);
        String jsonResult = JSON.toJSONString(result);
        System.out.println(jsonResult);
        return jsonResult;
    }
}
