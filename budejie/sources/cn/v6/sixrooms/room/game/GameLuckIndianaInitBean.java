package cn.v6.sixrooms.room.game;

import cn.v6.sixrooms.bean.MessageBean;

public class GameLuckIndianaInitBean extends MessageBean {
    private static final long serialVersionUID = 1;
    private String atnum;
    private String btm;
    private String coin;
    private String etm;
    private String gid;
    private String id;
    private String prize;
    private String src;
    private String title;
    private String tnum;
    private String type;
    private String typeid;
    private String uanum;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getCoin() {
        return this.coin;
    }

    public void setCoin(String str) {
        this.coin = str;
    }

    public String getTnum() {
        return this.tnum;
    }

    public void setTnum(String str) {
        this.tnum = str;
    }

    public String getAtnum() {
        return this.atnum;
    }

    public void setAtnum(String str) {
        this.atnum = str;
    }

    public String getUanum() {
        return this.uanum;
    }

    public void setUanum(String str) {
        this.uanum = str;
    }

    public String getGid() {
        return this.gid;
    }

    public void setGid(String str) {
        this.gid = str;
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

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getSrc() {
        return this.src;
    }

    public void setSrc(String str) {
        this.src = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getPrize() {
        return this.prize;
    }

    public void setPrize(String str) {
        this.prize = str;
    }

    public String getTypeid() {
        return this.typeid;
    }

    public void setTypeid(String str) {
        this.typeid = str;
    }
}
