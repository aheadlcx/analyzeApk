package com.budejie.www.bean;

import java.io.Serializable;

public class LabelUser implements Serializable {
    String fans_count;
    String header;
    String is_follow;
    String jie_v;
    String name;
    String sina_v;
    String type;
    String uid;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getJie_v() {
        return this.jie_v;
    }

    public void setJie_v(String str) {
        this.jie_v = str;
    }

    public String getSina_v() {
        return this.sina_v;
    }

    public void setSina_v(String str) {
        this.sina_v = str;
    }

    public String getFans_count() {
        return this.fans_count;
    }

    public void setFans_count(String str) {
        this.fans_count = str;
    }

    public String getIs_follow() {
        return this.is_follow;
    }

    public void setIs_follow(String str) {
        this.is_follow = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String str) {
        this.header = str;
    }
}
