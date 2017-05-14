package com.taotao.service;

import com.taotao.pojo.TbItemParamItem;
import pojo.TaoTaoResult;

/**
 * Created by Administrator on 2017/5/12.
 */
public interface TbItemParamItemService {
    public TaoTaoResult<TbItemParamItem> selectByItemId(Long itemId);
}
