package com.taotao.portal.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParam;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemInfoService;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.TaoTaoResult;
import util.HttpUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Created by wgt on 2017/5/22.
 */
@Service
public class ItemInfoServiceImpl implements ItemInfoService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_ITEM_INFO_URL}")
    private String REST_ITEM_INFO_URL;

    @Value("${REST_ITEM_DESC_URL}")
    private String REST_ITEM_DESC_URL;

    @Value("${REST_ITEM_PARAM_URL}")
    private String REST_ITEM_PARAM_URL;

    @Override
    public TbItem getBaseInfo(Long itemId) {
        if (itemId==null){throw new NullPointerException("itemId is null!");}
        try {
            String jsonResult = HttpUtil.doGet(REST_BASE_URL+REST_ITEM_INFO_URL+itemId.toString());
            TaoTaoResult<ItemInfo> taoResult = JSON.parseObject(jsonResult,new TypeReference<TaoTaoResult<ItemInfo>>(){});
            if (taoResult.getStatus()==200){
                return taoResult.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getDescInfo(Long itemId){
        if (itemId==null){throw new NullPointerException("itemId is null ! ");}
        try {
            String jsonResult = HttpUtil.doGet(REST_BASE_URL+REST_ITEM_DESC_URL+itemId.toString());
            TaoTaoResult<String> taoResult = JSON.parseObject(jsonResult,new TypeReference<TaoTaoResult<String>>(){});
            if (taoResult.getStatus()==200){
                return taoResult.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String getParamInfo(Long itemId) {
        if (itemId==null){throw new NullPointerException("itemId is null !");}
        try {
            String jsonResult = HttpUtil.doGet(REST_BASE_URL+REST_ITEM_PARAM_URL+itemId.toString());
            System.out.println(jsonResult);
            TaoTaoResult<TbItemParam> taoTaoResult = JSON.parseObject(jsonResult,new TypeReference<TaoTaoResult<TbItemParam>>(){});
            if (taoTaoResult.getStatus()==200){
                TbItemParam itemParam = taoTaoResult.getData();
                String param_data = itemParam.getParamData();
                List<Map> jsonList = JSON.parseObject(param_data, new TypeReference<List<Map>>(){});


                StringBuffer sb = new StringBuffer();
                sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
                sb.append("    <tbody>\n");

                for (Map map1:jsonList){
                    sb.append("        <tr>\n");
                    sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+map1.get("group")+"</th>\n");
                    sb.append("        </tr>\n");
                    List<Map> list2 = (List<Map>) map1.get("params");
                    for (Map map2:list2){
                        sb.append("        <tr>\n");
                        sb.append("            <td class=\"tdTitle\">"+map2.get("k")+"</td>\n");
                        sb.append("            <td>"+map2.get("v")+"</td>\n");
                        sb.append("        </tr>\n");
                    }
                }
                sb.append("    </tbody>\n");
                sb.append("</table>");
                //返回html片段
                return sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
        return "";
    }


}
