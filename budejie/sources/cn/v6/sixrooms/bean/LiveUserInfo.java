package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class LiveUserInfo implements Serializable {
    private String alias;
    private String flvtitle;
    private String from;
    private String picuser;
    private String prid;
    private String site;
    private String sound;
    private String uid;

    public String getPicuser() {
        return this.picuser;
    }

    public void setPicuser(String str) {
        this.picuser = str;
    }

    public String getFlvtitle() {
        return this.flvtitle;
    }

    public void setFlvtitle(String str) {
        this.flvtitle = str;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getSound() {
        return this.sound;
    }

    public void setSound(String str) {
        this.sound = str;
    }

    public String getSite() {
        return this.site;
    }

    public void setSite(String str) {
        this.site = str;
    }

    public String getPrid() {
        return this.prid;
    }

    public void setPrid(String str) {
        this.prid = str;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String str) {
        this.from = str;
    }
}
