package com.taotao.dao;


import com.taotao.pojo.TbUser;

public interface TbUserMapper {
    public TbUser selectByUsername(String username);
    public TbUser selectByPhone(String phone);
    public TbUser selectByEmail(String password);

    public void saveUser(TbUser user);

}