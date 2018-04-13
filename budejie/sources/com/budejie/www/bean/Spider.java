package com.budejie.www.bean;

import com.google.gson.annotations.SerializedName;

public class Spider {
    @SerializedName("title")
    public String title;
    @SerializedName("videourl")
    public String videourl;

    public String toString() {
        return "Spider [title=" + this.title + ", videourl=" + this.videourl + "]";
    }
}
