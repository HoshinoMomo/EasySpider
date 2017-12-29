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
public class EzConnection {
     private Set<String> urls = new HashSet<>();
     public EzConnection(){

     }
     public void getURLHtml(String url){
         try {
             System.out.println(url+"页，开始扫描");
             Document doc = Jsoup.connect(url).get();
             Elements links = doc.select("a[href]");
             Elements pngs = doc.select("img[src$=.png]");//所有引用png图片的元素
             Elements jpgs = doc.select("img[src$=.jpg]");//所有引用png图片的元素

             links.forEach(link->{
                     if(urls.add(link.attr("abs:href"))){
                         EzWaitQueue.getInstance().push(link.attr("abs:href"));
                         System.out.println(link.attr("abs:href"));
                     }
             });
             pngs.forEach(link->{
                // EzDownloadQueue.getInstance().push(link.attr("src"));
                 System.out.println(link.attr("src"));
             });
             jpgs.forEach(link->{
              //   EzDownloadQueue.getInstance().push(link.attr("src"));
                 System.out.println(link.attr("src"));
             });
         }catch (Exception e){
             e.printStackTrace();
         }
     }
     public void addDataToQueue(){
         while (!EzWaitQueue.getInstance().isEmpty()){
             getURLHtml(EzWaitQueue.getInstance().pop());
             try {
                 Thread.sleep(5000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     }
}
