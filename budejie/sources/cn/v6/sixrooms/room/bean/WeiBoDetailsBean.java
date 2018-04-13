package cn.v6.sixrooms.room.bean;

import java.io.Serializable;

public class WeiBoDetailsBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String aid = "";
    private String audname = "";
    private String falias = "";
    private String fname = "";
    private WeiBoForwardPicBean forwardPic;
    private String fuid = "";
    private String game = "";
    private String gift = "";
    private String link;
    private String mid = "";
    private WeiBoMp3Bean mp3;
    private String msg = "";
    private String num = "";
    private WeiBoPicBean pic;
    private String pid = "";
    private String rootid = "";
    private String talias = "";
    private String tuid = "";
    private String ualias = "";
    private String uid = "";
    private String url = "";

    public WeiBoForwardPicBean getForwardPic() {
        return this.forwardPic;
    }

    public void setForwardPic(WeiBoForwardPicBean weiBoForwardPicBean) {
        this.forwardPic = weiBoForwardPicBean;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public WeiBoPicBean getPic() {
        return this.pic;
    }

    public void setPic(WeiBoPicBean weiBoPicBean) {
        this.pic = weiBoPicBean;
    }

    public WeiBoMp3Bean getMp3() {
        return this.mp3;
    }

    public void setMp3(WeiBoMp3Bean weiBoMp3Bean) {
        this.mp3 = weiBoMp3Bean;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(String str) {
        this.pid = str;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }

    public String getAudname() {
        return this.audname;
    }

    public void setAudname(String str) {
        this.audname = str;
    }

    public String getAid() {
        return this.aid;
    }

    public void setAid(String str) {
        this.aid = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getUalias() {
        return this.ualias;
    }

    public void setUalias(String str) {
        this.ualias = str;
    }

    public String getTuid() {
        return this.tuid;
    }

    public void setTuid(String str) {
        this.tuid = str;
    }

    public String getTalias() {
        return this.talias;
    }

    public void setTalias(String str) {
        this.talias = str;
    }

    public String getGift() {
        return this.gift;
    }

    public void setGift(String str) {
        this.gift = str;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String str) {
        this.num = str;
    }

    public String getFuid() {
        return this.fuid;
    }

    public void setFuid(String str) {
        this.fuid = str;
    }

    public String getFalias() {
        return this.falias;
    }

    public void setFalias(String str) {
        this.falias = str;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String str) {
        this.fname = str;
    }

    public String getGame() {
        return this.game;
    }

    public void setGame(String str) {
        this.game = str;
    }

    public String getMid() {
        return this.mid;
    }

    public void setMid(String str) {
        this.mid = str;
    }

    public String getRootid() {
        return this.rootid;
    }

    public void setRootid(String str) {
        this.rootid = str;
    }

    public String toString() {
        return "WeiBoDetailsBean [msg=" + this.msg + ", pic=" + this.pic + ", mp3=" + this.mp3 + ", url=" + this.url + ", pid=" + this.pid + ", link=" + this.link + ", audname=" + this.audname + ", aid=" + this.aid + ", uid=" + this.uid + ", ualias=" + this.ualias + ", tuid=" + this.tuid + ", talias=" + this.talias + ", gift=" + this.gift + ", num=" + this.num + ", fuid=" + this.fuid + ", falias=" + this.falias + ", fname=" + this.fname + ", game=" + this.game + ", mid=" + this.mid + ", rootid=" + this.rootid + ", forwardPic=" + this.forwardPic + "]";
    }
}
