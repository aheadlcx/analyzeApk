package com.iflytek.cloud.thirdparty;

public class n extends Exception {
    private static final long serialVersionUID = 2569308067518897199L;
    private String a = "";
    private int b = 0;

    public n(int i, String str) {
        this.b = i;
        this.a = str;
    }

    public String a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }
}
