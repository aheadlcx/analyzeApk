package cn.v6.sixrooms.room.gift;

import cn.v6.sixrooms.bean.MessageBean;

public class BoxingBean extends MessageBean {
    private BoxingBoxer boxer1;
    private BoxingBoxer boxer2;
    private String boxid;
    private int isVote = -1;
    private int state;
    private String title;
    private int win;

    public int getIsVote() {
        return this.isVote;
    }

    public void setIsVote(int i) {
        this.isVote = i;
    }

    public String getBoxid() {
        return this.boxid;
    }

    public void setBoxid(String str) {
        this.boxid = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public BoxingBoxer getBoxer1() {
        return this.boxer1;
    }

    public void setBoxer1(BoxingBoxer boxingBoxer) {
        this.boxer1 = boxingBoxer;
    }

    public BoxingBoxer getBoxer2() {
        return this.boxer2;
    }

    public void setBoxer2(BoxingBoxer boxingBoxer) {
        this.boxer2 = boxingBoxer;
    }

    public int getWin() {
        return this.win;
    }

    public void setWin(int i) {
        this.win = i;
    }

    public String toString() {
        return "BoxingBean [boxid=" + this.boxid + ", title=" + this.title + ", state=" + this.state + ", boxer1=" + this.boxer1 + ", boxer2=" + this.boxer2 + ", win=" + this.win + ", isVote=" + this.isVote + "]";
    }
}
