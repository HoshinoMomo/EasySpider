package com.easyzhang;

import com.easyzhang.analysis.page.NetPageAnalysis;
import com.easyzhang.analysis.url.NetUrlAnalysis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @author EasyZhang
 * @date 2019/1/18 -  17:19
 */

public class NeteaseBootstrap extends Bootstarp<NetUrlAnalysis, NetPageAnalysis> {

    private static final Logger logger = LoggerFactory.getLogger(NeteaseBootstrap.class);

    public NeteaseBootstrap(NetUrlAnalysis netUrlAnalysis,List<String> rootUrls, NetPageAnalysis netPageAnalysis){
        this.urlAnalysis =netUrlAnalysis;
        this.rootUrls = rootUrls;
        this.pageAnalysis = netPageAnalysis;
    }

    public static void main(String[] args){
        List<String> rootUrls = Arrays.asList("http://news.163.com/");
        NeteaseBootstrap neteaseBootstrap = new NeteaseBootstrap(new NetUrlAnalysis(),rootUrls,new NetPageAnalysis());
        neteaseBootstrap.start();
    }
}
