package com.budejie.www.bean;

import java.util.ArrayList;

public class DingMeData {
    private ListInfo info;
    private ArrayList<DingNewsItem> list;

    public ArrayList<DingNewsItem> getList() {
        return this.list;
    }

    public void setList(ArrayList<DingNewsItem> arrayList) {
        this.list = arrayList;
    }

    public ListInfo getInfo() {
        return this.info;
    }

    public void setInfo(ListInfo listInfo) {
        this.info = listInfo;
    }
}
