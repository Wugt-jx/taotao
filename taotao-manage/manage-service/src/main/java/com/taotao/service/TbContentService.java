package com.taotao.service;

import com.taotao.pojo.TbContent;
import pojo.EasyUIDataGridResult;
import pojo.TaoTaoResult;

/**
 * Created by wgt on 2017/5/15.
 */
public interface TbContentService {

    public EasyUIDataGridResult<TbContent> selectList(Integer page, Integer rows,Long categoryId);

    public TaoTaoResult save(TbContent content);

    public TaoTaoResult update(TbContent content);

    public TaoTaoResult delete(Long[] ids);
}
