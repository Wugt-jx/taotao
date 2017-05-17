package com.taotao.rest.service.impl;

import com.alibaba.fastjson.JSON;
import com.taotao.dao.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.rest.redis.JedisClient;
import com.taotao.rest.service.ItemCatService;
import com.taotao.rest.vo.CatResult;
import com.taotao.rest.vo.CateNode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/15.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private TbItemCatMapper itemCatMapper;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private CatResult catResult;

    @Autowired
    private JedisClient jedisClient;

    @Value("INDEX_CAT_REDIS_KEY")
    private String INDEX_CAT_REDIS_KEY;

    @Override
    public String getItemCatList() {
        String result=null;
        try{
           result = jedisClient.get(INDEX_CAT_REDIS_KEY);
           if (!StringUtils.isBlank(result)) {
               return result;
           }
        }catch (Exception e){
            e.printStackTrace();
        }
        catResult.setData(getCatList(0L));
        result = JSON.toJSONString(catResult);

        try {
            jedisClient.set(INDEX_CAT_REDIS_KEY,result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 递归获取全部数据
     * @param parentId
     * @return
     */
    public List<?> getCatList(Long parentId){
        List<TbItemCat> itemCats = itemCatMapper.selectByParentId(parentId);

        List resultList = new ArrayList<>();
        int count =0 ;
        for (TbItemCat itemCat:itemCats){
            if (itemCat.getIsParent()){
                CateNode cateNode = new CateNode();
                if (parentId==0){
                    cateNode.setName("<a href='/products/"+itemCat.getId()+".html'>"+itemCat.getName()+"</a>");
                }else {
                    cateNode.setName(itemCat.getName());
                }
                cateNode.setUrl("/products/"+itemCat.getId()+".html");
                cateNode.setItem(getCatList(itemCat.getId()));
                resultList.add(cateNode);
                count++;
                if (parentId==0 && count>=14){
                    break;
                }
            }else {
                resultList.add("/products/"+itemCat.getId()+".html|" + itemCat.getName());
            }
        }

        return resultList;
    }
}
