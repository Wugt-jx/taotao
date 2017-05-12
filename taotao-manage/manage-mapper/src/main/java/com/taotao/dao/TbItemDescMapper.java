package com.taotao.rest.dao;


import com.taotao.rest.pojo.TbItemDesc;

public interface TbItemDescMapper {

    public void insert(TbItemDesc itemDesc);

    public String findDescById(Long id);

    public void update(TbItemDesc itemDesc);

    public void delete(Long itemId);
}