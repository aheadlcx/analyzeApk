package com.qiushibaike.statsdk;

public class PageObj {
    private String a;
    private long b;
    private long c;

    private PageObj() {
    }

    PageObj(String str, long j, long j2) {
        this.a = str;
        this.b = j;
        this.c = j2;
    }

    static a a(PageObj pageObj) {
        return new a(Constant.INNER_EVENT_ID, pageObj.a, 1, pageObj.b, pageObj.c);
    }
}
