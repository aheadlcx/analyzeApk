package cn.v6.sixrooms.bean;

public class UserPictureBean {
    private String addtm;
    private String comNum;
    private Memo memo;
    private String picid;
    private String sourcepath;
    private String tid;
    private String title;
    private String visit_num;

    public String getPicid() {
        return this.picid;
    }

    public void setPicid(String str) {
        this.picid = str;
    }

    public String getVisit_num() {
        return this.visit_num;
    }

    public void setVisit_num(String str) {
        this.visit_num = str;
    }

    public String getSourcepath() {
        return this.sourcepath;
    }

    public void setSourcepath(String str) {
        this.sourcepath = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getAddtm() {
        return this.addtm;
    }

    public void setAddtm(String str) {
        this.addtm = str;
    }

    public Memo getMemo() {
        return this.memo;
    }

    public void setMemo(Memo memo) {
        this.memo = memo;
    }

    public String getTid() {
        return this.tid;
    }

    public void setTid(String str) {
        this.tid = str;
    }

    public String getComNum() {
        return this.comNum;
    }

    public void setComNum(String str) {
        this.comNum = str;
    }
}
