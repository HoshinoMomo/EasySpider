package com.easyzhang;

import com.easyzhang.analysis.page.NetPageAnalysis;
import com.easyzhang.analysis.url.NetUrlAnalysis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * @author EasyZhang
 * @date 2019/1/18 -  17:19
 */

public class NeteaseBootstrap extends Bootstarp<NetUrlAnalysis, NetPageAnalysis> {

    private static final Logger logger = LoggerFactory.getLogger(NeteaseBootstrap.class);

    public NeteaseBootstrap(NetUrlAnalysis netUrlAnalysis,List<String> rootUrls, NetPageAnalysis netPageAnalysis){
        this.urlAnalysis = netUrlAnalysis;
        this.rootUrls = rootUrls;
        this.pageAnalysis = netPageAnalysis;
    }

    @Override
    public boolean isUrlMatchPattern(String testUrl, LocalDate localDate) {
        String baseURL = "163.com/";
        return testUrl.contains(baseURL);
    }

    @Override
    public boolean isNewsUrlMatchPattern(String testUrl, LocalDate localDate) {
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

   /*     if(thisDay<10){
            baseURL += "0"+thisDay+"/";
        }else {
            baseURL += thisDay+"/";
        }*/
        return testUrl.contains(baseURL);
    }

    public static void main(String[] args){
        List<String> rootUrls = Arrays.asList("http://news.163.com/");
        NeteaseBootstrap neteaseBootstrap = new NeteaseBootstrap(new NetUrlAnalysis(),rootUrls,new NetPageAnalysis());
        neteaseBootstrap.start();
    }
}
