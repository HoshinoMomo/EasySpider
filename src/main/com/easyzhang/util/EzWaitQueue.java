package com.easyzhang.util;

import java.net.URL;
import java.util.LinkedList;

/**
 * Created by EasyZhang on 2017-12-29.
 */
public class EzWaitQueue {
    //制造一个等待扫描的 URL队列
    private LinkedList<String> urls = new LinkedList<>();

    private static EzWaitQueue ezWaitQueue;

    private EzWaitQueue(){}

    public static EzWaitQueue getInstance(){
        return ezWaitQueue;
    }

    public static void createEzWaitQueue(){
        ezWaitQueue = new EzWaitQueue();
    }
    public synchronized void push(String url){
        urls.addFirst(url);
    }

    public String peek(){
        return urls.getFirst();
    }

    public String pop(){
        return urls.removeFirst();
    }

    public boolean isEmpty(){
        return urls.isEmpty();
    }
}
