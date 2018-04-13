package cn.v6.sixrooms.bean;

public class FireworkFailBean extends MessageBean {
    private String msg;
    private String redid;
    private String state;

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }

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
