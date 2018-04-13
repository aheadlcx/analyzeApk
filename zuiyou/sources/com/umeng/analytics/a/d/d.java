package com.umeng.analytics.a.d;

import android.text.TextUtils;
import com.umeng.analytics.a.b.a;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class d implements Serializable {
    private static final long a = 1;
    private Map<String, com.umeng.analytics.a.c.d> b = new HashMap();

    public void a(a aVar, String str) {
        if (this.b.containsKey(str)) {
            c(str);
        } else {
            b(str);
        }
        aVar.a(this, false);
    }

    public Map<String, com.umeng.analytics.a.c.d> a() {
        return this.b;
    }

    public void b() {
        this.b.clear();
    }

    public void a(Map<String, com.umeng.analytics.a.c.d> map) {
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
        this.b.put(str, new com.umeng.analytics.a.c.d(str, System.currentTimeMillis(), 1));
    }

    private void c(String str) {
        this.b.put(str, ((com.umeng.analytics.a.c.d) this.b.get(str)).a());
    }

    public void a(com.umeng.analytics.a.c.d dVar) {
        if (a(dVar.c())) {
            b(dVar);
        } else {
            this.b.put(dVar.c(), dVar);
        }
    }

    private void b(com.umeng.analytics.a.c.d dVar) {
        this.b.put(dVar.c(), ((com.umeng.analytics.a.c.d) this.b.get(dVar.c())).a(dVar));
    }
}
