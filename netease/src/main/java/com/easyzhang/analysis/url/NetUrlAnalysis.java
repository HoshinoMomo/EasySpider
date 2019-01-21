package com.easyzhang.analysis.url;

import com.easyzhang.analysis.page.PageAnalysis;
import com.easyzhang.dto.NewsUrlQueue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EasyZhang
 * @date 2019/1/18 -  17:13
 */

public class NetUrlAnalysis implements UrlAnalysis {

    private static final Logger logger = LoggerFactory.getLogger(NetUrlAnalysis.class);
    @Override
    public List<String> apply(Document document) {
        List<String> stringList = new ArrayList<>();
        Elements elements = document.select("a[href]");
        for(Element element : elements){
            stringList.add(element.attr("href"));
        }
        return stringList;
    }
}
