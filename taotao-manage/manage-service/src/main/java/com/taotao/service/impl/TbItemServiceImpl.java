package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.dao.TbItemDescMapper;
import com.taotao.dao.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.EasyUIDataGridResult;
import pojo.TaoTaoResult;
import util.IDUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
@Service
public class TbItemServiceImpl implements TbItemService  {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbItemMapper itemMapper;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbItemDescMapper itemDescMapper;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbItemDesc itemDesc;

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

    @Override
    public TaoTaoResult insert(TbItem item, String desc) {
        if (item==null){
            throw new NullPointerException("item is null!");
        }
        if (desc==null||"".equals(desc.trim())){
            return insert(item);
        }
        Date date = new Date();
        item.setId(IDUtils.genItemId());
        item.setStatus(true);
        item.setCreated(date);
        item.setUpdated(date);
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemMapper.insert(item);
        itemDescMapper.insert(itemDesc);
        return TaoTaoResult.ok();
    }


    @Override
    @Transactional
    public TaoTaoResult insert(TbItem item) {

        if (item==null){
            throw new NullPointerException("item is null");
        }
        Date date = new Date();
        item.setId(IDUtils.genItemId());
        item.setStatus(true);
        item.setCreated(date);
        item.setUpdated(date);
        itemMapper.insert(item);
        return TaoTaoResult.ok();
    }

}
