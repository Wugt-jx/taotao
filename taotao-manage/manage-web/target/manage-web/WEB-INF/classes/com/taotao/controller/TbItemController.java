package com.taotao.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojo.EasyUIDataGridResult;
import pojo.TaoTaoResult;

/**
 * 商品展示
 */
@RequestMapping("/item")
@Controller
public class TbItemController {

    @Autowired
    private TbItemService itemService;

    @Autowired
    private TbItemParamItem itemParamItem;

    /**
     * 根据id查询商品详情信息
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/findById/{itemId}",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String findById(@PathVariable Long itemId){
        TbItem item = itemService.findById(itemId);
        System.out.println(item.getTitle());
        return JSONObject.toJSONString(item);
    }

    /**
     * 获取商品列表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/select",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String selectByLimit(@RequestParam(defaultValue="1")Integer page,
                                @RequestParam(defaultValue="30")Integer rows
    ){
        EasyUIDataGridResult<TbItem> result= itemService.selectByLimit(page, rows);
        return JSON.toJSONString(result);
    }


    /**
     * 创建商品
     * @param item
     * @param desc
     * @param itemParams
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveItem(TbItem item,String desc,String itemParams){
        TaoTaoResult taoTaoResult = itemService.insert(item,desc,itemParams);

        String result = JSON.toJSONString(taoTaoResult);
        return result;
    }

    /**
     * 更新商品
     * @param item
     * @param desc
     * @param itemParams
     * @param itemParamId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(TbItem item, String desc, String itemParams,Long itemParamId){
        itemParamItem.setId(itemParamId);
        itemParamItem.setParamData(itemParams);
        TaoTaoResult taoTaoResult = itemService.update(item,desc,itemParamItem);
        String result = JSON.toJSONString(taoTaoResult);
        System.out.println(result);
        return result;
    }

    /**
     * 删除商品
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    public String delete(Long[] ids){
        TaoTaoResult taoTaoResult = itemService.delete(ids);
        String result = JSON.toJSONString(taoTaoResult);
        return result;
    }
}
