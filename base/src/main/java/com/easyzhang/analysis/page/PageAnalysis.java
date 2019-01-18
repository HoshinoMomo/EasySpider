package com.easyzhang.analysis.page;

import com.easyzhang.dto.NewsQueue;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author EasyZhang
 * @date 2019/1/18 -  14:40
 */
public abstract class PageAnalysis{

    protected NewsQueue newsQueue;
    protected ExecutorService executorService = Executors.newFixedThreadPool(5);

    abstract public void asynGetNews();

    public NewsQueue getNewsQueue() {
        return newsQueue;
    }

    public void setNewsQueue(NewsQueue newsQueue) {
        this.newsQueue = newsQueue;
    }
}
