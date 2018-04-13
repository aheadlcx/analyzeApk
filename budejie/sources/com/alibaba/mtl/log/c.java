package com.alibaba.mtl.log;

import java.util.Map;

public class c {
    public static final c a = new c();
    private String H = null;
    private String I = null;
    private String J = null;
    private String K = null;
    private Map<String, String> r = null;
    private boolean u = false;
    private boolean v = false;
    private boolean w = false;
    private boolean x = false;
    private boolean y = false;

    public static c a() {
        return a;
    }

    public synchronized void e(String str) {
        this.I = str;
    }

    public synchronized void p() {
        this.x = true;
    }

    public synchronized boolean f() {
        return this.x;
    }

    public synchronized void c(Map<String, String> map) {
        this.r = map;
    }

    /* renamed from: a */
    public synchronized Map<String, String> m23a() {
        return this.r;
    }
}
