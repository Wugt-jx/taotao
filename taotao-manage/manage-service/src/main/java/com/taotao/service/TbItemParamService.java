package com.taotao.service;

import com.taotao.pojo.TbItemParam;
import pojo.EasyUIDataGridResult;
import pojo.TaoTaoResult;

/**
 * Created by Administrator on 2017/5/11.
 */
public interface TbItemParamService {

    public TaoTaoResult<TbItemParam> selectByItemCatId(Long item_cat_id);

    public EasyUIDataGridResult<TbItemParam> selectByLimit(Integer page, Integer rows);
}
