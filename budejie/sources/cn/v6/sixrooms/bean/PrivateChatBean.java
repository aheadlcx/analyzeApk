package cn.v6.sixrooms.bean;

import java.util.List;

public class PrivateChatBean extends MessageBean {
    private static final long serialVersionUID = 1;
    private String content;
    private String fid;
    private String frid;
    private String from;
    private List prop;
    private String tname;
    private String to;
    private String trid;

    public String getFid() {
        return this.fid;
    }

    public void setFid(String str) {
        this.fid = str;
    }

    public String getFrid() {
        return this.frid;
    }

    public void setFrid(String str) {
        this.frid = str;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String str) {
        this.to = str;
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

    public List getProp() {
        return this.prop;
    }

    public void setProp(List list) {
        this.prop = list;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }
}
