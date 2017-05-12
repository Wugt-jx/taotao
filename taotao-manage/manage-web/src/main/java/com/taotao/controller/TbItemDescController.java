package com.taotao.rest.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.rest.service.TbItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.TaoTaoResult;

/**
 * Created by Administrator on 2017/5/10.
 */
@Controller
@RequestMapping("/itemDesc")
public class TbItemDescController {

    @Autowired
    private TbItemDescService itemDescService;

    @ResponseBody
    @RequestMapping(value = "/getDesc/{id}",produces = "text/plain;charset=UTF-8")
    public String getDesc(@PathVariable Long id){
        TaoTaoResult taoTaoResult = TaoTaoResult.ok(itemDescService.findDescById(id));
        String result = JSON.toJSONString(taoTaoResult);
        System.out.println(result);
        return result;
    }
}
