package cn.v6.sixrooms.bean;

public class VoteBean extends MessageBean {
    private static final long serialVersionUID = 1;
    private int giftnum;
    private int gifttop;
    private int votenum;
    private int votetop;

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
}
