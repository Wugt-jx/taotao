package com.taotao.rest;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import util.FtpUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/5/9.
 */
public class Demo1 {

    @Test
    public void test()throws IOException{
        String host = "112.74.27.58";
        int port = 21;
        String username = "ftpuser";
        String password = "wgt110";
        String basePath = "/home/ftpuser/www";
        String filePath = "/image";
        String filename = "lalala.jpg";
        FileInputStream inputStream = new FileInputStream(new File("G:\\test_image\\123.jpg"));
        FtpUtil.uploadFile(host,port,username,password,basePath,filePath,filename,inputStream);
    }
}
