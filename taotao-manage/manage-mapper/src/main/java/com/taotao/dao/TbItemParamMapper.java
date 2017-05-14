package com.taotao.dao;


import com.taotao.pojo.TbItemParam;

import java.util.List;

public interface TbItemParamMapper {
    public TbItemParam selectByItemCatId(long item_cat_id);

    public List<TbItemParam> selectList();

    public void insert(TbItemParam itemParam);

   // public void update(TbItemParam itemParam);

    public void delete(Long id);
}