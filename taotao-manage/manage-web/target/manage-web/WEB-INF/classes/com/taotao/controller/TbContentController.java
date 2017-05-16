package com.taotao.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.pojo.TbContent;
import com.taotao.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.EasyUIDataGridResult;
import pojo.TaoTaoResult;

/**
 * Created by wgt on 2017/5/15.
 */
@Controller
@RequestMapping(value = "/content")
public class TbContentController {

    @Autowired
    private TbContentService contentService;


    @ResponseBody
    @RequestMapping(value = "/query/list",produces = "application/json;charset=UTF-8")
    public String selectLimit(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue="30")Integer rows,
                              Long categoryId){
        EasyUIDataGridResult dataGridResult = contentService.selectList(page,rows,categoryId);
        String result = JSON.toJSONString(dataGridResult);
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/save",produces = "application/json;charset=UTF-8")
    public String save(TbContent content){
        TaoTaoResult taoTaoResult = contentService.save(content);
        String result =JSON.toJSONString(taoTaoResult);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/edit",produces = "application/json;charset=UTF-8")
    public String update(TbContent content){
        TaoTaoResult taoTaoResult = contentService.update(content);
        String result = JSON.toJSONString(taoTaoResult);
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/delete",produces = "application/json;charset=UTF-8")
    public String delete(Long[] ids){
        TaoTaoResult taoTaoResult =contentService.delete(ids);
        String result = JSON.toJSONString(taoTaoResult);
        return result;
    }
}
