package cn.v6.sixrooms.room.game;

import java.io.Serializable;

public class GameLuckIndianaResultBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String alias;
    private String buynum;
    private String gid;
    private GameLuckIndianaInitBean ngame;
    private String propid;
    private String rid;
    private String sendnum;
    private String title;
    private String uid;

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getBuynum() {
        return this.buynum;
    }

    public void setBuynum(String str) {
        this.buynum = str;
    }

    public String getPropid() {
        return this.propid;
    }

    public void setPropid(String str) {
        this.propid = str;
    }

    public String getSendnum() {
        return this.sendnum;
    }

    public void setSendnum(String str) {
        this.sendnum = str;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public String getRid() {
        return this.rid;
    }

    public void setRid(String str) {
        this.rid = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getGid() {
        return this.gid;
    }

    public void setGid(String str) {
        this.gid = str;
    }

    public GameLuckIndianaInitBean getNgame() {
        return this.ngame;
    }

    public void setNgame(GameLuckIndianaInitBean gameLuckIndianaInitBean) {
        this.ngame = gameLuckIndianaInitBean;
    }
}
