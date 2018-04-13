package com.budejie.www.bean;

import java.util.List;

public class HeadPortraitData {
    private int count;
    private List<HeadPortraitItem> lists;
    private String pid;

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(String str) {
        this.pid = str;
    }

    public List<HeadPortraitItem> getLists() {
        return this.lists;
    }

    public void setLists(List<HeadPortraitItem> list) {
        this.lists = list;
    }
}
