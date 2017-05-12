package com.taotao.rest.dao;


import com.taotao.rest.pojo.TbItem;

import java.util.List;

public interface TbItemMapper {


    public TbItem findById(long id);


    public List<TbItem> SelectByLimit();

    public void insert(TbItem item);

    public void update(TbItem item);

    public void delete(Long id);
}