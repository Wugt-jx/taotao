package com.taotao.sso.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.TaoTaoResult;
import util.MappingJsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wgt on 2017/5/23.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping(value = "/check/{content}/{type}")
    public String checkParams(@PathVariable String content, @PathVariable Integer type, String callback){
        TaoTaoResult taoResult = userService.checkParams(content,type);
        String result =JSON.toJSONString(taoResult);
        if (callback!=null&&"".equals(callback.trim())){
           result =  MappingJsonUtil.setJsonpFunciton(result,callback);
        }
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/register")
    public String register(TbUser user){
        TaoTaoResult taoResult = userService.registerUser(user);
        String result = JSON.toJSONString(taoResult);

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        HttpServletRequest request, HttpServletResponse response){
        return JSON.toJSONString(userService.userLogin(username,password,request,response));
    }



    @ResponseBody
    @RequestMapping(value = "/token/{token}")
    public String getUserByToken(@PathVariable String token, String callback){
        TaoTaoResult taoResult = userService.getUserByToken(token);

        if (StringUtils.isBlank(callback)){
            return JSON.toJSONString(taoResult);
        }else{
            return MappingJsonUtil.setJsonpFunciton(JSON.toJSONString(taoResult),callback);
        }

    }
}
