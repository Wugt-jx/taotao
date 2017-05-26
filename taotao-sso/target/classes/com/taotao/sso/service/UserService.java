package com.taotao.sso.service;

import com.taotao.pojo.TbUser;
import pojo.TaoTaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wgt on 2017/5/23.
 */
public interface UserService {
    public TaoTaoResult checkParams(String content,Integer type);

    public TaoTaoResult registerUser(TbUser user);

    public TaoTaoResult userLogin(String username, String password,
                                  HttpServletRequest request, HttpServletResponse response);

    public TaoTaoResult getUserByToken(String token);
}
