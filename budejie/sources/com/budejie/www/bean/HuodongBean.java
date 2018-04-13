package com.budejie.www.bean;

public class HuodongBean {
    private String content;
    private String huodongId;
    private String picUrl;
    private String reserve;
    private String shareUrl;
    private int theme_id;
    private String title;
    private String type;
    private String uid;
    private String videoUrl;
    private String voiceUrl;

    public HuodongBean(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        this.huodongId = str;
        this.content = str2;
        this.picUrl = str3;
        this.voiceUrl = str4;
        this.videoUrl = str5;
        this.title = str6;
        this.shareUrl = str7;
        this.type = str8;
        this.uid = str9;
        this.reserve = str10;
    }

    public String getHuodongId() {
        return this.huodongId;
    }

    public void setHuodongId(String str) {
        this.huodongId = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setPicUrl(String str) {
        this.picUrl = str;
    }

    public String getVoiceUrl() {
        return this.voiceUrl;
    }

    public void setVoiceUrl(String str) {
        this.voiceUrl = str;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public void setVideoUrl(String str) {
        this.videoUrl = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getShareUrl() {
        return this.shareUrl;
    }

    public void setShareUrl(String str) {
        this.shareUrl = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getReserve() {
        return this.reserve;
    }

    public void setReserve(String str) {
        this.reserve = str;
    }

    public int getTheme_id() {
        return this.theme_id;
    }

    public void setTheme_id(int i) {
        this.theme_id = i;
    }
}
