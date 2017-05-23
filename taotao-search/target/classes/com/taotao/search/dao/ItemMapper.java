package com.taotao.search.dao;

import com.taotao.search.pojo.Item;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
@Component
public interface ItemMapper {

       public List<Item> getItemList();
       public Item getById(Long id);
}
