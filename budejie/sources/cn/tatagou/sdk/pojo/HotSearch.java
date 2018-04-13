package cn.tatagou.sdk.pojo;

public class HotSearch extends CommonResponseResult {
    private String keyword;
    private String style;
    private String url;

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String str) {
        this.keyword = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getStyle() {
        return this.style;
    }

    public void setStyle(String str) {
        this.style = str;
    }
}
