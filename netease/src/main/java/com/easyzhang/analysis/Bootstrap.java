package com.easyzhang.analysis;

import com.easyzhang.analysis.page.NetAnalysis;
import com.easyzhang.analysis.url.NetUrlAnalysis;
import com.easyzhang.dto.NewsQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author EasyZhang
 * @date 2019/1/18 -  17:19
 */

public class Bootstrap {

    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args){
        NewsQueue newsQueue = new NewsQueue();

        NetUrlAnalysis netUrlAnalysis = new NetUrlAnalysis(newsQueue);
        netUrlAnalysis.getNewsUrls();

        NetAnalysis netAnalysis = new NetAnalysis(newsQueue);
        netAnalysis.asynGetNews();
    }
}
