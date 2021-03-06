package com.taotao.dao;


import com.taotao.pojo.TbItemParamItem;

public interface TbItemParamItemMapper {
    public void insert(TbItemParamItem itemParams);

    public void update(TbItemParamItem itemParams);

    public TbItemParamItem selectByItemId(Long itemId);

    public void delete(Long itemId);
}