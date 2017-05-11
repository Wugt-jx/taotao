package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.dao.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.TbItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.EasyUIDataGridResult;
import pojo.TaoTaoResult;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */
@Service
public class TbItemParamServiceImpl implements TbItemParamService {


    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbItemParamMapper itemParamMapper;

    @Override
    public TaoTaoResult<TbItemParam> selectByItemCatId(Long item_cat_id) {
        if (item_cat_id==null) throw new NullPointerException("item_cat_id is null");
        TbItemParam itemParam = itemParamMapper.selectByItemCatId(item_cat_id);
        if (itemParam!=null){
            return TaoTaoResult.ok(itemParam);
        }
        return new TaoTaoResult<>(-1,"not found the item_cat_id="+item_cat_id+" itemParam.");
    }

    @Override
    public EasyUIDataGridResult<TbItemParam> selectByLimit(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<TbItemParam> tbItemParams = itemParamMapper.selectList();
        PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(tbItemParams);
        long total = pageInfo.getTotal();
        EasyUIDataGridResult<TbItemParam> result = new EasyUIDataGridResult<>(total,tbItemParams);
        return result;
    }


}
