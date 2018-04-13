package com.budejie.www.bean;

import java.io.Serializable;
import java.util.List;

public class RecommendUser implements Serializable {
    String id;
    String introduction;
    String profile_image;
    List<Topics> topics;
    String userid;
    String username;

    public List<Topics> getTopics() {
        return this.topics;
    }

    public void setTopics(List<Topics> list) {
        this.topics = list;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
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

    public String getProfile_image() {
        return this.profile_image;
    }

    public void setProfile_image(String str) {
        this.profile_image = str;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String str) {
        this.introduction = str;
    }
}
