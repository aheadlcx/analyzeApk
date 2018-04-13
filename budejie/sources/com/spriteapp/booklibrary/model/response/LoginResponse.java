package com.spriteapp.booklibrary.model.response;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    private String user_avatar;
    private int user_false_point;
    private int user_gender;
    private int user_id;
    private String user_mobile;
    private String user_nickname;
    private int user_real_point;
    private int user_vip_class;

    public int getUser_id() {
        return this.user_id;
    }

    public void setUser_id(int i) {
        this.user_id = i;
    }

    public String getUser_nickname() {
        return this.user_nickname;
    }

    public void setUser_nickname(String str) {
        this.user_nickname = str;
    }

    public int getUser_gender() {
        return this.user_gender;
    }

    public void setUser_gender(int i) {
        this.user_gender = i;
    }

    public String getUser_mobile() {
        return this.user_mobile;
    }

    public void setUser_mobile(String str) {
        this.user_mobile = str;
    }

    public String getUser_avatar() {
        return this.user_avatar;
    }

    public void setUser_avatar(String str) {
        this.user_avatar = str;
    }

    public int getUser_real_point() {
        return this.user_real_point;
    }

    public void setUser_real_point(int i) {
        this.user_real_point = i;
    }

    public int getUser_false_point() {
        return this.user_false_point;
    }

    public void setUser_false_point(int i) {
        this.user_false_point = i;
    }

    public int getUser_vip_class() {
        return this.user_vip_class;
    }

    public void setUser_vip_class(int i) {
        this.user_vip_class = i;
    }
}
