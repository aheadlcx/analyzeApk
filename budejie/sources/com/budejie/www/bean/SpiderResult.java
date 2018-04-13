package com.budejie.www.bean;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class SpiderResult {
    @SerializedName("spider")
    public Map<String, Spider> spider;

    public String toString() {
        return "SpiderResult [spider=" + this.spider + "]";
    }
}
