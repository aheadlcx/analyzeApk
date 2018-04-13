package cn.v6.sixrooms.room.game;

import cn.v6.sixrooms.bean.MessageBean;

public class GameStartBean extends MessageBean {
    private static final long serialVersionUID = 9118129592272569844L;
    private int flag;
    private int gameid;
    private int loopid;
    private int rid;

    public GameStartBean(int i, int i2, int i3, int i4) {
        this.rid = i;
        this.gameid = i2;
        this.loopid = i3;
        this.flag = i4;
    }

    public int getRid() {
        return this.rid;
    }

    public void setRid(int i) {
        this.rid = i;
    }

    public int getGameid() {
        return this.gameid;
    }

    public void setGameid(int i) {
        this.gameid = i;
    }

    public int getLoopid() {
        return this.loopid;
    }

    public void setLoopid(int i) {
        this.loopid = i;
    }

    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int i) {
        this.flag = i;
    }

    public String toString() {
        return "GameStartBean [rid=" + this.rid + ", gameid=" + this.gameid + ", loopid=" + this.loopid + ", flag=" + this.flag + "]";
    }
}
