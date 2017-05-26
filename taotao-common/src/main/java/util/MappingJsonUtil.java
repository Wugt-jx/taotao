package util;

/**
 * Created by Administrator on 2017/5/15.
 */
public class MappingJsonUtil  {
    public static String setJsonpFunciton(String result,String callback){
        StringBuilder sb_result = new StringBuilder(callback);
        sb_result.append("(").append(result).append(");");
        return sb_result.toString();
    }
}
