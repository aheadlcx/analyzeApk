package com.budejie.www.bean;

public class MyNewsSourceItem {
    private String cid;
    private String content;
    private String precid;
    private String preuid;
    private String time;
    private UserItem user;
    private String videotime;
    private String videouri;
    private String voicetime;
    private String voiceuri;
    private String wid;

    public String getWid() {
        return this.wid;
    }

    public void setWid(String str) {
        this.wid = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getCid() {
        return this.cid;
    }

    public void setCid(String str) {
        this.cid = str;
    }

    public String getPrecid() {
        return this.precid;
    }

    public void setPrecid(String str) {
        this.precid = str;
    }

    public String getPreuid() {
        return this.preuid;
    }

    public void setPreuid(String str) {
        this.preuid = str;
    }

    public UserItem getUser() {
        return this.user;
    }

    public void setUser(UserItem userItem) {
        this.user = userItem;
    }

    public String getVoiceuri() {
        return this.voiceuri;
    }

    public void setVoiceuri(String str) {
        this.voiceuri = str;
    }

    public String getVoicetime() {
        return this.voicetime;
    }

    public void setVoicetime(String str) {
        this.voicetime = str;
    }

    public String getVideouri() {
        return this.videouri;
    }

    public void setVideouri(String str) {
        this.videouri = str;
    }

    public String getVideotime() {
        return this.videotime;
    }

    public void setVideotime(String str) {
        this.videotime = str;
    }
}
