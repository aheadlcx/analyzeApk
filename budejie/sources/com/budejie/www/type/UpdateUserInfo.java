package com.budejie.www.type;

import com.budejie.www.bean.NotificationSettingItem;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class UpdateUserInfo {
    private String Is_black_device;
    private String Is_black_user;
    private String age_group;
    @SerializedName("background_image")
    private String backgroundImage;
    private String birthday;
    private String bookmark;
    private String credit;
    private String degree;
    private String experience;
    @SerializedName("fans_add")
    private String fansAdd;
    @SerializedName("fans_count")
    private String fansCount;
    @SerializedName("follow_count")
    private String followCount;
    private String grade;
    private String id;
    private String introduction;
    private String jie_v;
    private String level;
    private String password;
    private String phone;
    @SerializedName("profile_image")
    private String profileImage;
    @SerializedName("profile_image_large")
    private String profileImageLarge;
    private String qq;
    private String sex;
    private String sina_v;
    @SerializedName("buttonlist")
    private List<NotificationSettingItem> switchsList;
    private String tiezi_count;
    private String trade_history;
    private String trade_ruler;
    private String username;
    private String v_desc;

    public String getQq() {
        return this.qq;
    }

    public void setQq(String str) {
        this.qq = str;
    }

    public String getBookmark() {
        return this.bookmark;
    }

    public void setBookmark(String str) {
        this.bookmark = str;
    }

    public List<NotificationSettingItem> getSwitchsList() {
        return this.switchsList;
    }

    public void setSwitchsList(List<NotificationSettingItem> list) {
        this.switchsList = list;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String str) {
        this.sex = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String str) {
        this.introduction = str;
    }

    public String getProfileImage() {
        return this.profileImage;
    }

    public void setProfileImage(String str) {
        this.profileImage = str;
    }

    public String getProfileImageLarge() {
        return this.profileImageLarge;
    }

    public void setProfileImageLarge(String str) {
        this.profileImageLarge = str;
    }

    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    public void setBackgroundImage(String str) {
        this.backgroundImage = str;
    }

    public String getFansCount() {
        return this.fansCount;
    }

    public void setFansCount(String str) {
        this.fansCount = str;
    }

    public String getFollowCount() {
        return this.followCount;
    }

    public void setFollowCount(String str) {
        this.followCount = str;
    }

    public String getFansAdd() {
        return this.fansAdd;
    }

    public void setFansAdd(String str) {
        this.fansAdd = str;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String str) {
        this.phone = str;
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

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String str) {
        this.birthday = str;
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
        return this.tiezi_count;
    }

    public void setTiezi_count(String str) {
        this.tiezi_count = str;
    }
}
