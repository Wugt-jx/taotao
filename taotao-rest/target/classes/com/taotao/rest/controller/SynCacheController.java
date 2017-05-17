package com.taotao.rest.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.rest.service.SynCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.TaoTaoResult;

/**
 * Created by Administrator on 2017/5/17.
 */
@Controller
@RequestMapping("/syn")
public class SynCacheController {

    @Autowired
    private SynCacheService synCacheService;

    @ResponseBody
    @RequestMapping(value = "/cache",produces = "application/json;charset=UTF-8")
    public String synCache(@RequestParam("hkey")String hkey,@RequestParam("key")String key){
        TaoTaoResult taoTaoResult = synCacheService.synCache(hkey,key);
        String result = JSON.toJSONString(taoTaoResult);
        return result;
    }


}
