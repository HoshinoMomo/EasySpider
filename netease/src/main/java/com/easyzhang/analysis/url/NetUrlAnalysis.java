package com.easyzhang.analysis.url;

import com.easyzhang.analysis.page.PageAnalysis;
import com.easyzhang.dto.NewsUrlQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

/**
 * @author EasyZhang
 * @date 2019/1/18 -  17:13
 */

public class NetUrlAnalysis implements UrlAnalysis {

    private static final Logger logger = LoggerFactory.getLogger(NetUrlAnalysis.class);
    @Override
    public NewsUrlQueue apply(List<String> rootUrls) {
        NewsUrlQueue newsUrlQueue = new NewsUrlQueue();
        rootUrls.forEach(rootUrl-> {

        });
        return newsUrlQueue;
    }

    private boolean getPattern(String testUrl, LocalDate localDate) {
        String baseURL = "163.com/";
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
        return testUrl.contains(baseURL);
    }
}