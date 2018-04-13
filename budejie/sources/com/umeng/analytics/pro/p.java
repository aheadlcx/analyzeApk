package com.umeng.analytics.pro;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class p implements Serializable {
    private static final long a = 1;
    private Map<String, k> b = new HashMap();

    public void a(f fVar, String str) {
        if (this.b.containsKey(str)) {
            c(str);
        } else {
            b(str);
        }
        fVar.a(this, false);
    }

    public Map<String, k> a() {
        return this.b;
    }

    public void b() {
        this.b.clear();
    }

    public void a(Map<String, k> map) {
        this.b = map;
    }

    public boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (Entry key : this.b.entrySet()) {
            if (((String) key.getKey()).equals(str)) {
                return true;
            }
        }
        return false;
    }

    private void b(String str) {
        this.b.put(str, new k(str, System.currentTimeMillis(), 1));
    }

    private void c(String str) {
        this.b.put(str, ((k) this.b.get(str)).a());
    }

    public void a(k kVar) {
        if (a(kVar.c())) {
            b(kVar);
        } else {
            this.b.put(kVar.c(), kVar);
        }
    }

    private void b(k kVar) {
        this.b.put(kVar.c(), ((k) this.b.get(kVar.c())).a(kVar));
    }
}
