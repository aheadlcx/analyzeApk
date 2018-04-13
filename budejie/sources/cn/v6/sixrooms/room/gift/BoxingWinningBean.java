package cn.v6.sixrooms.room.gift;

import cn.v6.sixrooms.bean.MessageBean;

public class BoxingWinningBean extends MessageBean {
    private String msg;
    private String trid;
    private String tuid;
    private String url;

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getTrid() {
        return this.trid;
    }

    public void setTrid(String str) {
        this.trid = str;
    }

    public String getTuid() {
        return this.tuid;
    }

    public void setTuid(String str) {
        this.tuid = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String toString() {
        return "BoxingWinningBean [msg=" + this.msg + ", trid=" + this.trid + ", tuid=" + this.tuid + ", url=" + this.url + "]";
    }
}
