package cn.v6.sixrooms.bean;

public class LiveStateBean extends MessageBean {
    private static final long serialVersionUID = 1;
    private String content;
    private String flvtitle;
    private String secflvtitle;
    private String typeID;
    private String videotype;

    public String getFlvtitle() {
        return this.flvtitle;
    }

    public void setFlvtitle(String str) {
        this.flvtitle = str;
    }

    public String getSecflvtitle() {
        return this.secflvtitle;
    }

    public void setSecflvtitle(String str) {
        this.secflvtitle = str;
    }

    public String getVideotype() {
        return this.videotype;
    }

    public void setVideotype(String str) {
        this.videotype = str;
    }

    public String getTypeID() {
        return this.typeID;
    }

    public void setTypeID(String str) {
        this.typeID = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }
}
