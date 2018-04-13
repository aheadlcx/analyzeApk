package com.budejie.www.bean;

import java.util.ArrayList;

public class MyNewsData {
    public ListInfo info;
    private ArrayList<MyNewsItem> list;

    public ArrayList<MyNewsItem> getList() {
        return this.list;
    }

    public void setList(ArrayList<MyNewsItem> arrayList) {
        this.list = arrayList;
    }
}
