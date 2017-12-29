package com.easyzhang.util;

import java.net.URL;
import java.util.LinkedList;

/**
 * Created by eazyzhang on 2017-12-25.
 */
public class EzDownloadQueue {

    //制造一个URL队列
    private LinkedList<String> urls = new LinkedList<>();

    private static EzDownloadQueue ezDownloadQueue;

    private EzDownloadQueue(){}

    public static EzDownloadQueue getInstance(){
        return ezDownloadQueue;
    }

    public static void createEzDownloadQueue(){
        ezDownloadQueue = new EzDownloadQueue();
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
