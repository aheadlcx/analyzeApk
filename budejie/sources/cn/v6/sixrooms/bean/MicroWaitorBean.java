package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class MicroWaitorBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String alias;
    private int coin6rank;
    private String flag;
    private String picuser;
    private String uid;
    private String urid;

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getUrid() {
        return this.urid;
    }

    public void setUrid(String str) {
        this.urid = str;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String str) {
        this.flag = str;
    }

    public int getCoin6rank() {
        return this.coin6rank;
    }

    public void setCoin6rank(int i) {
        this.coin6rank = i;
    }

    public String getPicuser() {
        return this.picuser;
    }

    public void setPicuser(String str) {
        this.picuser = str;
    }

    public String toString() {
        return "MicroWaitorBean [uid=" + this.uid + ", urid=" + this.urid + ", alias=" + this.alias + ", flag=" + this.flag + ", coin6rank=" + this.coin6rank + ", picuser=" + this.picuser + "]";
    }
}
