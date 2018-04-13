package cn.v6.sixrooms.room.bean;

import java.io.Serializable;

public class OperatorFlowBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String activityUrl;
    private String buyinfo;
    private String logourl;
    private String msg;
    private String operator;

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String str) {
        this.operator = str;
    }

    public String getLogourl() {
        return this.logourl;
    }

    public void setLogourl(String str) {
        this.logourl = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getActivityUrl() {
        return this.activityUrl;
    }

    public void setActivityUrl(String str) {
        this.activityUrl = str;
    }

    public String getBuyinfo() {
        return this.buyinfo;
    }

    public void setBuyinfo(String str) {
        this.buyinfo = str;
    }
}
