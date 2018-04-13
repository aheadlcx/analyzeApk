package com.qiushibaike.statsdk;

import java.util.ArrayList;
import java.util.List;

public class SessionObj {
    List<PageObj> a = new ArrayList();
    private long b;
    private long c;
    private int d;

    public void reset() {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.a.clear();
        setStart(System.currentTimeMillis());
    }

    public void setStart(long j) {
        this.b = j;
    }

    public long getStart() {
        return this.b;
    }

    public void setCount(int i) {
        this.d = i;
    }

    public int getCount() {
        return this.d;
    }

    public long getEnd() {
        return this.c;
    }

    public void setEnd(long j) {
        this.c = j;
    }

    public void onPageView(String str, long j, long j2) {
        this.a.add(new PageObj(str, j, j2));
    }

    public List<a> getEvents() {
        List<a> arrayList = new ArrayList(this.a.size());
        for (PageObj a : this.a) {
            arrayList.add(PageObj.a(a));
        }
        return arrayList;
    }
}
