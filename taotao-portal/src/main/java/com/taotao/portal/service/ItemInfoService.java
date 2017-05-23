package com.taotao.portal.service;

import com.taotao.pojo.TbItem;

/**
 * Created by wgt on 2017/5/22.
 */
public interface ItemInfoService {

    public TbItem getBaseInfo(Long itemId);
    public String getDescInfo(Long itemId);
    public String getParamInfo(Long itemId);
}

