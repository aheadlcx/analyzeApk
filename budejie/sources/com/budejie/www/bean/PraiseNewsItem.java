package com.budejie.www.bean;

import java.io.Serializable;

public class PraiseNewsItem implements Serializable {
    private String avatar;
    private String download;
    private String id;
    private String msgContent;
    private String reserve;
    private String time;
    private String type;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getMsgContent() {
        return this.msgContent;
    }

    public void setMsgContent(String str) {
        this.msgContent = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getReserve() {
        return this.reserve;
    }

    public void setReserve(String str) {
        this.reserve = str;
    }

    public String getDownload() {
        return this.download;
    }

    public void setDownload(String str) {
        this.download = str;
    }

    public String toString() {
        return "PraiseNewsItem [id=" + this.id + ", msgContent=" + this.msgContent + ", avatar=" + this.avatar + ", time=" + this.time + ", type=" + this.type + ", reserve=" + this.reserve + ", download=" + this.download + "]";
    }
}
