package com.taotao.service;

import com.taotao.pojo.TbItem;
import pojo.EasyUIDataGridResult;
import pojo.TaoTaoResult;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
public interface TbItemService {

    public TbItem findById(long id);

    public EasyUIDataGridResult<TbItem> selectByLimit(Integer page,Integer rows);

    public TaoTaoResult insert(TbItem item,String desc,String itemParam);

    public TaoTaoResult insert(TbItem item);

    public TaoTaoResult update(TbItem item,String desc);

    public TaoTaoResult update(TbItem item);
}
