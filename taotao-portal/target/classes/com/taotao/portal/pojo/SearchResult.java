package com.taotao.portal.pojo;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
public class SearchResult {
    private Long recordCount;
    private List<Item> itemList;
    private Long pageCount;
    private Long curPage;


    public SearchResult() {
    }

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public Long getCurPage() {
        return curPage;
    }

    public void setCurPage(Long curPage) {
        this.curPage = curPage;
    }
}
