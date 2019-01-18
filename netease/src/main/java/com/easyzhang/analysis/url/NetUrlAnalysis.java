package com.easyzhang.analysis.url;

import com.easyzhang.dto.NewsQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

/**
 * @author EasyZhang
 * @date 2019/1/18 -  17:13
 */

public class NetUrlAnalysis extends UrlAnalysis {

    private static final Logger logger = LoggerFactory.getLogger(NetUrlAnalysis.class);
    private static final String[] ROOT_URLS = {"http://news.163.com/world","http://war.163.com/"};

    protected NewsQueue newsQueue;

    public NetUrlAnalysis(NewsQueue newsQueue) {
        this.newsQueue = newsQueue;
    }

    @Override
    String getPattern() {
        String baseURL = "163.com/";
        LocalDate localDate = LocalDate.now();
        //减去2000年
        int thisYear = localDate.getYear() - 2000;
        int thisMonth = localDate.getMonthValue();
        int thisDay = localDate.getDayOfMonth();

        baseURL += thisYear+"/";

        if(thisMonth<10){
            baseURL += "0"+thisMonth;
        }else {
            baseURL += thisMonth+"";
        }

        if(thisDay<10){
            baseURL += "0"+thisDay+"/";
        }else {
            baseURL += thisDay+"/";
        }
        return baseURL;
    }

    @Override
    public void getNewsUrls() {
        for(String rootUrl : ROOT_URLS){

        }
        if(isOrderPattern("https://news.163.com/19/0118/05/E5PFTIRC0001899N.html")){
            newsQueue.push("https://news.163.com/19/0118/05/E5PFTIRC0001899N.html");
        }
    }
}
