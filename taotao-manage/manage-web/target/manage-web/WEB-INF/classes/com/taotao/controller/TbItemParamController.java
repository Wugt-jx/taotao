package com.taotao.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.TbItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "/query/itemcatid/{itemCatId}",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public String queryByItemCatId(@PathVariable Long itemCatId){
        TaoTaoResult<TbItemParam> taoTaoResult=itemParamService.selectByItemCatId(itemCatId);
        String result = JSON.toJSONString(taoTaoResult);
        logger.info("backdata{}",result);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public String queryList(@RequestParam(defaultValue="1")Integer page,
                            @RequestParam(defaultValue="30")Integer rows){
        EasyUIDataGridResult<TbItemParam> result = itemParamService.selectByLimit(page,rows);
        String jsonResult = JSON.toJSONString(result);
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping(value = "/save/{cid}",method = RequestMethod.POST)
    public String save(@PathVariable Long cid,String paramData){
        TaoTaoResult taoTaoResult = itemParamService.insert(cid,paramData);
        String result = JSON.toJSONString(taoTaoResult);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String delete(@RequestParam("ids") Long[] ids){
        TaoTaoResult taoTaoResult = itemParamService.delete(ids);
        String result = JSON.toJSONString(taoTaoResult);
        return result;
    }
}
