package cn.v6.sixrooms.bean;

public class AnchorPrompt extends MessageBean {
    private String content;
    private String toUid;
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getToUid() {
        return this.toUid;
    }

    public void setToUid(String str) {
        this.toUid = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }
}
