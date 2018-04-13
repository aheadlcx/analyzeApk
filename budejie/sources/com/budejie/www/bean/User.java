package com.budejie.www.bean;

import java.io.Serializable;

public class User implements Serializable {
    public String id;
    public String is_vip;
    public String personal_page;
    public String profile_image;
    public String qq_uid;
    public String qzone_uid;
    public String sex;
    public String username;
    public String weibo_uid;

    public User(String str) {
        this.username = str;
    }
}
