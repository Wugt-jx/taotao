package com.taotao.dao;


import com.taotao.pojo.TbItem;

import java.util.List;

public interface TbItemMapper {


    public TbItem findById(long id);


    public List<TbItem> SelectByLimit();
}