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

/**
 * 后台管理员对广告内容的操作
 */
@Controller
@RequestMapping(value = "/content")
public class TbContentController {

    @Autowired
    private TbContentService contentService;

    /**
     * 获取指定广告类型下所有的广告内容记录
     * @param page 从第几页开始获取，默认为第一页
     * @param rows 每页获取的广告条数，默认为30
     * @param categoryId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query/list",produces = "application/json;charset=UTF-8")
    public String selectLimit(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue="30")Integer rows,
                              Long categoryId){
        EasyUIDataGridResult dataGridResult = contentService.selectList(page,rows,categoryId);
        String result = JSON.toJSONString(dataGridResult);
        return result;
    }

    /**
     * 创建广告
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save",produces = "application/json;charset=UTF-8")
    public String save(TbContent content){
        TaoTaoResult taoTaoResult = contentService.save(content);
        String result =JSON.toJSONString(taoTaoResult);
        return result;
    }

    /**
     * 更新广告
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/edit",produces = "application/json;charset=UTF-8")
    public String update(TbContent content){
        TaoTaoResult taoTaoResult = contentService.update(content);
        String result = JSON.toJSONString(taoTaoResult);
        return result;
    }

    /**
     * 删除广告，批量删除
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete",produces = "application/json;charset=UTF-8")
    public String delete(Long[] ids){
        TaoTaoResult taoTaoResult =contentService.delete(ids);
        String result = JSON.toJSONString(taoTaoResult);
        return result;
    }
}
