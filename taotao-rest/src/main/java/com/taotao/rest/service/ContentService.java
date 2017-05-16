package com.taotao.rest.service;

import com.taotao.pojo.TbContent;
import pojo.TaoTaoResult;

/**
 * Created by Administrator on 2017/5/16.
 */
public interface ContentService {
    public TaoTaoResult<TbContent> getContentList(Long contentCid);
}
