package com.easyzhang;

import com.easyzhang.operate.EzConnection;
import com.easyzhang.operate.EzDownload;
import com.easyzhang.util.EzDownloadQueue;
import com.easyzhang.util.EzWaitQueue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * Created by EasyZhang on 2017-12-26.
 */
public class EZMain {
      public static void main(String[] args) throws Exception{


        String baseURL = "http://tech.163.com/19/0117/01/E5ME6STK00097U81.html";
        Document doc = Jsoup.connect(baseURL).get();
        String title = doc.select("meta[property=og:title]").attr("content");
        String tag = doc.select("meta[property=article:tag]").attr("content");
        String href = doc.select("a[id=ne_article_source]").attr("href");
        String source = doc.select("a[id=ne_article_source]").text();
        Elements elements = doc.getElementById("endText").select("p");
        String content = "";
        for(Element element : elements){
            content += element.toString();
        }
        System.out.println(content);
    }
}
