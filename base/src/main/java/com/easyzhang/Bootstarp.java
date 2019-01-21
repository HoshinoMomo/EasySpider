package com.easyzhang;

import com.easyzhang.analysis.page.PageAnalysis;
import com.easyzhang.analysis.pattern.UrlPattern;
import com.easyzhang.analysis.url.UrlAnalysis;
import com.easyzhang.dto.NewsDto;
import com.easyzhang.dto.NewsUrlQueue;
import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
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
public abstract class Bootstarp<T extends UrlAnalysis,R extends PageAnalysis> implements UrlPattern{

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
                 String url = newsUrlQueue.pop();
                 //  //开头的东西，不要了
                 if(url.charAt(0) == '/'){
                     continue;
                 }
                 //判断是否符合扫描的URL规范
                 if(isUrlMatchPattern(url, LocalDate.now())){
                     logger.info(url);
                     Document document = Jsoup.connect(url).get();
                     this.newsUrlQueue.pushAll(urlAnalysis.apply(document));

                     //如果拿超过了500条，就关闭队列，不能再push
                     if(this.newsUrlQueue.getSize() > 5000){
                         this.newsUrlQueue.cloe();
                     }
                     //判断这个URL是不是新闻URL
                     if(isNewsUrlMatchPattern(url, LocalDate.now())){
                         //不断的读新闻,读新闻，丢进线程池
                         executorService.submit(()->{
                             NewsDto newsDto = pageAnalysis.apply(document);
                             if(Objects.nonNull(newsDto)){
                                 logger.info(newsDto.getTitle());
                             }
                         });
                     }
                 }
             } catch (HttpStatusException e) {
                 logger.error("页面缺失" + e.getMessage());
             } catch (IOException e) {
                 logger.error(e.getMessage(),e);
             }

         }
         sleep();
     }

     //休息
     private void sleep(){
        logger.info("进入休息状态，休息一个小时");
         try {
             Thread.sleep(3600000);
             logger.info("休息结束，开始干活");
             work();
         } catch (InterruptedException e) {
             logger.error(e.getMessage(),e);
         }
     }
}
