package cn.v6.sixrooms.room.game;

public class GameInfoBean {
    private String gid;
    private int inner;
    private int loopid;
    private int outer;

    public GameInfoBean(int i, String str, int i2, int i3) {
        this.outer = i;
        this.gid = str;
        this.inner = i2;
        this.loopid = i3;
    }

    public int getOuter() {
        return this.outer;
    }

    public void setOuter(int i) {
        this.outer = i;
    }

    public String getGid() {
        return this.gid;
    }

    public void setGid(String str) {
        this.gid = str;
    }

    public int getInner() {
        return this.inner;
    }

    public void setInner(int i) {
        this.inner = i;
    }

    public int getLoopid() {
        return this.loopid;
    }

    public void setLoopid(int i) {
        this.loopid = i;
    }

    public String toString() {
        return "GameInfoBean [outer=" + this.outer + ", gid=" + this.gid + ", inner=" + this.inner + ", loopid=" + this.loopid + "]";
    }
}
