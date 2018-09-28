package com.easyzhang.operate;

import com.easyzhang.util.EzDownloadQueue;
import com.easyzhang.util.EzWaitQueue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by EasyZhang 2017-12-25.
 */
public class EzConnection implements Runnable{
     private Set<String> urls = new HashSet<>();
     private String baseUrl;
     public EzConnection(String baseUrl){
           this.baseUrl = baseUrl;
     }
     public void getURLHtml(String url){
         try {
             System.out.println(url+"页，开始扫描");
             Document doc = Jsoup.connect(url).get();
             Elements links = doc.select("a[href]");
             Elements pngs = doc.select("img[src$=.png]");//所有引用png图片的元素
             Elements jpgs = doc.select("img[src$=.jpg]");//所有引用png图片的元素

             links.forEach(link->{
                 if(link.attr("abs:href").contains(baseUrl)){
                     if(urls.add(link.attr("abs:href"))){
                         EzWaitQueue.getInstance().push(link.attr("abs:href"));
                         System.out.println(link.attr("abs:href"));
                     }
                 }
             });
             pngs.forEach(link->{
                 EzDownloadQueue.getInstance().push(link.attr("src"));
                 System.out.println(link.attr("src"));
             });
             jpgs.forEach(link->{
                 EzDownloadQueue.getInstance().push(link.attr("src"));
                 System.out.println(link.attr("src"));
             });
         }catch (Exception e){
             e.printStackTrace();
         }
     }
    @Override
    public void run() {
         while(true){
             if (!EzWaitQueue.getInstance().isEmpty()){
                 getURLHtml(EzWaitQueue.getInstance().pop());
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }
    }
}
