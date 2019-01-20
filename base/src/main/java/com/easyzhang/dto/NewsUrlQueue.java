package com.easyzhang.dto;


import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author EasyZhang
 * @date 2019/1/18 -  16:10
 */

public class NewsUrlQueue {

    //制造一个URL队列
    private LinkedList<String> urls = new LinkedList<>();
    private boolean close = true;
    private Set<String> foundNews = new HashSet<>();

    public NewsUrlQueue(){}

    public synchronized void push(String url){
        if(close){
            return;
        }
        if(foundNews.add(url)){
            urls.addFirst(url);
        }
    }

    public synchronized void pushAll(Collection<String> stringCollection){
        if(close){
            return;
        }
        stringCollection.forEach(s -> {
            if(foundNews.add(s)){
                urls.addFirst(s);
            }
        });
    }

    //初始化启动
    public void init(Collection<String> baseUrls){
        this.close = false;
        baseUrls.forEach(baseUrl->{
            urls.addFirst(baseUrl);
        });
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

    public int getSize(){
        return urls.size();
    }

    public void cloe(){
        this.close = true;
    }

    public void open(){
        this.close = false;
    }
}
