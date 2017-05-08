package com.taotao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojo.EasyUIDataGridResult;

import java.util.List;

/**
 * Created by wgt on 2017/5/7.
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private ItemService itemService;


    @RequestMapping(value = "/{page}",method = RequestMethod.GET)
    public String index(@PathVariable String page){
        return page;
    }


    @RequestMapping(value = "/findById/{itemId}",method = RequestMethod.GET)
    @ResponseBody
    public String findById(@PathVariable Long itemId){
        TbItem item = itemService.findById(itemId);
        System.out.println(item.getTitle());
        return JSONObject.toJSONString(item);
    }

    @RequestMapping(value = "/select.action",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String selectByLimit(@RequestParam(defaultValue="1")Integer page,
                                @RequestParam(defaultValue="30")Integer rows
    ){
        EasyUIDataGridResult<TbItem> result= itemService.selectByLimit(page, rows);
        return JSON.toJSONString(result);
    }
}
