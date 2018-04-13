package com.budejie.www.bean;

import android.text.TextUtils;

public class DingNewsItem {
    private String body;
    private String download;
    private String header;
    private String id;
    private String name;
    private String reserve;
    private String time;
    private String title;
    private String type;
    private String uid;
    private String voicetime;
    private String voiceuri;
    private String wid;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
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

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = str.replace("T", " ");
        }
        this.time = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getReserve() {
        return this.reserve;
    }

    public void setReserve(String str) {
        this.reserve = str;
    }

    public String getDownload() {
        return this.download;
    }

    public void setDownload(String str) {
        this.download = str;
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

    public void setUid(String str) {
        this.uid = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setWid(String str) {
        this.wid = str;
    }

    public String getWid() {
        return this.wid;
    }
}
