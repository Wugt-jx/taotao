package com.taotao.rest.service.impl;

import com.taotao.rest.dao.TbItemDescMapper;
import com.taotao.rest.service.TbItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/10.
 */
@Service
public class TbItemDescServiceImpl implements TbItemDescService {


    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbItemDescMapper itemDescMapper;


    @Override
    public String findDescById(Long item_id) {
        return itemDescMapper.findDescById(item_id);
    }
}
