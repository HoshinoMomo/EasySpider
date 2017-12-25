package com.easyzhang.operate;

import com.easyzhang.util.EzQueue;

/**
 * Created by EasyZhang on 2017-12-25.
 */
public class EzDownload {
    public static void main(String[] args){
        //初始化队列
        EzQueue.createEzQueue();
        EzConnection ezConnection = new EzConnection("https://www.csdn.net/");
        EzFilter.getQueue(ezConnection.getURLQueue());
        System.out.println(EzQueue.getInstance().peek().toString());
    }
}
