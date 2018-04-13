package cn.v6.sixrooms.room.gift;

import cn.v6.sixrooms.bean.MessageBean;

public class BoxingWinVictoryBean extends MessageBean {
    public static final int BOXING_WIN_VICTORY_BLUE = 2;
    public static final int BOXING_WIN_VICTORY_RED = 1;
    public static final int BOXING_WIN_VICTORY_TIE = 3;
    private String boxid;
    private int win;

    public String getBoxid() {
        return this.boxid;
    }

    public void setBoxid(String str) {
        this.boxid = str;
    }

    public int getWin() {
        return this.win;
    }

    public void setWin(int i) {
        this.win = i;
    }

    public String toString() {
        return "BoxingWinVictoryBean [boxid=" + this.boxid + ", win=" + this.win + "]";
    }
}
