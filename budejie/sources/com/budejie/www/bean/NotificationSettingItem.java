package com.budejie.www.bean;

import com.google.gson.annotations.SerializedName;

public class NotificationSettingItem {
    private String db_key;
    @SerializedName("is_show_android")
    private int is_show;
    private String name;
    private int user_setting;

    public String getDb_key() {
        return this.db_key;
    }

    public NotificationSettingItem(String str, String str2, int i, int i2) {
        this.db_key = str;
        this.name = str2;
        this.is_show = i;
        this.user_setting = i2;
    }

    public void setDb_key(String str) {
        this.db_key = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getIs_show() {
        return this.is_show;
    }

    public void setIs_show(int i) {
        this.is_show = i;
    }

    public int getUser_setting() {
        return this.user_setting;
    }

    public void setUser_setting(int i) {
        this.user_setting = i;
    }
}
