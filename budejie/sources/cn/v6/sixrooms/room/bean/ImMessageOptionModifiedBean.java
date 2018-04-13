package cn.v6.sixrooms.room.bean;

public class ImMessageOptionModifiedBean {
    private String friend;
    private String group;
    private String hid;
    private String msg;
    private String sound;
    private String sysmsg;

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getFriend() {
        return this.friend;
    }

    public void setFriend(String str) {
        this.friend = str;
    }

    public String getHid() {
        return this.hid;
    }

    public void setHid(String str) {
        this.hid = str;
    }

    public String getSysmsg() {
        return this.sysmsg;
    }

    public void setSysmsg(String str) {
        this.sysmsg = str;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String str) {
        this.group = str;
    }

    public String getSound() {
        return this.sound;
    }

    public void setSound(String str) {
        this.sound = str;
    }
}
