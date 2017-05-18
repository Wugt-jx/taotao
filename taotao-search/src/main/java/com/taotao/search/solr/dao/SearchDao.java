package com.taotao.search.solr.dao;

import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * Created by Administrator on 2017/5/18.
 */
public interface SearchDao {
    SearchResult search(SolrQuery query) throws Exception;
}
