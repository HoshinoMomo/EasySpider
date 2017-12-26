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
        EzConnection ezConnection = new EzConnection("http://www.woyaogexing.com/touxiang/");
        EzFilter.getQueue(ezConnection.getURLQueue());

        new Thread(new EzDownload()).start();
    }
}
