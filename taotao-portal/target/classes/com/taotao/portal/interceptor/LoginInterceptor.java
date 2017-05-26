package com.taotao.portal.interceptor;


import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import util.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wgt on 2017/5/24.
 */

/**
 * 登陆拦截器，查看用户是否已经登陆
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("http://localhost:8084/user")
    private String SSO_BASE_URL;

    @Value("/showLogin")
    private String SSO_PAGE_LOGIN;

    @Value("/token/")
    private String SSO_USER_TOKEN;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        logger.info("cookies:{}",token);


        TbUser user = userService.getUserByToken(token);
        if (null == user) {
            //跳转到登录页面，把用户请求的url作为参数传递给登录页面。
            response.sendRedirect(SSO_BASE_URL + SSO_PAGE_LOGIN + "?redirect=" + request.getRequestURL());
            //返回false
            return false;
        }
        request.setAttribute("user", user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}
