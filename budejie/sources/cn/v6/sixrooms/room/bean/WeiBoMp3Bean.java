package cn.v6.sixrooms.room.bean;

import java.io.Serializable;

public class WeiBoMp3Bean implements Serializable {
    private static final long serialVersionUID = 1;
    private String aid = "";
    private String audname = "";
    private String link = "";

    public String getAudname() {
        return this.audname;
    }

    public void setAudname(String str) {
        this.audname = str;
    }

    public String getAid() {
        return this.aid;
    }

    public void setAid(String str) {
        this.aid = str;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }

    public String toString() {
        return "WeiBoMp3Bean [audname=" + this.audname + ", aid=" + this.aid + ", link=" + this.link + "]";
    }
}
