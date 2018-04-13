package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class RoomNotice implements Serializable {
    private static final long serialVersionUID = 1;
    private String frid;
    private String from;
    private String id;
    private String item;
    private String num;
    private String pubtime;
    private String rid;
    private String tm;
    private String to;
    private String trid;

    public String getRid() {
        return this.rid;
    }

    public void setRid(String str) {
        this.rid = str;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public String getFrid() {
        return this.frid;
    }

    public void setFrid(String str) {
        this.frid = str;
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

    public String getItem() {
        return this.item;
    }

    public void setItem(String str) {
        this.item = str;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String str) {
        this.num = str;
    }

    public String getTm() {
        return this.tm;
    }

    public void setTm(String str) {
        this.tm = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getPubtime() {
        return this.pubtime;
    }

    public void setPubtime(String str) {
        this.pubtime = str;
    }

    public String toString() {
        return "RoomNotice [rid=" + this.rid + ", from=" + this.from + ", frid=" + this.frid + ", to=" + this.to + ", trid=" + this.trid + ", item=" + this.item + ", num=" + this.num + ", tm=" + this.tm + ", id=" + this.id + ", pubtime=" + this.pubtime + "]";
    }
}
