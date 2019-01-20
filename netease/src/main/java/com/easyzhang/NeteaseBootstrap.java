package com.easyzhang;

import com.easyzhang.Bootstarp;
import com.easyzhang.analysis.page.NetAnalysis;
import com.easyzhang.analysis.url.NetUrlAnalysis;
import com.easyzhang.dto.NewsDto;
import com.easyzhang.dto.NewsUrlQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author EasyZhang
 * @date 2019/1/18 -  17:19
 */

public class NeteaseBootstrap extends Bootstarp<NetUrlAnalysis,NetAnalysis> {

    private static final Logger logger = LoggerFactory.getLogger(NeteaseBootstrap.class);

    public static void main(String[] args){
        List<String> rootUrls = Arrays.asList("","");
        NeteaseBootstrap neteaseBootstrap = new NeteaseBootstrap();
        neteaseBootstrap.operator(new NetUrlAnalysis(),rootUrls,new NetAnalysis());
    }
}
