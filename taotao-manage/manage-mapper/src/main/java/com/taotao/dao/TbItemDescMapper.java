package com.taotao.dao;


import com.taotao.pojo.TbItemDesc;

public interface TbItemDescMapper {

    public void insert(TbItemDesc itemDesc);

    public String findDescById(Long id);
}