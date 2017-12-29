package com.easyzhang.operate;

import com.easyzhang.util.EzLog;
import com.easyzhang.util.EzDownloadQueue;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by EasyZhang on 2017-12-25.
 */
public class EzDownload implements Runnable{
    @Override
    public void run() {
        EzLog.getInstance().addMessage("下载线程开始下载。。。。。。。。\n");
        try {
            while (true){
                if(!EzDownloadQueue.getInstance().isEmpty()){
                    while(!EzDownloadQueue.getInstance().isEmpty()){
                        download(EzDownloadQueue.getInstance().pop());
                    }
                    EzLog.getInstance().addMessage("下载完成！\n");
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
                HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
                connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
                InputStream in = connection.getInputStream();

                File saveDir = new File("d:/image");
                if(!saveDir.exists()){
                    saveDir.mkdir();
                }
                EzLog.getInstance().addMessage("下载:"+saveDir+File.separator+imageName+"\n");
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
