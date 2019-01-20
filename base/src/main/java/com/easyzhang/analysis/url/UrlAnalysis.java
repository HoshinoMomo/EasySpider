package com.easyzhang.analysis.url;


import com.easyzhang.dto.NewsUrlQueue;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

/**
 * @author EasyZhang
 * @date 2019/1/18 -  16:16
 * 根据rootUrls 找到一堆新闻的链接，并放到队列里面
 */

public interface UrlAnalysis extends Function<List<String>, NewsUrlQueue> {

    @Override
    NewsUrlQueue apply(List<String> rootUrls);
}
