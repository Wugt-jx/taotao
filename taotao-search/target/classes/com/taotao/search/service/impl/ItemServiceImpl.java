package com.taotao.search.service.impl;

import com.taotao.pojo.TbItem;
import com.taotao.search.dao.ItemMapper;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.TaoTaoResult;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
@Service
public class ItemServiceImpl implements ItemService {


    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private ItemMapper mapper;

    @Autowired
    private SolrServer solrServer;

    @Override
    public TaoTaoResult importItemToIndex() throws Exception {
        try {

            //查询商品列表
            List<Item> list = mapper.getItemList();
            //把商品信息写入索引库
            for (Item item : list) {
                //创建一个SolrInputDocument对象
                SolrInputDocument document = new SolrInputDocument();
                document.setField("id", item.getId());
                document.setField("item_title", item.getTitle());
                document.setField("item_sell_point", item.getSell_point());
                document.setField("item_price", item.getPrice());
                document.setField("item_image", item.getImage());
                document.setField("item_category_name", item.getCategory_name());
                document.setField("item_desc", item.getItem_des());
                //写入索引库
                solrServer.add(document);
            }
            //提交修改
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return TaoTaoResult.build(500, ExceptionUtils.getStackTrace(e));
        }
        return TaoTaoResult.ok();

    }


    @Override
    public TaoTaoResult importOneItemIndex(Long itemId) {
        if (itemId==null){throw new NullPointerException("itemId is null !");}
        Item item = mapper.getById(itemId);
        SolrInputDocument document = new SolrInputDocument();
        document.setField("id", item.getId());
        document.setField("item_title", item.getTitle());
        document.setField("item_sell_point", item.getSell_point());
        document.setField("item_price", item.getPrice());
        document.setField("item_image", item.getImage());
        document.setField("item_category_name", item.getCategory_name());
        document.setField("item_desc", item.getItem_des());
        try {
            solrServer.add(document);
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TaoTaoResult.ok();

    }
}
