package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.dao.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.EasyUIDataGridResult;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
@Service
public class TbItemServiceImpl implements TbItemService  {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbItemMapper itemMapper;


    @Override
    public TbItem findById(long id) {
        return itemMapper.findById(id);
    }

    @Override
    public EasyUIDataGridResult<TbItem> selectByLimit(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<TbItem> tbItems = itemMapper.SelectByLimit();
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(tbItems);
        long total = pageInfo.getTotal();
        EasyUIDataGridResult<TbItem> result = new EasyUIDataGridResult<TbItem>(total,tbItems);

        return  result;
    }

}
