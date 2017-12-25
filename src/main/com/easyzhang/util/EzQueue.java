package com.easyzhang.util;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by eazyzhang on 2017-12-25.
 */
public class EzQueue {

    //制造一个URL队列
    private LinkedList<URL> urls = new LinkedList<>();

    private static EzQueue ezQueue;

    private EzQueue(){}

    public static EzQueue getInstance(){
        return ezQueue;
    }

    public static void createEzQueue(){
        ezQueue = new EzQueue();
    }
    public synchronized void push(URL url){
        urls.addFirst(url);
    }

    public URL peek(){
        return urls.getFirst();
    }

    public URL pop(){
       return urls.removeFirst();
    }

    public boolean isEmpty(){
        return urls.isEmpty();
    }

}
