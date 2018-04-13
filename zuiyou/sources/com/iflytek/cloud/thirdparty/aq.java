package com.iflytek.cloud.thirdparty;

public class aq extends Exception {
    private static final long serialVersionUID = 705579169594933666L;
    private int a;
    private String b;

    public aq(int i, String str) {
        super(str);
        this.a = i;
        this.b = str;
    }

    public int a() {
        return this.a;
    }
}
