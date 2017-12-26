package com.easyzhang.operate;

import com.easyzhang.util.EZLog;
import com.easyzhang.util.EzQueue;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by EasyZhang on 2017-12-25.
 */
public class EzFilter {

    // 编码
    private static final String ECODING = "UTF-8";
    // 获取img标签正则
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
    // 获取src路径的正则
    private static final String IMGSRC_REG = "(?<=(src=\"))[^\"]*?(?=\")";

    public static void getQueue(String html) {
         List<String> list = getImageSrc(getImageUrl(html));
         if(list == null|| list.isEmpty()){
             EZLog.getInstance().addMessage("这个页面没有图片！！！！！\n");
             return ;
         }
         list.forEach(a-> {
             try {
                 StringBuilder stringBuilder = new StringBuilder();
                 if('h' != (a.charAt(0))){
                     stringBuilder.append("https:");
                     URL url = new URL(stringBuilder.toString());
                     EzQueue.getInstance().push(url);
                 }
                 stringBuilder.append(a);
                 if('i' == a.charAt(a.length()-1)){
                 }else {
                     stringBuilder.append("g");
                     URL url = new URL(stringBuilder.toString());
                     EzQueue.getInstance().push(url);
                 }
             }catch (Exception e){
                 e.printStackTrace();
             }
         });
    }

    private static List<String> getImageUrl(String HTML) {
        Matcher matcher = Pattern.compile(IMGURL_REG).matcher(HTML);
        List<String> listImgUrl = new ArrayList<String>();
        while (matcher.find()) {
            listImgUrl.add(matcher.group());
        }
        return listImgUrl;
    }

    private static List<String> getImageSrc(List<String> listImageUrl) {
        List<String> listImgSrc = new ArrayList<String>();
        for (String image : listImageUrl) {
            Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(image);
            while (matcher.find()) {
                listImgSrc.add(matcher.group().substring(0,
                        matcher.group().length() - 1));
            }
        }
        return listImgSrc;
    }
}
