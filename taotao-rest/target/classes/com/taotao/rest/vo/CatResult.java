package com.taotao.rest.vo;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/5/15.
 */
@Component
public class CatResult {
    private List<?> data;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
