package cn.v6.sixrooms.bean;

public class FireworkSuccessBean extends MessageBean {
    private String msg;
    private String redid;

    public String getRedid() {
        return this.redid;
    }

    public void setRedid(String str) {
        this.redid = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}
