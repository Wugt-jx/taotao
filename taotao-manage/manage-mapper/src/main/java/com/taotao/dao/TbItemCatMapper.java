package com.taotao.rest.dao;


import com.taotao.rest.pojo.TbItemCat;

import java.util.List;

public interface TbItemCatMapper {
    public List<TbItemCat> selectByParentId(long parentId);
}