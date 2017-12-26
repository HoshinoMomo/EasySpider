package com.easyzhang.operate;

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
        try {
            while (true){
                if(!EzQueue.getInstance().isEmpty()){
                    System.out.println("下载线程启动。。。。。。。。");
                    Thread.sleep(3000);
                    while(!EzQueue.getInstance().isEmpty()){
                       // System.out.println(EzQueue.getInstance().pop().toString());
                        download(EzQueue.getInstance().pop().toString());
                    }
                }
                System.out.println("下载队列为空，休息10秒。。。。。。。");
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

                File saveDir = new File("d:/resource");
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
