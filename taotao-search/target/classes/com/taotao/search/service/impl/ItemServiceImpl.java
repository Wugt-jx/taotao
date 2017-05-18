package com.taotao.search.service.impl;

import com.taotao.search.dao.ItemMapper;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.TaoTaoResult;

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
        List<Item> itemList = mapper.getItemList();
        for (Item item:itemList){
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id",item.getId());
            document.addField("item_title",item.getTitle());
            document.addField("item_sell_point",item.getSell_point());
            document.addField("item_price",item.getPrice());
            document.addField("item_image", item.getImage());
            document.addField("item_category_name", item.getCategory_name());
            solrServer.add(document);
        }
        solrServer.commit();
        return TaoTaoResult.ok();
    }
}
