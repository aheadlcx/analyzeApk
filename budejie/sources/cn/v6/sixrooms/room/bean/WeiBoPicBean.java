package cn.v6.sixrooms.room.bean;

import java.io.Serializable;

public class WeiBoPicBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String link;
    private String pid;
    private String url;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(String str) {
        this.pid = str;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }

    public String toString() {
        return "WeiBoPicBean [url=" + this.url + ", pid=" + this.pid + ", link=" + this.link + "]";
    }
}
