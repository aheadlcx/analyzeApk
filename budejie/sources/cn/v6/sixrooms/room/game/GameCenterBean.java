package cn.v6.sixrooms.room.game;

import java.io.Serializable;

public class GameCenterBean {
    private String action;
    private String className;
    private Serializable data;
    private String downLoadUrl;
    private String gameDescription;
    private int gameImageResource;
    private String gameName;
    private String gameType;
    private String gid;
    private String imgUrl;
    private String packageName;
    private String rid;
    private String ruid;

    public String getGid() {
        return this.gid;
    }

    public void setGid(String str) {
        this.gid = str;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String str) {
        this.className = str;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public String getRuid() {
        return this.ruid;
    }

    public void setRuid(String str) {
        this.ruid = str;
    }

    public String getDownLoadUrl() {
        return this.downLoadUrl;
    }

    public void setDownLoadUrl(String str) {
        this.downLoadUrl = str;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getRid() {
        return this.rid;
    }

    public void setRid(String str) {
        this.rid = str;
    }

    public String getGameName() {
        return this.gameName;
    }

    public void setGameName(String str) {
        this.gameName = str;
    }

    public String getGameDescription() {
        return this.gameDescription;
    }

    public void setGameDescription(String str) {
        this.gameDescription = str;
    }

    public String getGameType() {
        return this.gameType;
    }

    public void setGameType(String str) {
        this.gameType = str;
    }

    public int getGameImageResource() {
        return this.gameImageResource;
    }

    public void setGameImageResource(int i) {
        this.gameImageResource = i;
    }

    public Serializable getData() {
        return this.data;
    }

    public void setData(Serializable serializable) {
        this.data = serializable;
    }

    public String toString() {
        return "GameCenterBean [gameImageResource=" + this.gameImageResource + ", gameName=" + this.gameName + ", gameDescription=" + this.gameDescription + ", gameType=" + this.gameType + ", packageName=" + this.packageName + ", rid=" + this.rid + "]";
    }
}
