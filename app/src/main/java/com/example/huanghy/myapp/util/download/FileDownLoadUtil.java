package com.example.huanghy.myapp.util.download;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by huanghy on 2017/4/28.
 * 文件下载
 *      注意：需要在AndroidManifest.xml中声明网络权限和向sd卡中写入的权限
 */

public class FileDownLoadUtil {

    /**
     * 文件(单线程)下载,保存在Adown目录下
     * @param downloadUrl   文件下载url
     * @param fileName  文件名称（在手机sd卡中的名称）
     */
    public static void download(final String downloadUrl,final String fileName) {
//        downloadUrl = "http://gdown.baidu.com/data/wisegame/66c0762768439059/baiduditu_795.apk";
//        fileName = "baiduditu_795.apk";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //*存储在手机的sd卡中
                    String dirName = Environment.getExternalStorageDirectory() + "/Adown/";
                    File dir = new File(dirName);
                    if (!dir.exists()) {
                        dir.mkdir();
                    }

                    //*sd卡中的文件名称（路径）
                    String savePath = dirName + fileName;
                    File newFile = new File(savePath);
                    if (newFile.exists()) {//如果文件存在，则删除，为了产生覆盖效果
                        newFile.delete();
                    }

                    //构造URL
                    URL url = new URL(downloadUrl);
                    URLConnection con = url.openConnection();
                    con.setRequestProperty("Accept-Encoding", "identity");
                    //获取文件长度,若文件长度返回为0或-1表示下载失败
                    int contentLength = con.getContentLength();
                    Log.d("MainActivity", "文件长度:" + contentLength);
                    //获取输入流
                    InputStream is = con.getInputStream();
                    //
                    byte[] bs = new byte[1024];
                    int len;
                    OutputStream os = new FileOutputStream(savePath);
                    //开始读取
                    while ((len = is.read(bs)) != -1) {
                        os.write(bs,0,len);
                    }
                    //完毕，关闭所有链接
                    os.close();
                    is.close();
                    Log.d("MainActivity", "下载完成");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
