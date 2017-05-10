package com.taotao.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
import com.taotao.pojo.TbItem;
import com.taotao.service.TbItemService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojo.EasyUIDataGridResult;
import pojo.TaoTaoResult;

@RequestMapping("/item")
@Controller
public class TbItemController {

    @Autowired
    private TbItemService itemService;

    @RequestMapping(value = "/findById/{itemId}",method = RequestMethod.GET)
    @ResponseBody
    public String findById(@PathVariable Long itemId){
        TbItem item = itemService.findById(itemId);
        System.out.println(item.getTitle());
        return JSONObject.toJSONString(item);
    }


    @RequestMapping(value = "/select",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String selectByLimit(@RequestParam(defaultValue="1")Integer page,
                                @RequestParam(defaultValue="30")Integer rows
    ){
        EasyUIDataGridResult<TbItem> result= itemService.selectByLimit(page, rows);
        return JSON.toJSONString(result);
    }



    @ResponseBody
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveItem(TbItem item){
        TaoTaoResult taoTaoResult = itemService.insert(item);
        String result = JSON.toJSONString(taoTaoResult);
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/toUpdate",method = RequestMethod.GET)
    public TbItem toUpdatePage(@Param("id")Long id){
        System.out.println(id);
        return null;
    }
}
