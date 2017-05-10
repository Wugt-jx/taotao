package com.taotao.service.impl;

import com.taotao.service.PicService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pojo.PicResult;
import util.FtpUtil;
import util.IDUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/10.
 */
@Service
public class PicServiceImpl implements PicService {

    @Value("${FILI_UPLOAD_PATH}")
    private String FILI_UPLOAD_PATH;
    @Value("${FTP_SERVER_IP}")
    private String FTP_SERVER_IP;
    @Value("${FTP_SERVER_PORT}")
    private Integer FTP_SERVER_PORT;
    @Value("${FTP_SERVER_USERNAME}")
    private String FTP_SERVER_USERNAME;
    @Value("${FTP_SERVER_PASSWORD}")
    private String FTP_SERVER_PASSWORD;


    @Override
    public PicResult fileUpload(MultipartFile uploadFile) {
        //String result = saveFile(uploadFile);
        //System.out.println(result);
        PicResult picResult = new PicResult(0,"http://112.74.27.58/image/load.jpg","上传成功！");
        /*if (result==null){
            picResult = new PicResult(1,"","上传失败！");
        }else {
            picResult = new PicResult(1,result,"上传成功！");
        }*/
        return picResult;
    }

    public String saveFile(MultipartFile saveFile){
        String result = null;

        try {
            if (saveFile.isEmpty())
                return null;

            String filePath = "/" + new SimpleDateFormat("yyyy").format(new Date()) + "/"
                    + new SimpleDateFormat("MM").format(new Date()) + "/"
                    + new SimpleDateFormat("dd").format(new Date());

            String originalFilename = saveFile.getOriginalFilename();

            String newFileName = IDUtils.genImageName() + originalFilename.substring(originalFilename.lastIndexOf("."));

            FtpUtil.uploadFile(FTP_SERVER_IP, FTP_SERVER_PORT, FTP_SERVER_USERNAME, FTP_SERVER_PASSWORD,
                    FILI_UPLOAD_PATH, filePath, newFileName, saveFile.getInputStream());
            result = filePath + "/" + newFileName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }




}
