package com.taotao.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.service.TbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.EasyUITreeNodeResult;

import java.util.List;


@Controller
@RequestMapping("/item/cat")
public class TbItemCatController {

    @Autowired
    private TbItemCatService itemCatService;

    @RequestMapping(value = "/list",method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String selectByParentId(@RequestParam(value="id",defaultValue = "0") Long parentId){
        List<EasyUITreeNodeResult> results = itemCatService.selectByParentId(parentId);
        return JSON.toJSONString(results);
    }
}
