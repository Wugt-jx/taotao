package com.taotao.search.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.search.service.ItemService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.TaoTaoResult;

/**
 * Created by Administrator on 2017/5/18.
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
      private ItemService itemService;

    @ResponseBody
    @RequestMapping("/importall")
    public String importItemAll(){
        TaoTaoResult taoResult =null;
        try {
           taoResult = itemService.importItemToIndex();
        }catch (Exception e){
           e.printStackTrace();
           taoResult = TaoTaoResult.build(4000, ExceptionUtils.getStackTrace(e));
        }
        return JSON.toJSONString(taoResult);
    }

}
