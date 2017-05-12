package com.taotao.rest.service;

import org.springframework.web.multipart.MultipartFile;
import pojo.PicResult;

/**
 * Created by Administrator on 2017/5/10.
 */
public interface PicService {

    public PicResult fileUpload(MultipartFile uploadFile);

}
