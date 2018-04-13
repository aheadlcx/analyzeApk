package com.alibaba.mtl.appmonitor.c;

import org.json.JSONArray;

public class d extends JSONArray implements b {
    public void clean() {
        for (int i = 0; i < length(); i++) {
            Object opt = opt(i);
            if (opt != null && (opt instanceof b)) {
                a.a().a((b) opt);
            }
        }
    }

    public void fill(Object... objArr) {
    }
}
