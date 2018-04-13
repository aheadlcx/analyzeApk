package com.budejie.www.bean;

import java.util.ArrayList;

public class MyCollectItem {
    private ArrayList<ListItemObject> addList;
    private ArrayList<ListItemObject> delList;
    private String version;

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public ArrayList<ListItemObject> getAddList() {
        return this.addList;
    }

    public void setAddList(ArrayList<ListItemObject> arrayList) {
        this.addList = arrayList;
    }

    public ArrayList<ListItemObject> getDelList() {
        return this.delList;
    }

    public void setDelList(ArrayList<ListItemObject> arrayList) {
        this.delList = arrayList;
    }
}
