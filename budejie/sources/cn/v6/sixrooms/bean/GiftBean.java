package cn.v6.sixrooms.bean;

public class GiftBean extends MessageBean {
    protected static final long serialVersionUID = 1;
    protected int fid;
    protected int frid;
    protected String from;
    protected boolean isSystem;
    protected int item;
    protected String msg;
    protected int num;
    protected int tid;
    protected String to;
    protected int trid;

    public int getFid() {
        return this.fid;
    }

    public void setFid(int i) {
        this.fid = i;
    }

    public int getFrid() {
        return this.frid;
    }

    public void setFrid(int i) {
        this.frid = i;
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

    public int getTid() {
        return this.tid;
    }

    public void setTid(int i) {
        this.tid = i;
    }

    public int getTrid() {
        return this.trid;
    }

    public void setTrid(int i) {
        this.trid = i;
    }

    public int getItem() {
        return this.item;
    }

    public void setItem(int i) {
        this.item = i;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int i) {
        this.num = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String toString() {
        return "GiftBean [fid=" + this.fid + ", frid=" + this.frid + ", from=" + this.from + ", to=" + this.to + ", tid=" + this.tid + ", trid=" + this.trid + ", item=" + this.item + ", num=" + this.num + ", msg=" + this.msg + "]";
    }

    public boolean isSystem() {
        return this.isSystem;
    }

    public void setSystem(boolean z) {
        this.isSystem = z;
    }
}
