package com.budejie.www.bean;

public class MyNewLabelItem {
    private String body;
    private String ctime;
    private int theme_id;
    private String title;
    private String type;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String str) {
        this.body = str;
    }

    public String getCtime() {
        return this.ctime;
    }

    public void setCtime(String str) {
        this.ctime = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public int getTheme_id() {
        return this.theme_id;
    }

    public void setTheme_id(int i) {
        this.theme_id = i;
    }
}
