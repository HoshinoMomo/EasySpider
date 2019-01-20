package com.easyzhang.analysis.page;

import com.easyzhang.dto.NewsDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

/**
 * @author EasyZhang
 * @date 2019/1/18 -  17:06
 */

public class NetPageAnalysis implements PageAnalysis {

    private static final Logger logger = LoggerFactory.getLogger(NetPageAnalysis.class);

    @Override
    public NewsDto apply(Document document) {
        String title = document.select("h1").text();
        String tag = document.select("meta[property=article:tag]").attr("content");
        String href = document.select("a[id=ne_article_source]").attr("href");
        String source = document.select("a[id=ne_article_source]").text();
        Elements elements = document.getElementById("endText").select("p");
        if(Objects.isNull(elements) || "".equals(title)){   //如果里面没有文章，这个就是空，是空就返回空
            return null;
        }
        StringBuilder content = new StringBuilder();
        for(Element element : elements){
            content.append(element.toString());
        }
        return new NewsDto(title,tag,href,source,content.toString());
    }
}
