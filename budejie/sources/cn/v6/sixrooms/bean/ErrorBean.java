package cn.v6.sixrooms.bean;

public class ErrorBean extends MessageBean {
    private static final long serialVersionUID = 1;
    private String content;
    private String flag;
    private String t;
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String str) {
        this.flag = str;
    }

    public String getT() {
        return this.t;
    }

    public void setT(String str) {
        this.t = str;
    }
}
