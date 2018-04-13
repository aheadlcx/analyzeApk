package cn.v6.sixrooms.room.gift;

import cn.v6.sixrooms.bean.MessageBean;

public class BoxingVoteBean extends MessageBean {
    public static final String BOXING_VOTE_STATE_CLOSE = "close";
    public static final String BOXING_VOTE_STATE_OPEN = "open";
    private String boxid;
    private String flag;

    public String getBoxid() {
        return this.boxid;
    }

    public void setBoxid(String str) {
        this.boxid = str;
    }

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String str) {
        this.flag = str;
    }

    public String toString() {
        return "BoxingVoteBean [boxid=" + this.boxid + ", flag=" + this.flag + "]";
    }
}
