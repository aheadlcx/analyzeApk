package com.alibaba.sdk.android.httpdns;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;

class a {
    private static a a = new a();
    /* renamed from: a */
    private static ConcurrentMap f2a;
    /* renamed from: a */
    private static ConcurrentSkipListSet f3a;

    private a() {
        f2a = new ConcurrentHashMap();
        f3a = new ConcurrentSkipListSet();
    }

    static a a() {
        return a;
    }

    /* renamed from: a */
    int m3a() {
        return f2a.size();
    }

    b a(String str) {
        return (b) f2a.get(str);
    }

    /* renamed from: a */
    ArrayList m4a() {
        return new ArrayList(f2a.keySet());
    }

    /* renamed from: a */
    void m5a(String str) {
        f3a.add(str);
    }

    void a(String str, b bVar) {
        f2a.put(str, bVar);
    }

    /* renamed from: a */
    boolean m6a(String str) {
        return f3a.contains(str);
    }

    void b(String str) {
        f3a.remove(str);
    }

    void clear() {
        f2a.clear();
        f3a.clear();
    }
}
