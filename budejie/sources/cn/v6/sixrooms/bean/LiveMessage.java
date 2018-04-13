package cn.v6.sixrooms.bean;

public class LiveMessage extends MessageBean {
    private String content;
    private String toUid;

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getToUid() {
        return this.toUid;
    }

    public void setToUid(String str) {
        this.toUid = str;
    }
}
