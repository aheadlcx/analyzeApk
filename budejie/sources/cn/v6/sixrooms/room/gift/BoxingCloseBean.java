package cn.v6.sixrooms.room.gift;

import cn.v6.sixrooms.bean.MessageBean;

public class BoxingCloseBean extends MessageBean {
    private String boxid;

    public String getBoxid() {
        return this.boxid;
    }

    public void setBoxid(String str) {
        this.boxid = str;
    }

    public String toString() {
        return "BoxingCloseBean [boxid=" + this.boxid + "]";
    }
}
