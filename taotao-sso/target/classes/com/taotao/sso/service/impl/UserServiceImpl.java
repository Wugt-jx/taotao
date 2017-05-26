package com.taotao.sso.service.impl;

import com.alibaba.fastjson.JSON;
import com.taotao.dao.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.sso.redis.JedisClient;
import com.taotao.sso.service.UserService;
import com.taotao.sso.util.CheckConstant;
import com.taotao.sso.util.ResponseConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import pojo.TaoTaoResult;
import util.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.UUID;

/**
 * Created by wgt on 2017/5/23.
 */
@Service
public class UserServiceImpl implements UserService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JedisClient client;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbUserMapper userMapper;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;

    @Value("${SSO_SESSION_EXPIRE}")
    private String SSO_SESSION_EXPIRE;

    @Value("${LOGIN_COOKIES}")
    private String LOGIN_COOKIES;


    /**
     * 检查用户名，手机号，邮箱是否已经被注册
     * @param content
     * @param type
     * @return
     */
    @Override
    public TaoTaoResult checkParams(String content,Integer type) {
        if (content==null||type==null){
            return TaoTaoResult.build(ResponseConstant.FAIL_CODE,"检验内容不能为空");
        }
        if (type== CheckConstant.TYPE_USERNAME){
            if (userMapper.selectByUsername(content)!=null){
                return TaoTaoResult.ok(CheckConstant.EXIST);
            }
            return TaoTaoResult.ok(CheckConstant.NONE);
        }
        if (type==CheckConstant.TYPE_PHONE){
            if (userMapper.selectByPhone(content)!=null){
                return TaoTaoResult.ok(CheckConstant.EXIST);
            }
            return TaoTaoResult.ok(CheckConstant.NONE);
        }
        if (type==CheckConstant.TYPE_EMAIL){
            if (userMapper.selectByEmail(content)!=null){
                return TaoTaoResult.ok(CheckConstant.EXIST);
            }
            return TaoTaoResult.ok(CheckConstant.NONE);
        }
        return TaoTaoResult.ok(CheckConstant.EXIST);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public TaoTaoResult registerUser(TbUser user) {
        Date date = new Date();
        user.setCreated(date);
        user.setUpdated(date);
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.saveUser(user);
        logger.info("user register success:{}",JSON.toJSONString(user));
        return TaoTaoResult.ok();
    }

    /**
     * 用户登陆
     * @param username 用户名
     * @param password  密码
     * @param request
     * @param response
     * @return
     */
    @Override
    public TaoTaoResult userLogin(String username, String password,
                                  HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            return TaoTaoResult.build(ResponseConstant.FAIL_CODE,"lack of parameter !");
        }
        TbUser user = userMapper.selectByUsername(username);
        if (user==null){
            return TaoTaoResult.build(ResponseConstant.FAIL_CODE,"该用户不存在！");
        }
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
            return TaoTaoResult.build(ResponseConstant.FAIL_CODE,"密码不正确");
        }
        logger.info("user login success:{} ",JSON.toJSONString(user));
        //使用uuid生成token
        String token = UUID.randomUUID().toString();
        try {
            client.set(REDIS_USER_SESSION_KEY+token, JSON.toJSONString(user));
            client.expire(REDIS_USER_SESSION_KEY+token,Integer.parseInt(SSO_SESSION_EXPIRE));
            CookieUtils.setCookie(request,response,LOGIN_COOKIES,token);
        }catch (Exception e){
            e.printStackTrace();
            return TaoTaoResult.build(ResponseConstant.ERROR_CODE, ExceptionUtils.getStackTrace(e));
        }
        return TaoTaoResult.ok(token);
    }

    /**
     * 单点登录，使用token查询缓存里是否已经登录。
     * @param token
     * @return
     */
    @Override
    public TaoTaoResult getUserByToken(String token) {
        token = REDIS_USER_SESSION_KEY+token;

        try {
            String result = client.get(token);
            if (StringUtils.isBlank(result)){
                return TaoTaoResult.build(ResponseConstant.FAIL_CODE,"session已经过期，请重新登陆");
            }
            client.expire(token,Integer.parseInt(SSO_SESSION_EXPIRE));
            return TaoTaoResult.ok(JSON.parseObject(result,TbUser.class));
        }catch (Exception e){
            e.printStackTrace();
            return TaoTaoResult.build(ResponseConstant.ERROR_CODE,ExceptionUtils.getStackTrace(e));
        }
    }
}
