package com.taotao.rest.service.impl;

import com.taotao.dao.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.TaoTaoResult;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */
@Service
public class ContentServiceImpl implements ContentService{

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbContentMapper contentMapper;


    @Override
    @Transactional
    public TaoTaoResult<TbContent> getContentList(Long contentCid) {
        if (contentCid==null){return TaoTaoResult.build(4000,"lack of parameter!");}
        List<TbContent> contents = contentMapper.selectLimit(contentCid);
        return TaoTaoResult.ok(contents);
    }
}
