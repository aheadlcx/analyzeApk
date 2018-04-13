package cn.v6.sixrooms.room.bean;

import java.util.List;

public class ResponseLoginBean {
    private int frtm;
    private int grtm;
    private ImMessageOptionModifiedBean iminfo;
    private List<ImMessageUnreadBean> msglist;
    private int msgnum;
    private List notip;
    private int rnum;
    private int usernum;

    public int getFrtm() {
        return this.frtm;
    }

    public void setFrtm(int i) {
        this.frtm = i;
    }

    public int getGrtm() {
        return this.grtm;
    }

    public void setGrtm(int i) {
        this.grtm = i;
    }

    public int getRnum() {
        return this.rnum;
    }

    public void setRnum(int i) {
        this.rnum = i;
    }

    public ImMessageOptionModifiedBean getIminfo() {
        return this.iminfo;
    }

    public void setIminfo(ImMessageOptionModifiedBean imMessageOptionModifiedBean) {
        this.iminfo = imMessageOptionModifiedBean;
    }

    public int getMsgnum() {
        return this.msgnum;
    }

    public void setMsgnum(int i) {
        this.msgnum = i;
    }

    public List<ImMessageUnreadBean> getMsglist() {
        return this.msglist;
    }

    public void setMsglist(List<ImMessageUnreadBean> list) {
        this.msglist = list;
    }

    public int getUsernum() {
        return this.usernum;
    }

    public void setUsernum(int i) {
        this.usernum = i;
    }

    public List getNotip() {
        return this.notip;
    }

    public void setNotip(List list) {
        this.notip = list;
    }
}
