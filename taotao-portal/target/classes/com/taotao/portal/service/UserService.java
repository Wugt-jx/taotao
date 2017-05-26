package com.taotao.portal.service;

import com.taotao.pojo.TbUser;
import pojo.TaoTaoResult;

/**
 * Created by wgt on 2017/5/24.
 */
public interface UserService {
    public TbUser getUserByToken(String token);
}
