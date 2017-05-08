package com.taotao.dao;


import com.taotao.pojo.TbItemCat;
import java.util.List;

public interface TbItemCatMapper {
    public List<TbItemCat> selectByParentId(long parentId);
}