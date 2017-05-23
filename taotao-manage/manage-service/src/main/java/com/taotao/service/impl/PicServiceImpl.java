package com.taotao.service.impl;

import com.taotao.service.PicService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pojo.PicResult;
import util.FtpUtil;
import util.IDUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/10.
 */
@Service
public class PicServiceImpl implements PicService {

    //图片服务器ip地址，用于拼接url保存到数据库中
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    //将图片上传后，服务器放置图片的根路径
    @Value("${FILI_UPLOAD_PATH}")
    private String FILI_UPLOAD_PATH;

    //使用ftp服务时，指定的图片服务器的ip
    @Value("${FTP_SERVER_IP}")
    private String FTP_SERVER_IP;

    //指定图片上传时，指定服务器ftp服务的端口号 默认为21
    @Value("${FTP_SERVER_PORT}")
    private Integer FTP_SERVER_PORT;

    //图片服务器上传图片使用的用户
    @Value("${FTP_SERVER_USERNAME}")
    private String FTP_SERVER_USERNAME;

    //用户密码
    @Value("${FTP_SERVER_PASSWORD}")
    private String FTP_SERVER_PASSWORD;


    @Override
    public PicResult fileUpload(MultipartFile uploadFile) {
        String result = saveFile(uploadFile);
        if (result==null){
            return new PicResult(1,"","上传失败！");
        }else {
            return new PicResult(0,result,"上传成功！");
        }
    }

    /**
     * 上传图片
     * @param saveFile
     * @return
     */
    public String saveFile(MultipartFile saveFile) {
        String result = null;
        if (saveFile.isEmpty())
            return null;

        //根据上传的日期，创建文件夹，确保图片服务器中的文件夹不会因图片文件过多造成服务器压力
        String filePath = "/" + new SimpleDateFormat("yyyy").format(new Date()) + "/"
                + new SimpleDateFormat("MM").format(new Date()) + "/"
                + new SimpleDateFormat("dd").format(new Date());
        String originalFilename = saveFile.getOriginalFilename();

        //创建保存到图片服务器时所用的文件名
        String newFileName = IDUtils.genImageName() + originalFilename.substring(originalFilename.lastIndexOf("."));
        try {
            boolean success =  FtpUtil.uploadFile(FTP_SERVER_IP, FTP_SERVER_PORT, FTP_SERVER_USERNAME, FTP_SERVER_PASSWORD,
                    FILI_UPLOAD_PATH, filePath, newFileName, saveFile.getInputStream());
            if (success) {
                result = IMAGE_BASE_URL + filePath + "/" + newFileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }




}
