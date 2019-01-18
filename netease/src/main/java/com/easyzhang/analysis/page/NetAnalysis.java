package com.easyzhang.analysis.page;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author EasyZhang
 * @date 2019/1/18 -  17:06
 */

public class NetAnalysis extends PageAnalysis {

    private static final Logger logger = LoggerFactory.getLogger(NetAnalysis.class);

    public NetAnalysis(String url){
        this.url = url;
    }

    @Override
    public void run() {
     //   String baseURL = "http://tech.163.com/19/0117/01/E5ME6STK00097U81.html";
        try {
            Document doc = Jsoup.connect(url).get();
            String title = doc.select("meta[property=og:title]").attr("content");
            String tag = doc.select("meta[property=article:tag]").attr("content");
            String href = doc.select("a[id=ne_article_source]").attr("href");
            String source = doc.select("a[id=ne_article_source]").text();
            Elements elements = doc.getElementById("endText").select("p");
            StringBuilder content = new StringBuilder();
            for(Element element : elements){
                content.append(element.toString());
            }
            new easyzhang.dto.NewsDto(title,tag,href,source,content.toString());
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }

    }
}
