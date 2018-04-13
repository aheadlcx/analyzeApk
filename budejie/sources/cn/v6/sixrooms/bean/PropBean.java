package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class PropBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String btm;
    private String etm;
    private int flag;
    private String guard_alias;
    private String guard_btm;
    private String guard_etm;
    private String guard_rid;
    private String guard_state;
    private String guard_uid;
    private String id;
    private int propImgId;
    private String propid;
    private String state;
    private String tag;
    private String title;
    private int typeTag;
    private String uid;

    public int getTypeTag() {
        return this.typeTag;
    }

    public void setTypeTag(int i) {
        this.typeTag = i;
    }

    public int getPropImgId() {
        return this.propImgId;
    }

    public void setPropImgId(int i) {
        this.propImgId = i;
    }

    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int i) {
        this.flag = i;
    }

    public String toString() {
        return "PropBean [tag=" + this.tag + ", typeTag=" + this.typeTag + ", id=" + this.id + ", uid=" + this.uid + ", propid=" + this.propid + ", btm=" + this.btm + ", etm=" + this.etm + ", state=" + this.state + ", title=" + this.title + ", guard_uid=" + this.guard_uid + ", guard_rid=" + this.guard_rid + ", guard_alias=" + this.guard_alias + ", guard_btm=" + this.guard_btm + ", guard_etm=" + this.guard_etm + ", guard_state=" + this.guard_state + ", flag=" + this.flag + "]";
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getPropid() {
        return this.propid;
    }

    public void setPropid(String str) {
        this.propid = str;
    }

    public String getBtm() {
        return this.btm;
    }

    public void setBtm(String str) {
        this.btm = str;
    }

    public String getEtm() {
        return this.etm;
    }

    public void setEtm(String str) {
        this.etm = str;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getGuard_uid() {
        return this.guard_uid;
    }

    public void setGuard_uid(String str) {
        this.guard_uid = str;
    }

    public String getGuard_rid() {
        return this.guard_rid;
    }

    public void setGuard_rid(String str) {
        this.guard_rid = str;
    }

    public String getGuard_alias() {
        return this.guard_alias;
    }

    public void setGuard_alias(String str) {
        this.guard_alias = str;
    }

    public String getGuard_btm() {
        return this.guard_btm;
    }

    public void setGuard_btm(String str) {
        this.guard_btm = str;
    }

    public String getGuard_etm() {
        return this.guard_etm;
    }

    public void setGuard_etm(String str) {
        this.guard_etm = str;
    }

    public String getGuard_state() {
        return this.guard_state;
    }

    public void setGuard_state(String str) {
        this.guard_state = str;
    }
}
