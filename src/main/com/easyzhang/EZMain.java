package com.easyzhang;

import com.easyzhang.operate.EzConnection;
import com.easyzhang.operate.EzDownload;
import com.easyzhang.operate.EzFilter;
import com.easyzhang.util.EzQueue;

/**
 * Created by EasyZhang on 2017-12-26.
 */
public class EZMain {
      public static void main(String[] args){
        //初始化队列
        EzQueue.createEzQueue();
        //启动下载线程
        new Thread(new EzDownload()).start();

        EzConnection ezConnection = new EzConnection("http://www.woyaogexing.com/touxiang/");
        EzFilter.getQueue(ezConnection.getURLQueue());
        try {
            Thread.sleep(50000);
        }catch (Exception e){
            e.printStackTrace();
        }
          ezConnection = new EzConnection("http://www.woyaogexing.com/touxiang/z/wxchengshu/");
          EzFilter.getQueue(ezConnection.getURLQueue());
    }
}
