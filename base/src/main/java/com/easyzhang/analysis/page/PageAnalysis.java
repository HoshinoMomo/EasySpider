package com.easyzhang.analysis.page;

import com.easyzhang.dto.NewsDto;

import java.util.function.Function;

/**
 * @author EasyZhang
 * @date 2019/1/18 -  14:40
 * 根据新闻链接，取新闻
 */
public interface PageAnalysis extends Function<String,NewsDto>{

    @Override
    NewsDto apply(String url);
}
