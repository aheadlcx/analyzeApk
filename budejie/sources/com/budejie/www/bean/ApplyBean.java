package com.budejie.www.bean;

public class ApplyBean {
    private String name;
    private String state;
    private String url;
    private int versioncode;

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public int getVersioncode() {
        return this.versioncode;
    }

    public void setVersioncode(int i) {
        this.versioncode = i;
    }
}
