package com.budejie.www.bean;

public class MentionedItem {
    private String download;
    private String id;
    private ListItemObject pinfo;
    private String reserve;
    private String time;
    private String title;
    private String type;

    public String getDownload() {
        return this.download;
    }

    public void setDownload(String str) {
        this.download = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
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

    public ListItemObject getPinfo() {
        return this.pinfo;
    }

    public void setPinfo(ListItemObject listItemObject) {
        this.pinfo = listItemObject;
    }
}
