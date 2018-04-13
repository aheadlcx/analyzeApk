package com.alibaba.mtl.appmonitor.a;

import com.alibaba.mtl.appmonitor.c.b;
import java.util.HashMap;
import java.util.Map;

public class h implements b {
    public int e;
    public Map<String, String> k;
    public String u;
    public String v;
    public String w;
    public String x;

    public void clean() {
        this.u = null;
        this.e = 0;
        this.v = null;
        this.w = null;
        this.x = null;
        if (this.k != null) {
            this.k.clear();
        }
    }

    public void fill(Object... objArr) {
        if (this.k == null) {
            this.k = new HashMap();
        }
    }
}
