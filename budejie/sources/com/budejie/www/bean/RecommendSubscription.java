package com.budejie.www.bean;

import java.io.Serializable;

public class RecommendSubscription implements Serializable, Cloneable {
    String image_list;
    String is_default;
    String is_sub;
    String post_num;
    String post_number;
    String sub_number;
    String theme_id;
    String theme_name;
    String type;

    public String getTheme_id() {
        return this.theme_id;
    }

    public void setTheme_id(String str) {
        this.theme_id = str;
    }

    public String getTheme_name() {
        return this.theme_name;
    }

    public void setTheme_name(String str) {
        this.theme_name = str;
    }

    public String getImage_list() {
        return this.image_list;
    }

    public void setImage_list(String str) {
        this.image_list = str;
    }

    public String getSub_number() {
        return this.sub_number;
    }

    public void setSub_number(String str) {
        this.sub_number = str;
    }

    public String getPost_num() {
        return this.post_num;
    }

    public void setPost_num(String str) {
        this.post_num = str;
    }

    public String getIs_sub() {
        return this.is_sub;
    }

    public void setIs_sub(String str) {
        this.is_sub = str;
    }

    public String getIs_default() {
        return this.is_default;
    }

    public void setIs_default(String str) {
        this.is_default = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getPost_number() {
        return this.post_number;
    }

    public void setPost_number(String str) {
        this.post_number = str;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
