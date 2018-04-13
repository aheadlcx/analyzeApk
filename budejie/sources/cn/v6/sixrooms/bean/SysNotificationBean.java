package cn.v6.sixrooms.bean;

public class SysNotificationBean extends MessageBean {
    private static final long serialVersionUID = 1;
    private String content;
    private String from;
    private int rank;
    private boolean rankFlag;
    private String to;

    public boolean isRankFlag() {
        return this.rankFlag;
    }

    public void setRankFlag(boolean z) {
        this.rankFlag = z;
    }

    public int getRank() {
        return this.rank;
    }

    public void setRank(int i) {
        this.rank = i;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String str) {
        this.to = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String toString() {
        return "BroadcastBean [from=" + this.from + ", to=" + this.to + ", content=" + this.content + "]";
    }
}
