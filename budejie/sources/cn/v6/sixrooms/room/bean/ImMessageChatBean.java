package cn.v6.sixrooms.room.bean;

import android.text.TextUtils;

public class ImMessageChatBean implements Comparable<ImMessageChatBean> {
    private String alias;
    private int breakPoint;
    private String cmid;
    private int getFrom;
    private int height;
    private boolean isPlaying;
    private long mid;
    private String msg;
    private int old;
    private String pic;
    private String picuser;
    private long receiveUid;
    private int second;
    private int sendStatus = 0;
    private long sendTm;
    private String tm;
    private int type;
    private long uid;
    private String url;
    private String voice;
    private int width;

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public void setPlaying(boolean z) {
        this.isPlaying = z;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getGetFrom() {
        return this.getFrom;
    }

    public void setGetFrom(int i) {
        this.getFrom = i;
    }

    public int getBreakPoint() {
        return this.breakPoint;
    }

    public void setBreakPoint(int i) {
        this.breakPoint = i;
    }

    public long getReceiveUid() {
        return this.receiveUid;
    }

    public void setReceiveUid(long j) {
        this.receiveUid = j;
    }

    public long getMid() {
        return this.mid;
    }

    public void setMid(long j) {
        this.mid = j;
    }

    public int getSecond() {
        return this.second;
    }

    public void setSecond(int i) {
        this.second = i;
    }

    public int getSendStatus() {
        return this.sendStatus;
    }

    public void setSendStatus(int i) {
        this.sendStatus = i;
    }

    public String getVoice() {
        return this.voice;
    }

    public void setVoice(String str) {
        this.voice = str;
    }

    public String getPicuser() {
        return this.picuser;
    }

    public void setPicuser(String str) {
        this.picuser = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getCmid() {
        return this.cmid;
    }

    public void setCmid(String str) {
        this.cmid = str;
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

    public String getTm() {
        return this.tm;
    }

    public void setTm(String str) {
        this.tm = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public int getOld() {
        return this.old;
    }

    public void setOld(int i) {
        this.old = i;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String str) {
        this.pic = str;
    }

    public long getSendTm() {
        return this.sendTm;
    }

    public void setSendTm(long j) {
        this.sendTm = j;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getOriginalPicUrl() {
        if (TextUtils.isEmpty(this.pic)) {
            return this.pic;
        }
        if (!this.pic.endsWith("_s.jpg")) {
            return this.pic;
        }
        String str = this.pic;
        try {
            return this.pic.substring(0, this.pic.length() - 6) + ".jpg";
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return str;
        }
    }

    public boolean equals(Object obj) {
        ImMessageChatBean imMessageChatBean = (ImMessageChatBean) obj;
        long mid = imMessageChatBean.getMid();
        if (imMessageChatBean.getUid() != this.uid) {
            return false;
        }
        if (this.tm.equals(imMessageChatBean.getTm()) && mid == this.mid) {
            return true;
        }
        return false;
    }

    public int compareTo(ImMessageChatBean imMessageChatBean) {
        long parseLong = Long.parseLong(this.tm);
        long parseLong2 = Long.parseLong(imMessageChatBean.getTm());
        if (parseLong > parseLong2) {
            return 1;
        }
        if (parseLong != parseLong2) {
            return -1;
        }
        if (this.mid <= imMessageChatBean.getMid()) {
            return this.mid == imMessageChatBean.getMid() ? 0 : -1;
        } else {
            return 1;
        }
    }

    public String toString() {
        return "ImMessageChatBean [uid=" + this.uid + ", alias=" + this.alias + ", tm=" + this.tm + ", msg=" + this.msg + ", old=" + this.old + ", cmid=" + this.cmid + ", pic=" + this.pic + ", voice=" + this.voice + ", width=" + this.width + ", height=" + this.height + ", mid=" + this.mid + ", second=" + this.second + ", picuser=" + this.picuser + ", type=" + this.type + ", sendStatus=" + this.sendStatus + ", receiveUid=" + this.receiveUid + ", getFrom=" + this.getFrom + ", breakPoint=" + this.breakPoint + ", sendTm=" + this.sendTm + ", isPlaying=" + this.isPlaying + "]";
    }
}
