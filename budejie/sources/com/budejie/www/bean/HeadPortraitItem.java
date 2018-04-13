package com.budejie.www.bean;

import java.io.Serializable;

public class HeadPortraitItem implements Serializable {
    private String fans_count;
    private String introduce;
    private String is_follow;
    public boolean is_vip;
    private String praise_time;
    private String profile_image;
    private String sex;
    private String tiezi_count;
    private String userid;
    private String username;

    public String getFans_count() {
        return this.fans_count;
    }

    public void setFans_count(String str) {
        this.fans_count = str;
    }

    public String getTiezi_count() {
        return this.tiezi_count;
    }

    public void setTiezi_count(String str) {
        this.tiezi_count = str;
    }

    public String getIs_follow() {
        return this.is_follow;
    }

    public void setIs_follow(String str) {
        this.is_follow = str;
    }

    public String getIntroduce() {
        return this.introduce;
    }

    public void setIntroduce(String str) {
        this.introduce = str;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String str) {
        this.sex = str;
    }

    public String getProfile_image() {
        return this.profile_image;
    }

    public void setProfile_image(String str) {
        this.profile_image = str;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String str) {
        this.userid = str;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getPraise_time() {
        return this.praise_time;
    }

    public void setPraise_time(String str) {
        this.praise_time = str;
    }
}
