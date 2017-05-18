package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

/**
 * Created by Administrator on 2017/5/18.
 */
public interface ItemSearchService {
    public SearchResult search(String queryString, int page, int rows) throws Exception;
}
