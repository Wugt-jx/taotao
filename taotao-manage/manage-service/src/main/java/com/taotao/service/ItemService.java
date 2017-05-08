package com.taotao.service;

import com.taotao.pojo.TbItem;
import pojo.EasyUIDataGridResult;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
public interface ItemService {
    public TbItem findById(long id);
    public EasyUIDataGridResult<TbItem> selectByLimit(Integer page,Integer rows);
}
