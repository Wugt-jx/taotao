package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wgt on 2017/5/23.
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(){
       return "login";
    }

    @RequestMapping("/showRegister")
    public String showRegister(){
        return "register";
    }

    @RequestMapping("/showLogin")
    public String showLogin(String redirect,Model model){
        model.addAttribute("redirect",redirect);
        return "login";
    }
}
