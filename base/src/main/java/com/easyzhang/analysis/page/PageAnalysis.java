package com.easyzhang.analysis.page;

import com.easyzhang.dto.NewsDto;
import org.jsoup.nodes.Document;

import java.util.function.Function;

/**
 * @author EasyZhang
 * @date 2019/1/18 -  14:40
 * 根据document取新闻
 */
public interface PageAnalysis extends Function<Document,NewsDto>{

    @Override
    NewsDto apply(Document document);
}
