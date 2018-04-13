package com.tencent.weibo.sdk.android.model;

public class Firend {
    private String headurl;
    private String name;
    private String nick;

    public String getNick() {
        return this.nick;
    }

    public void setNick(String str) {
        this.nick = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getHeadurl() {
        return this.headurl;
    }

    public void setHeadurl(String str) {
        this.headurl = str;
    }
}
