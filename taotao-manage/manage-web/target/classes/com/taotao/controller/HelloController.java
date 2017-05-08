package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
    @RequestMapping(value = "/{page}",method = RequestMethod.GET)
    public String index(@PathVariable String page){
        return page;
    }
}
