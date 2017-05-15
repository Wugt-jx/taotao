package com.taotao.service;

import com.taotao.pojo.TbContentCategory;
import pojo.EasyUITreeNodeResult;
import pojo.TaoTaoResult;

import java.util.List;

/**
 * Created by Administrator on 2017/5/15.
 */
public interface TbContentCategoryService {
     List<EasyUITreeNodeResult> getContentCategoryList(Long parentId);
     TaoTaoResult save(Long parentId,String name);
     TaoTaoResult delete(Long parentId,Long id);
}
