package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class VoteableBean implements Serializable {
    private static final long serialVersionUID = 3144661830127070951L;
    private String cpid;
    private String favscore;
    private String flag;
    private int giftnum;
    private int gifttop;
    private boolean isAble;
    private int isbalnum;
    private int votenum;
    private int votetop;

    public boolean isAble() {
        return this.isAble;
    }

    public int getVotenum() {
        return this.votenum;
    }

    public void setVotenum(int i) {
        this.votenum = i;
    }

    public int getVotetop() {
        return this.votetop;
    }

    public void setVotetop(int i) {
        this.votetop = i;
    }

    public int getGiftnum() {
        return this.giftnum;
    }

    public void setGiftnum(int i) {
        this.giftnum = i;
    }

    public int getGifttop() {
        return this.gifttop;
    }

    public void setGifttop(int i) {
        this.gifttop = i;
    }

    public String getCpid() {
        return this.cpid;
    }

    public void setCpid(String str) {
        this.cpid = str;
    }

    public int getIsbalnum() {
        return this.isbalnum;
    }

    public void setIsbalnum(int i) {
        this.isbalnum = i;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setAble(boolean z) {
        this.isAble = z;
    }

    public String getFavscore() {
        return this.favscore;
    }

    public void setFavscore(String str) {
        this.favscore = str;
    }

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String str) {
        this.flag = str;
    }

    public String toString() {
        return "VoteableBean [isAble=" + this.isAble + ", flag=" + this.flag + ", favscore=" + this.favscore + "]";
    }
}
