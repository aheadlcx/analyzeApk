package com.umeng.analytics.social;

public class c {
    private int a = -1;
    private String b = "";
    private String c = "";
    private Exception d = null;

    public c(int i) {
        this.a = i;
    }

    public c(int i, Exception exception) {
        this.a = i;
        this.d = exception;
    }

    public Exception a() {
        return this.d;
    }

    public int b() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }

    public String c() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String d() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public String toString() {
        return "status=" + this.a + "\r\n" + "msg:  " + this.b + "\r\n" + "data:  " + this.c;
    }
}
