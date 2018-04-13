package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class FansBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String coin6rank;
    private String contribution;
    private String gender;
    private String money;
    private String picuser;
    private String rid;
    private String uid;
    private String uname;

    public String getMoney() {
        return this.money;
    }

    public void setMoney(String str) {
        this.money = str;
    }

    public String getRid() {
        return this.rid;
    }

    public void setRid(String str) {
        this.rid = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getContribution() {
        return this.contribution;
    }

    public void setContribution(String str) {
        this.contribution = str;
    }

    public String getUname() {
        return this.uname;
    }

    public void setUname(String str) {
        this.uname = str;
    }

    public String getPicuser() {
        return this.picuser;
    }

    public void setPicuser(String str) {
        this.picuser = str;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public String getCoin6rank() {
        return this.coin6rank;
    }

    public void setCoin6rank(String str) {
        this.coin6rank = str;
    }

    public String toString() {
        return "FansBean [rid=" + this.rid + ", uid=" + this.uid + ", contribution=" + this.contribution + ", uname=" + this.uname + ", picuser=" + this.picuser + ", gender=" + this.gender + ", coin6rank=" + this.coin6rank + ", money=" + this.money + "]";
    }
}
