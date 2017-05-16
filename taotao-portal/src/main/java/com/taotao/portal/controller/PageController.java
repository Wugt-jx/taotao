package com.taotao.portal.controller;


import com.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "/index")
    public String welcome(Model model){
        String ad1 = contentService.getContentList();
        model.addAttribute("ad1",ad1);
        return "index";
    }

}
