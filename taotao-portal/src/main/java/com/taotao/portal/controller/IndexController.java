package com.taotao.portal.controller;


import com.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 门户首页加载请求
 */
@Controller
public class IndexController {

    @Autowired
    private ContentService contentService;

    /**
     * 加载广告内容
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    public String welcome(Model model){
        String ad1 = contentService.getContentList();
        model.addAttribute("ad1",ad1);
        return "index";
    }

}
