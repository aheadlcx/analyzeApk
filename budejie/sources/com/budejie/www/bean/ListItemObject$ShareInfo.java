package com.budejie.www.bean;

import java.io.Serializable;

public class ListItemObject$ShareInfo implements Serializable {
    private static final long serialVersionUID = 1;
    private String name;
    private String share_time;
    private String uid;

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getShare_time() {
        return this.share_time;
    }

    public void setShare_time(String str) {
        this.share_time = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }
}
