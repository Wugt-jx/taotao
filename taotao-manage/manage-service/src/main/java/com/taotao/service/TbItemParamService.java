package com.taotao.service;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;
import pojo.EasyUIDataGridResult;
import pojo.TaoTaoResult;

/**
 * Created by Administrator on 2017/5/11.
 */
public interface TbItemParamService {

    public TaoTaoResult<TbItemParam> selectByItemCatId(Long item_cat_id);

    public EasyUIDataGridResult<TbItemParam> selectByLimit(Integer page, Integer rows);

    public TaoTaoResult insert(Long itemCatId,String paramData);

    //public TaoTaoResult update(TbItemParam itemParam);

    public TaoTaoResult delete(Long[] ids);
}
