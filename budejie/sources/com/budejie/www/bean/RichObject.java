package com.budejie.www.bean;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class RichObject implements Serializable {
    private static final long serialVersionUID = 1;
    private String body;
    private String desc;
    @SerializedName("img_url")
    private String imgUrl;
    @SerializedName("source_url")
    private String sourceUrl;
    private String title;

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public String getSourceUrl() {
        return this.sourceUrl;
    }

    public void setSourceUrl(String str) {
        this.sourceUrl = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String str) {
        this.body = str;
    }
}
