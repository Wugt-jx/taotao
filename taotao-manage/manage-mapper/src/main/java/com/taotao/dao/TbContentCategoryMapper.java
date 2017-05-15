package com.taotao.dao;


import com.taotao.pojo.TbContentCategory;

import java.util.List;

public interface TbContentCategoryMapper {
    public List<TbContentCategory> selectByParentId(Long parentId);

    public Long insert(TbContentCategory contentCategory);

    public TbContentCategory selectById(Long id);

    public void setParent(TbContentCategory contentCategory);

    public void delete(Long parentId);

    public void update(TbContentCategory contentCategory);
}