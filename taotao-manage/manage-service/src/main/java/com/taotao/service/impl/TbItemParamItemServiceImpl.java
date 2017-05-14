package com.taotao.service.impl;

import com.taotao.dao.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.TbItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.TaoTaoResult;

/**
 * Created by Administrator on 2017/5/12.
 */
@Service
public class TbItemParamItemServiceImpl implements TbItemParamItemService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TaoTaoResult<TbItemParamItem> selectByItemId(Long itemId) {
        if (itemId==null) throw new NullPointerException("itemId is null");
        TbItemParamItem itemParamItem = itemParamItemMapper.selectByItemId(itemId);
        if (itemParamItem==null){
            return TaoTaoResult.build(-1,"未找到该商品参数",null);
        }
        return TaoTaoResult.ok(itemParamItem);
    }
}
