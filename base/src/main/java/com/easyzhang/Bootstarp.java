package com.easyzhang;

import com.easyzhang.analysis.page.PageAnalysis;
import com.easyzhang.analysis.url.UrlAnalysis;
import com.easyzhang.dto.NewsDto;
import com.easyzhang.dto.NewsUrlQueue;
import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
    protected T urlAnalysis;
    protected R pageAnalysis;
    protected List<String> rootUrls;

    public void start(){
        if(Objects.isNull(urlAnalysis) || Objects.isNull(pageAnalysis) || CollectionUtils.isEmpty(rootUrls)){
            logger.error("No exception! 因为你没初始化变量....");
            return;
        }
        work();
    }

     //工作
     private void work(){
         newsUrlQueue.init(rootUrls);
         while(!newsUrlQueue.isEmpty()){
             try {
                 Document document = Jsoup.connect(newsUrlQueue.pop()).get();
                 this.newsUrlQueue.pushAll(urlAnalysis.apply(document));

                 //如果拿超过了500条，就关闭队列，不能再push
                 if(this.newsUrlQueue.getSize() > 500){
                     this.newsUrlQueue.cloe();
                 }

                 //不断的读新闻,读新闻，丢进线程池
                 executorService.submit(()->{
                     NewsDto newsDto = pageAnalysis.apply(document);
                     if(Objects.nonNull(newsDto)){
                         logger.info(newsDto.getTitle());
                     }
                 });
             } catch (IOException e) {
                 logger.error(e.getMessage(),e);
             }
         }
         sleep();
     }

     //休息
     private void sleep(){
        logger.info("{}进入休息状态，休息一个小时");
         try {
             Thread.sleep(360000);
             work();
         } catch (InterruptedException e) {
             logger.error(e.getMessage(),e);
         }
     }
}
