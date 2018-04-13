package com.budejie.www.bean;

import java.util.List;

public class MyMsgListData {
    private int count;
    private List<MyMsgItem> lists;
    private int maxtime;
    private int userid;

    public int getMaxtime() {
        return this.maxtime;
    }

    public void setMaxtime(int i) {
        this.maxtime = i;
    }

    public int getUserid() {
        return this.userid;
    }

    public void setUserid(int i) {
        this.userid = i;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public List<MyMsgItem> getLists() {
        return this.lists;
    }

    public void setLists(List<MyMsgItem> list) {
        this.lists = list;
    }
}
