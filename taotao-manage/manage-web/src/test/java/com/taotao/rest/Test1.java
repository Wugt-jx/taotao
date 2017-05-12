package com.taotao.rest;

import org.junit.Test;
import util.FtpUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by wgt on 2017/5/10.
 */
public class Test1 {


    /**
     * Description: 向FTP服务器上传文件
     * @param host FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param basePath FTP服务器基础目录
     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
     * @param filename 上传到FTP服务器上的文件名
     * @param input 输入流
     * @return 成功返回true，否则返回false
*/
    /*@Test
    public void test() throws FileNotFoundException {
        String host = "112.74.27.58";
        int port = 21;
        String username = "ftpuser";
        String password = "wgt110";
        String basePath = "/home/ftpuser/www/";
        String filePath = "image/";
        String fileName = "load.jpg";
        FileInputStream inputStream = new FileInputStream(new File("F:\\TestHTML\\bootstrap\\img\\2.jpg"));
        FtpUtil.uploadFile(host,port,username,password,basePath,filePath,fileName,inputStream);
    }*/
}
