package com.budejie.www.bean;

import java.util.ArrayList;

public class SyncCollectData {
    private ArrayList<ListItemObject> dataList;
    private ArrayList<String> invalidList;

    public ArrayList<String> getInvalidList() {
        return this.invalidList;
    }

    public void setInvalidList(ArrayList<String> arrayList) {
        this.invalidList = arrayList;
    }

    public ArrayList<ListItemObject> getDataList() {
        return this.dataList;
    }

    public void setDataList(ArrayList<ListItemObject> arrayList) {
        this.dataList = arrayList;
    }
}
