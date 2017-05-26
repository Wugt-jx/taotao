package com.taotao.portal.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.TaoTaoResult;
import util.HttpUtil;

/**
 * Created by wgt on 2017/5/24.
 */
@Service
public class UserServiceImpl implements UserService {
    // @Value("${REST_BASE_URL}")
    @Value("${SSO_BASE_URL}")
    private String SSO_BASE_URL;

    @Value("${SSO_USER_TOKEN}")
    private String SSO_USER_TOKEN;

    /**
     * 调用sso服务，查询用户是否已经登录
     * @param token
     * @return
     */
    @Override
    public TbUser getUserByToken(String token) {
        try {
            String jsonResult = HttpUtil.doGet(SSO_BASE_URL+SSO_USER_TOKEN+token);
            TaoTaoResult<TbUser> taoResult = JSON.parseObject(jsonResult,new TypeReference<TaoTaoResult<TbUser>>(){});
            if (taoResult.getStatus()==200){
                TbUser user = taoResult.getData();
                return user;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
