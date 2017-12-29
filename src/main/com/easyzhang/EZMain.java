package com.easyzhang;

import com.easyzhang.operate.EzConnection;
import com.easyzhang.operate.EzDownload;
import com.easyzhang.util.EzDownloadQueue;
import com.easyzhang.util.EzWaitQueue;


/**
 * Created by EasyZhang on 2017-12-26.
 */
public class EZMain {
      public static void main(String[] args){
        //初始化下载队列队列
        EzDownloadQueue.createEzDownloadQueue();
        //初始化等待扫描URL的队列
         EzWaitQueue.createEzWaitQueue();
        //启动下载线程
        new Thread(new EzDownload()).start();

        String baseURL = "http://www.woyaogexing.com";
        EzWaitQueue.getInstance().push(baseURL);
        EzConnection ezConnection = new EzConnection(baseURL);
        ezConnection.addDataToQueue();

    }
}
