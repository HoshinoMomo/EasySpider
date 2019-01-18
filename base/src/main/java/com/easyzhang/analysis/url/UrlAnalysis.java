package com.easyzhang.analysis.url;


/**
 * @author EasyZhang
 * @date 2019/1/18 -  16:16
 *
 * 分析URL链接是否符合规范
 * EX：   网易的新闻都是  163.com/19/0118/小时/hash.html
 *        分析扫描出来的网址是不是都和这个匹配
 */

public abstract class UrlAnalysis {

     //url链接是否符合URL规范

     boolean isOrderPattern(String url){
         return url.contains(getPattern());
     }

     abstract String getPattern();
}
