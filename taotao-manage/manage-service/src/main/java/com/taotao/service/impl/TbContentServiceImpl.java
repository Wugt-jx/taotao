package com.taotao.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.taotao.dao.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.service.TbContentService;
import com.taotao.service.util.SynCacheUtil;
import constant.RedisSynConstant;
import org.apache.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.EasyUIDataGridResult;
import pojo.TaoTaoResult;
import util.HttpUtil;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wgt on 2017/5/15.
 */
@Service
public class TbContentServiceImpl implements TbContentService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbContentMapper contentMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
        try {
            TaoTaoResult synResult = SynCacheUtil.synCache(RedisSynConstant.INDEX_CONTENT_REDIS_KEY,content.getCategoryId()+"");
            if (synResult.getStatus()==RedisSynConstant.FAIL_CODE){logger.info("redis synchronization fail of cache");}
            logger.info("redis synchronization successfully of cache");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }

        return TaoTaoResult.ok();
    }

    @Override
    @Transactional
    public TaoTaoResult delete(Long[] ids) {
        if (ids.length<=0){throw new NullPointerException("ids length less than 0 !");}
        for (Long id:ids){
            contentMapper.delete(id);
            try {
                TaoTaoResult synResult = SynCacheUtil.synCache(RedisSynConstant.INDEX_CONTENT_REDIS_KEY,id+"");
                if (synResult.getStatus()==RedisSynConstant.FAIL_CODE){logger.info("redis synchronization fail of cache");}
                logger.info("redis synchronization successfully of cache");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (HttpException e) {
                e.printStackTrace();
            }
        }
        return TaoTaoResult.ok();
    }




}
