package com.taotao.rest.service;

import com.taotao.rest.pojo.TbItem;
import com.taotao.rest.pojo.TbItemParamItem;
import pojo.EasyUIDataGridResult;
import pojo.TaoTaoResult;

public interface TbItemService {

    public TbItem findById(long id);

    public EasyUIDataGridResult<TbItem> selectByLimit(Integer page,Integer rows);

    public TaoTaoResult insert(TbItem item,String desc,String itemParam);

    public TaoTaoResult update(TbItem item, String desc, TbItemParamItem itemParams);

    public TaoTaoResult delete(Long[] ids);
}
