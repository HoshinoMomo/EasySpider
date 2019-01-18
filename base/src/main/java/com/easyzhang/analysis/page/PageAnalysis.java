package com.easyzhang.analysis.page;

/**
 * @author EasyZhang
 * @date 2019/1/18 -  14:40
 */
public abstract class PageAnalysis implements Runnable {

    protected String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
