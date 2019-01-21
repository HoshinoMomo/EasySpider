package com.easyzhang.analysis.pattern;

import java.time.LocalDate;

/**
 * @author EasyZhang
 * @date 2019/1/21 -  14:58
 */
public interface UrlPattern {

    //扫除来的URL是否符合扫描规范
    boolean isUrlMatchPattern(String testUrl, LocalDate localDate);

    //扫出来的新闻URL是否符合新闻URL规范
    boolean isNewsUrlMatchPattern(String testUrl, LocalDate localDate);
}
