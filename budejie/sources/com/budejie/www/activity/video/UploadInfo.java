package com.budejie.www.activity.video;

import java.io.Serializable;

public class UploadInfo implements Serializable {
    private static final long serialVersionUID = 1;
    private String account;
    private String ctime;
    private String duration;
    private String fileSize;
    private String height;
    private String image;
    private String name;
    private String platform;
    private String position;
    private String title;
    private String token;
    private String topicid;
    private String uid;
    private String video;
    private String width;

    public String getWidth() {
        return this.width;
    }

    public void setWidth(String str) {
        this.width = str;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String str) {
        this.height = str;
    }

    public String getCtime() {
        return this.ctime;
    }

    public void setCtime(String str) {
        this.ctime = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(String str) {
        this.fileSize = str;
    }

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

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String str) {
        this.duration = str;
    }

    public String getTopicid() {
        return this.topicid;
    }

    public void setTopicid(String str) {
        this.topicid = str;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String str) {
        this.platform = str;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String str) {
        this.account = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String str) {
        this.position = str;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public String getVideo() {
        return this.video;
    }

    public void setVideo(String str) {
        this.video = str;
    }

    public String toString() {
        return "UploadInfo [title=" + this.title + ", duration=" + this.duration + ", topicid=" + this.topicid + ", platform=" + this.platform + ", account=" + this.account + ", token=" + this.token + ", position=" + this.position + ", image=" + this.image + ", video=" + this.video + ", name=" + this.name + ", fileSize=" + this.fileSize + ", uid=" + this.uid + ", ctime=" + this.ctime + "]";
    }
}
