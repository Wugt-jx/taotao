package com.taotao.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.EasyUITreeNodeResult;
import pojo.TaoTaoResult;

import java.util.List;

/**
 * Created by Administrator on 2017/5/15.
 */

/**
 * 管理员对广告内容类型模块操作
 *
 * 广告类型模型以节点形式
 * 大类型广告下有小类型广告类型，数据库中 is_parent 字段标注该类型是否为大类型广告
 */
@Controller
@RequestMapping("/content/category")
public class TbContentCategoryController {

    @Autowired
    private TbContentCategoryService contentCategoryService;

    /**
     * 根据广告类型ID
     * 查询该ID下细分的广告类型列表
     * @param parentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list",produces = "application/json;charset=UTF-8")
    public String getContentCategoryList(@RequestParam(value = "id",defaultValue = "0") Long parentId ){
        List<EasyUITreeNodeResult> results = contentCategoryService.getContentCategoryList(parentId);
        return JSON.toJSONString(results);
    }

    /**
     * 创建广告类型
     * @param parentId
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/create",produces = "application/json;charset=UTF-8")
    public String createContentCategory(@RequestParam("parentId") Long parentId,@RequestParam("name") String name){
        TaoTaoResult taoTaoResult = contentCategoryService.save(parentId,name);
        return JSON.toJSONString(taoTaoResult);
    }

    /**
     * 删除广告类型，删除时如果父类型下没有子节点类型，那么就转变成小广告类型
     * @param parentId
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String deleteContentCategory(Long parentId,Long id){
        TaoTaoResult taoTaoResult = contentCategoryService.delete(parentId,id);
        return JSON.toJSONString(taoTaoResult);
    }

    /**
     * 广告类型重命名
     * @param id
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/rename",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String rename(Long id,String name){
        TaoTaoResult taoTaoResult = contentCategoryService.rename(id,name);
        return JSON.toJSONString(taoTaoResult);
    }
}
