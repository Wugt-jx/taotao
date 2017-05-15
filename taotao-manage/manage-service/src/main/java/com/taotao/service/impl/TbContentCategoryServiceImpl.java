package com.taotao.service.impl;

import com.alibaba.druid.util.DaemonThreadFactory;
import com.taotao.dao.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.EasyUITreeNodeResult;
import pojo.TaoTaoResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/15.
 */
@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Autowired
    private TbContentCategory contentCategory;

    @Override
    public List<EasyUITreeNodeResult> getContentCategoryList(Long parentId) {
        if (parentId==null){throw new NullPointerException("parentId is null ! ");}
        List<EasyUITreeNodeResult> results = new ArrayList<>();
        for (TbContentCategory category:contentCategoryMapper.selectByParentId(parentId)){
            EasyUITreeNodeResult node= new EasyUITreeNodeResult();
            node.setId(category.getId());
            node.setText(category.getName());
            node.setState(category.getIsParent()?"closed":"open");
            results.add(node);
        }
        return results;
    }


    @Override
    @Transactional
    public TaoTaoResult save(Long parentId,String name) {
        if (parentId==null||name==null||"".equals(name.trim())){throw new NullPointerException("lack of parameter !");}
        Date date = new Date();
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        contentCategory.setSortOrder(1);
        contentCategory.setIsParent(false);
        contentCategory.setStatus(true);
        contentCategory.setUpdated(date);
        contentCategory.setCreated(date);
        contentCategoryMapper.insert(contentCategory);

        TbContentCategory parentContentCategory = contentCategoryMapper.selectById(parentId);
        if (!parentContentCategory.getIsParent()){
            parentContentCategory.setIsParent(true);
            parentContentCategory.setUpdated(date);
            contentCategoryMapper.setParent(parentContentCategory);
        }
        return TaoTaoResult.ok(contentCategory);
    }

    @Override
    @Transactional
    public TaoTaoResult delete(Long parentId, Long id) {
        if (parentId==null||id==null){
            throw new NullPointerException("lack of parameter !");
        }
        Date date = new Date();
        contentCategoryMapper.delete(id);
        List<TbContentCategory> contentCategories = contentCategoryMapper.selectByParentId(parentId);
        if (contentCategories.size()<=0){
            TbContentCategory parentCategory = contentCategoryMapper.selectById(parentId);
            parentCategory.setIsParent(false);
            contentCategoryMapper.setParent(parentCategory);
        }
        return TaoTaoResult.ok();
    }





}
