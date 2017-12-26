package com.easyzhang.operate;

import com.easyzhang.util.EZLog;
import com.easyzhang.util.EzQueue;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by EasyZhang on 2017-12-25.
 */
public class EzDownload implements Runnable{
    @Override
    public void run() {
        System.out.println("下载线程启动。。。。。。。。");
        try {
            while (true){
                if(!EzQueue.getInstance().isEmpty()){
                    EZLog.getInstance().addMessage("下载线程开始下载。。。。。。。。\n");
                    while(!EzQueue.getInstance().isEmpty()){
                       // System.out.println(EzQueue.getInstance().pop().toString());
                        download(EzQueue.getInstance().pop().toString());
                    }
                    EZLog.getInstance().addMessage("下载完成！\n");
                }
                Thread.sleep(10000);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void download(String url) {
            try {
                String imageName = url.substring(url.lastIndexOf("/") + 1,
                        url.length());

                URL uri = new URL(url);
                InputStream in = uri.openStream();

                File saveDir = new File("d:/image");
                if(!saveDir.exists()){
                    saveDir.mkdir();
                }
                FileOutputStream fo = new FileOutputStream(new File(saveDir+File.separator+imageName));
                byte[] buf = new byte[1024];
                int length = 0;
                while ((length = in.read(buf, 0, buf.length)) != -1) {
                    fo.write(buf, 0, length);
                }
                in.close();
                fo.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
