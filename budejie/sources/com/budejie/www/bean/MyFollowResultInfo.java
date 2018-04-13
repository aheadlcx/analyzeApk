package com.budejie.www.bean;

import java.util.ArrayList;
import java.util.List;

public class MyFollowResultInfo {
    public ListInfo info;
    public List<MyFollowItem> list;

    public ArrayList<Fans> getFans() {
        if (this.list == null) {
            return null;
        }
        ArrayList<Fans> arrayList = new ArrayList();
        for (MyFollowItem fans : this.list) {
            arrayList.add(new Fans(fans));
        }
        return arrayList;
    }
}
