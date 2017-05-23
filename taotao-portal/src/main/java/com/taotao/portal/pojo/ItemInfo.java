package com.taotao.portal.pojo;

import com.taotao.pojo.TbItem;

/**
 * Created by wgt on 2017/5/22.
 */
public class ItemInfo extends TbItem {
    public String[] getImages() {
        String image = getImage();
        if (image != null) {
            String[] images = image.split(",");
            return images;
        }
        return null;
    }
}
