package cn.v6.sixrooms.room;

import cn.v6.sixrooms.bean.MessageBean;

public class PublicNoticeBean extends MessageBean {
    private String bg;
    private String link;
    private String msg;
    private String tname;
    private String trid;
    private String tuid;
    private String type;
    private String uname;
    private String urid;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getUrid() {
        return this.urid;
    }

    public void setUrid(String str) {
        this.urid = str;
    }

    public String getUname() {
        return this.uname;
    }

    public void setUname(String str) {
        this.uname = str;
    }

    public String getTrid() {
        return this.trid;
    }

    public void setTrid(String str) {
        this.trid = str;
    }

    public String getTname() {
        return this.tname;
    }

    public void setTname(String str) {
        this.tname = str;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getBg() {
        return this.bg;
    }

    public void setBg(String str) {
        this.bg = str;
    }

    public String getTuid() {
        return this.tuid;
    }

    public void setTuid(String str) {
        this.tuid = str;
    }
}
