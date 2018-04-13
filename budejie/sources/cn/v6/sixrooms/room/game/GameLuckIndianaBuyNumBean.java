package cn.v6.sixrooms.room.game;

import java.io.Serializable;

public class GameLuckIndianaBuyNumBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String atnum;
    private String gid;
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getAtnum() {
        return this.atnum;
    }

    public void setAtnum(String str) {
        this.atnum = str;
    }

    public String getGid() {
        return this.gid;
    }

    public void setGid(String str) {
        this.gid = str;
    }
}
