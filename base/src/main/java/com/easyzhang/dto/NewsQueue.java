package com.easyzhang.dto;


import java.util.LinkedList;

/**
 * @author EasyZhang
 * @date 2019/1/18 -  16:10
 */

public class NewsQueue {

    //制造一个URL队列
    private LinkedList<String> urls = new LinkedList<>();

    public NewsQueue(){}

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
