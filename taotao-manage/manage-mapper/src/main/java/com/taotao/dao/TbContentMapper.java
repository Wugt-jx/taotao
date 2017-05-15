package com.taotao.dao;


import com.taotao.pojo.TbContent;

import java.util.List;

public interface TbContentMapper {

    public List<TbContent> selectLimit(Long categoryId);

    public void insert(TbContent content);

    public void update(TbContent content);

    public void delete(Long id);
}