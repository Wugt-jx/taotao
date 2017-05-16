package com.taotao.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传控制器
 */
@RequestMapping("/pic")
@Controller
public class PicController {

    @Autowired
    private PicService picService;

    @ResponseBody
    @RequestMapping(value = "/upload",produces = "text/plain;charset=UTF-8")
    public String fileUpload(MultipartFile uploadFile){
        System.out.println("coming");
        String result = JSON.toJSONString(picService.fileUpload(uploadFile));
        System.out.println(result);
        return result;
    }
}
