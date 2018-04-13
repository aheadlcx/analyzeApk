package com.budejie.www.bean;

import android.text.TextUtils;

public class UserItem {
    String Is_black_device;
    String Is_black_user;
    String age_group;
    String backgroundImage;
    String birthday;
    String bookmark;
    String credit;
    String degree;
    String experience;
    String fansCount;
    String followCount;
    String grade;
    String id;
    String introduction;
    String jie_v;
    String level;
    String name;
    String phone;
    String profile;
    String qq;
    String sex;
    String sina_v;
    String tiezi_count;
    String token;
    String trade_history;
    String trade_ruler;
    String v_desc;
    String weixin;

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String str) {
        this.birthday = str;
    }

    public String getQq() {
        return this.qq;
    }

    public void setQQ(String str) {
        this.qq = str;
    }

    public String getBookmark() {
        return this.bookmark;
    }

    public void setBookmark(String str) {
        this.bookmark = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String str) {
        this.sex = str;
    }

    public String getProfile() {
        return this.profile;
    }

    public void setProfile(String str) {
        this.profile = str;
    }

    public String getInstroduce() {
        return this.introduction;
    }

    public void setInstroduce(String str) {
        this.introduction = str;
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public String getFollowCount() {
        return TextUtils.isEmpty(this.followCount) ? "0" : this.followCount;
    }

    public void setFollowCount(String str) {
        this.followCount = str;
    }

    public String getFansCount() {
        return TextUtils.isEmpty(this.fansCount) ? "0" : this.fansCount;
    }

    public void setFansCount(String str) {
        this.fansCount = str;
    }

    public void setWeixin(String str) {
        this.weixin = str;
    }

    public String getWeixin() {
        return this.weixin;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String str) {
        this.level = str;
    }

    public String getCredit() {
        return this.credit;
    }

    public void setCredit(String str) {
        this.credit = str;
    }

    public String getExperience() {
        return this.experience;
    }

    public void setExperience(String str) {
        this.experience = str;
    }

    public String getSina_v() {
        return this.sina_v;
    }

    public void setSina_v(String str) {
        this.sina_v = str;
    }

    public String getJie_v() {
        return this.jie_v;
    }

    public void setJie_v(String str) {
        this.jie_v = str;
    }

    public String getV_desc() {
        return this.v_desc;
    }

    public void setV_desc(String str) {
        this.v_desc = str;
    }

    public String getAge_group() {
        return this.age_group;
    }

    public void setAge_group(String str) {
        this.age_group = str;
    }

    public String getDegree() {
        return this.degree;
    }

    public void setDegree(String str) {
        this.degree = str;
    }

    public String getTrade_history() {
        return this.trade_history;
    }

    public void setTrade_history(String str) {
        this.trade_history = str;
    }

    public String getTrade_ruler() {
        return this.trade_ruler;
    }

    public void setTrade_ruler(String str) {
        this.trade_ruler = str;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String str) {
        this.grade = str;
    }

    public String getIs_black_user() {
        return this.Is_black_user;
    }

    public void setIs_black_user(String str) {
        this.Is_black_user = str;
    }

    public String getIs_black_device() {
        return this.Is_black_device;
    }

    public void setIs_black_device(String str) {
        this.Is_black_device = str;
    }

    public String getTiezi_count() {
        return TextUtils.isEmpty(this.tiezi_count) ? "0" : this.tiezi_count;
    }

    public void setTiezi_count(String str) {
        this.tiezi_count = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }
}
