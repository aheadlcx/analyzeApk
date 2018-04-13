package com.budejie.www.bean;

import java.io.Serializable;

public class VideoOnlineBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String cookie;
    private String nikename;
    private String scheme;
    private String telephone;
    private String uid;

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getNikename() {
        return this.nikename;
    }

    public void setNikename(String str) {
        this.nikename = str;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String str) {
        this.telephone = str;
    }

    public String getCookie() {
        return this.cookie;
    }

    public void setCookie(String str) {
        this.cookie = str;
    }

    public String getScheme() {
        return this.scheme;
    }

    public void setScheme(String str) {
        this.scheme = str;
    }
}
