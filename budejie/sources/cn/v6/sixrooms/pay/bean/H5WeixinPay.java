package cn.v6.sixrooms.pay.bean;

public class H5WeixinPay {
    private String gid;
    private String link;
    private String method;
    private String orderid;
    private String param;
    private String typeid;
    private String useralias;
    private String userid;
    private String userrid;

    public String getGid() {
        return this.gid;
    }

    public void setGid(String str) {
        this.gid = str;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public String getParam() {
        return this.param;
    }

    public void setParam(String str) {
        this.param = str;
    }

    public String getOrderid() {
        return this.orderid;
    }

    public void setOrderid(String str) {
        this.orderid = str;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String str) {
        this.userid = str;
    }

    public String getUserrid() {
        return this.userrid;
    }

    public void setUserrid(String str) {
        this.userrid = str;
    }

    public String getUseralias() {
        return this.useralias;
    }

    public void setUseralias(String str) {
        this.useralias = str;
    }

    public String getTypeid() {
        return this.typeid;
    }

    public void setTypeid(String str) {
        this.typeid = str;
    }
}
