package cn.v6.sixrooms.bean;

import java.util.List;

public class PublicChatBean extends MessageBean {
    private static final long serialVersionUID = 1;
    private String content;
    private int cr;
    private String fid;
    private String frid;
    private String from;
    private List<String> priv;
    private List<String> prop;
    private String to;
    private String toid;
    private String torid;

    public void setCr(int i) {
        this.cr = i;
    }

    public int getCr() {
        return this.cr;
    }

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

    public String getToid() {
        return this.toid;
    }

    public void setToid(String str) {
        this.toid = str;
    }

    public String getTorid() {
        return this.torid;
    }

    public void setTorid(String str) {
        this.torid = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public List<String> getProp() {
        return this.prop;
    }

    public void setProp(List<String> list) {
        this.prop = list;
    }

    public List<String> getPriv() {
        return this.priv;
    }

    public void setPriv(List<String> list) {
        this.priv = list;
    }

    public String toString() {
        return "PublicChatBean [fid=" + this.fid + ", frid=" + this.frid + ", from=" + this.from + ", to=" + this.to + ", toid=" + this.toid + ", torid=" + this.torid + ", prop=" + this.prop + ", priv=" + this.priv + ", content=" + this.content + "]";
    }
}
