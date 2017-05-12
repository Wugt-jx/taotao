package com.taotao.rest.service;

import pojo.EasyUITreeNodeResult;

import java.util.List;

/**
 * Created by wgt on 2017/5/8.
 */
public interface TbItemCatService {

    public List<EasyUITreeNodeResult> selectByParentId(long id);
}
