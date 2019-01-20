package com.easyzhang.analysis.url;


import com.easyzhang.dto.NewsUrlQueue;
import org.jsoup.nodes.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

/**
 * @author EasyZhang
 * @date 2019/1/18 -  16:16
 * 根据document取URL
 */

public interface UrlAnalysis extends Function<Document, List<String>> {

    @Override
    List<String> apply(Document document);
}
