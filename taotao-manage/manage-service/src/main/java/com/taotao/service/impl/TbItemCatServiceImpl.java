package com.taotao.service.impl;

import com.taotao.dao.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.TbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.EasyUITreeNodeResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbItemCatServiceImpl implements TbItemCatService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EasyUITreeNodeResult> selectByParentId(long id) {

        List<TbItemCat> tbItemCats = itemCatMapper.selectByParentId(id);

        List<EasyUITreeNodeResult> results = new ArrayList<>();
        EasyUITreeNodeResult node ;

        for (TbItemCat itemCat:tbItemCats){
            node=new EasyUITreeNodeResult();
            node.setId(itemCat.getId());
            node.setText(itemCat.getName());
            node.setState(itemCat.getIsParent()?"closed":"open");
            results.add(node);
        }

        return results;
    }
}
