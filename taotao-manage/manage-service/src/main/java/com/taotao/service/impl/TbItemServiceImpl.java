package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.dao.TbItemDescMapper;
import com.taotao.dao.TbItemMapper;
import com.taotao.dao.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.TbItemService;
import com.taotao.service.util.SynSearchIndexUtil;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.EasyUIDataGridResult;
import pojo.TaoTaoResult;
import util.IDUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
@Service
public class TbItemServiceImpl implements TbItemService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbItemMapper itemMapper;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbItemDescMapper itemDescMapper;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbItemDesc itemDesc;

    @Autowired
    private TbItemParamItem itemParamItem;

    @Override
    public TbItem findById(long id) {
        return itemMapper.findById(id);
    }

    @Override
    public EasyUIDataGridResult<TbItem> selectByLimit(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<TbItem> tbItems = itemMapper.SelectByLimit();
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(tbItems);
        long total = pageInfo.getTotal();
        EasyUIDataGridResult<TbItem> result = new EasyUIDataGridResult<TbItem>(total, tbItems);
        return result;
    }

    @Override
    @Transactional
    public TaoTaoResult insert(TbItem item, String desc,String itemParams) {
        if (item == null) {throw new NullPointerException("item is null!");}

        Date date = new Date();
        item.setId(IDUtils.genItemId());
        item.setStatus(true);
        item.setCreated(date);
        item.setUpdated(date);
        itemMapper.insert(item);

        if (desc != null ||!"".equals(desc.trim())) {
            itemDesc.setItemId(item.getId());
            itemDesc.setItemDesc(desc);
            itemDesc.setCreated(date);
            itemDesc.setUpdated(date);
            itemDescMapper.insert(itemDesc);
        }

        if (itemParams!=null ||!"".equals(itemParams.trim())){
            itemParamItem.setItemId(item.getId());
            itemParamItem.setParamData(itemParams);
            itemParamItem.setCreated(date);
            itemParamItem.setUpdated(date);
            itemParamItemMapper.insert(itemParamItem);
        }

        try {
            SynSearchIndexUtil.synSearchIndex(item.getId());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
        return TaoTaoResult.ok();
    }



    @Override
    @Transactional
    public TaoTaoResult update(TbItem item, String desc,TbItemParamItem itemParams) {
        if (item == null) {throw new NullPointerException("item is null");}
        Date date = new Date();
        item.setUpdated(date);
        itemMapper.update(item);
        if (desc != null || !"".equals(desc.trim())) {
            itemDesc.setItemId(item.getId());
            itemDesc.setItemDesc(desc);
            itemDesc.setUpdated(date);
            itemDescMapper.update(itemDesc);
        }
        if (itemParams!=null
                &&itemParams.getId()!=null
                &&itemParams.getParamData()!=null
                &&!"".equals(itemParams.getParamData().trim())){
            itemParamItem.setUpdated(date);
            itemParamItemMapper.update(itemParams);
        }

        return TaoTaoResult.ok();
    }

    @Override
    @Transactional
    public TaoTaoResult delete(Long[] ids) {
        if (ids==null||ids.length>1);
        for (Long id:ids){
            itemMapper.delete(id);
            itemDescMapper.delete(id);
            itemParamItemMapper.delete(id);
        }
        return TaoTaoResult.ok();
    }

}