package cn.v6.sixrooms.room.bean;

import android.text.TextUtils;

public class ImMessageUnreadBean implements Comparable<ImMessageUnreadBean> {
    private String alias;
    private String gpic;
    private String grouprank;
    private String lasttm;
    private int login;
    private int mid;
    private int num;
    private String rank;
    private String rid;
    private long senduid;
    private String tm;
    private int type;
    private long uid;
    private String userpic;

    public long getSenduid() {
        return this.senduid;
    }

    public void setSenduid(long j) {
        this.senduid = j;
    }

    public String getLasttm() {
        return this.lasttm;
    }

    public long getLasttmInLong() {
        return TextUtils.isEmpty(this.lasttm) ? 0 : Long.parseLong(this.lasttm);
    }

    public void setLasttm(String str) {
        this.lasttm = str;
    }

    public String getRank() {
        return this.rank;
    }

    public void setRank(String str) {
        this.rank = str;
    }

    public int getMid() {
        return this.mid;
    }

    public void setMid(int i) {
        this.mid = i;
    }

    public String getTm() {
        return this.tm;
    }

    public long getTmLong() {
        return TextUtils.isEmpty(this.tm) ? 0 : Long.parseLong(this.tm);
    }

    public void setTm(String str) {
        this.tm = str;
    }

    public void setTmLong(long j) {
        this.tm = String.valueOf(j);
    }

    public long getUid() {
        return this.uid;
    }

    public void setUid(long j) {
        this.uid = j;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public String getRid() {
        return this.rid;
    }

    public void setRid(String str) {
        this.rid = str;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int i) {
        this.num = i;
    }

    public String getUserpic() {
        return this.userpic;
    }

    public void setUserpic(String str) {
        this.userpic = str;
    }

    public int getLogin() {
        return this.login;
    }

    public void setLogin(int i) {
        this.login = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getGrouprank() {
        return this.grouprank;
    }

    public void setGrouprank(String str) {
        this.grouprank = str;
    }

    public String getGpic() {
        return this.gpic;
    }

    public void setGpic(String str) {
        this.gpic = str;
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int compareTo(ImMessageUnreadBean imMessageUnreadBean) {
        long lasttmInLong = imMessageUnreadBean.getLasttmInLong();
        long lasttmInLong2 = getLasttmInLong();
        if (lasttmInLong - lasttmInLong2 > 0) {
            return 1;
        }
        return lasttmInLong == lasttmInLong2 ? 0 : -1;
    }
}
