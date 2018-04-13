package com.budejie.www.bean;

import java.util.ArrayList;

public class ShenHeItem {
    ArrayList<TouGaoItem> dataList;
    String lastId;

    public ArrayList<TouGaoItem> getDataList() {
        return this.dataList;
    }

    public void setDataList(ArrayList<TouGaoItem> arrayList) {
        this.dataList = arrayList;
    }

    public String getLastId() {
        return this.lastId;
    }

    public void setLastId(String str) {
        this.lastId = str;
    }
}
