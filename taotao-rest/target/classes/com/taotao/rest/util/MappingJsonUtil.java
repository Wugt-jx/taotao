package com.taotao.rest.util;

/**
 * Created by Administrator on 2017/5/15.
 */
public class MappingJsonUtil  {
    public static String setJsonpFunciton(String result,String callback){
        return callback + "(" + result + ");";
    }
}
