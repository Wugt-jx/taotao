package com.taotao.portal.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wgt on 2017/5/22.
 */
@Controller
@RequestMapping("/item")
public class ItemInfoController {

    @Autowired
    private ItemInfoService itemInfoService;

    @RequestMapping(value = "/{itemId}",method = RequestMethod.GET)
    public String getBaseInfo(@PathVariable Long itemId, Model model){
        ItemInfo item =(ItemInfo) itemInfoService.getBaseInfo(itemId);
        System.out.println(JSON.toJSONString(item));
        if (item!=null){
            model.addAttribute("item",item);
        }
        return "item";
    }

    @ResponseBody
    @RequestMapping(value = "/desc/{itemId}",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String getDescInfo(@PathVariable Long itemId){
        System.out.println("im coming" );
        return itemInfoService.getDescInfo(itemId);
    }


    @ResponseBody
    @RequestMapping(value = "/param/{itemId}",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String getParamInfo(@PathVariable Long itemId){
        return itemInfoService.getParamInfo(itemId);
    }


}
