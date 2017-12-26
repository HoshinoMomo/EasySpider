package com.easyzhang;

import com.easyzhang.operate.EzConnection;
import com.easyzhang.operate.EzDownload;
import com.easyzhang.operate.EzFilter;
import com.easyzhang.util.EzQueue;

import java.util.Scanner;

/**
 * Created by EasyZhang on 2017-12-26.
 */
public class EZMain {
      public static void main(String[] args){
        //初始化队列
        EzQueue.createEzQueue();
        //启动下载线程
        new Thread(new EzDownload()).start();

        System.out.println("请输入地址。。。。。。。。");
        while(true){
            Scanner scanner = new Scanner(System.in);
            String baseURL = scanner.next();
            System.out.println(baseURL+"扫描中。。。。。。。。");
            EzConnection ezConnection = new EzConnection(baseURL);
            EzFilter.getQueue(ezConnection.getURLQueue());
            try {
                Thread.sleep(10000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(baseURL+"扫描结束。。。。。。。。");
            System.out.println("请输入地址。。。。。。。。");
        }
    }
}
