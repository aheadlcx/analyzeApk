package cn.v6.sixrooms.room;

import cn.v6.sixrooms.bean.MessageBean;

public class SuperFireworksBean extends MessageBean {
    private String bg;
    private String link;
    private String localname;
    private String localrid;
    private String localuid;
    private String uname;
    private String urid;

    public String getBg() {
        return this.bg;
    }

    public void setBg(String str) {
        this.bg = str;
    }

    public String getUrid() {
        return this.urid;
    }

    public void setUrid(String str) {
        this.urid = str;
    }

    public String getLocalrid() {
        return this.localrid;
    }

    public void setLocalrid(String str) {
        this.localrid = str;
    }

    public String getUname() {
        return this.uname;
    }

    public void setUname(String str) {
        this.uname = str;
    }

    public String getLocalname() {
        return this.localname;
    }

    public void setLocalname(String str) {
        this.localname = str;
    }

    public String getLocaluid() {
        return this.localuid;
    }

    public void setLocaluid(String str) {
        this.localuid = str;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }
}
