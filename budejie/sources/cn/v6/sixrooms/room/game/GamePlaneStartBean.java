package cn.v6.sixrooms.room.game;

import cn.v6.sixrooms.bean.MessageBean;

public class GamePlaneStartBean extends MessageBean {
    private static final long serialVersionUID = 9118129592272569844L;
    private String flag;
    private String gameid;
    private String rid;

    public GamePlaneStartBean(String str, String str2, String str3) {
        this.rid = str;
        this.gameid = str2;
        this.flag = str3;
    }

    public String getRid() {
        return this.rid;
    }

    public void setRid(String str) {
        this.rid = str;
    }

    public String getGameid() {
        return this.gameid;
    }

    public void setGameid(String str) {
        this.gameid = str;
    }

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String str) {
        this.flag = str;
    }
}
