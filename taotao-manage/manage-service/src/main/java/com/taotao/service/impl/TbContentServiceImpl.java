package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.dao.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.EasyUIDataGridResult;
import pojo.TaoTaoResult;

import java.util.Date;
import java.util.List;

/**
 * Created by wgt on 2017/5/15.
 */
@Service
public class TbContentServiceImpl implements TbContentService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public EasyUIDataGridResult<TbContent> selectList(Integer page, Integer rows, Long categoryId) {
        PageHelper.startPage(page, rows);
        List<TbContent> contents = contentMapper.selectLimit(categoryId);
        PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(contents);
        long total = pageInfo.getTotal();
        EasyUIDataGridResult<TbContent> dataGridResult = new EasyUIDataGridResult<>(total,contents);
        return dataGridResult;
    }

    @Override
    @Transactional
    public TaoTaoResult save(TbContent content) {
        if (content==null){
            throw new NullPointerException("content is null!");
        }
        Date date = new Date();
        content.setCreated(date);
        content.setUpdated(date);
        contentMapper.insert(content);
        return TaoTaoResult.ok();
    }

    @Override
    @Transactional
    public TaoTaoResult update(TbContent content) {
        if (content==null){throw new NullPointerException("content is null!");}
        Date date = new Date();
        content.setUpdated(date);
        contentMapper.update(content);
        return TaoTaoResult.ok();
    }

    @Override
    @Transactional
    public TaoTaoResult delete(Long[] ids) {
        if (ids.length<=0){throw new NullPointerException("ids length less than 0 !");}
        for (Long id:ids){
            contentMapper.delete(id);
        }
        return TaoTaoResult.ok();
    }
}
