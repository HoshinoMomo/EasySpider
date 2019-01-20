package com.easyzhang;

import com.easyzhang.analysis.page.PageAnalysis;
import com.easyzhang.analysis.url.UrlAnalysis;
import com.easyzhang.dto.NewsDto;
import com.easyzhang.dto.NewsUrlQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 抽象出来的挖掘新闻的方法
 * @param <T>
 * @param <R>
 */
public abstract class Bootstarp<T extends UrlAnalysis,R extends PageAnalysis> {

    private static final Logger logger = LoggerFactory.getLogger(Bootstarp.class);
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    private NewsUrlQueue newsUrlQueue = new NewsUrlQueue();
    protected List<String> rootUrls = new ArrayList<>();

    public void operator(T t, List<String> rootUrls, R r){
        NewsUrlQueue newsUrlQueue = t.apply(rootUrls);
         while(!newsUrlQueue.isEmpty()){
             executorService.submit(()->{
                 NewsDto newsDto = r.apply(newsUrlQueue.pop());
                 logger.info(newsDto.getContent());
             });
         }
     }
}
