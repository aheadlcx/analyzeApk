package cn.v6.sixrooms.room.gift;

import cn.v6.sixrooms.bean.MessageBean;

public class BoxingBoxerVotes extends MessageBean {
    private long boxer1;
    private long boxer2;
    private String boxid;

    public String getBoxid() {
        return this.boxid;
    }

    public void setBoxid(String str) {
        this.boxid = str;
    }

    public long getBoxer1() {
        return this.boxer1;
    }

    public void setBoxer1(long j) {
        this.boxer1 = j;
    }

    public long getBoxer2() {
        return this.boxer2;
    }

    public void setBoxer2(long j) {
        this.boxer2 = j;
    }

    public String toString() {
        return "BoxingBoxerVotes [boxid=" + this.boxid + ", boxer1=" + this.boxer1 + ", boxer2=" + this.boxer2 + "]";
    }
}
