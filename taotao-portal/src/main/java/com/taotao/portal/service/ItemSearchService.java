package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * Created by Administrator on 2017/5/18.
 */
public interface ItemSearchService {
    public SearchResult search(String queryString, int page);
}
