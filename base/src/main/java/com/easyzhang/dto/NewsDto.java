package easyzhang.dto;



/**
 * @author EasyZhang
 * @date 2019/1/18 -  14:52
 */

public class NewsDto {

    private String title;
    private String tag;
    private String href;
    private String source;
    private String content;

    public NewsDto(String title, String tag, String href, String source, String content) {
        this.title = title;
        this.tag = tag;
        this.href = href;
        this.source = source;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
