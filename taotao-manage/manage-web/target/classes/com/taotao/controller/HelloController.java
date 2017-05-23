package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 后台管理界面之间的jsp页面跳转
 */
@Controller
public class HelloController {
    @RequestMapping(value = "/{page}",method = RequestMethod.GET)
    public String index(@PathVariable String page){
        return page;
    }
}
