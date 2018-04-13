package com.flurry.android;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

final class af {
    private LinkedHashMap a = new h(this, ((int) Math.ceil((double) (((float) 50) / 0.75f))) + 1, 0.75f);
    private int b = 50;

    af(int i) {
    }

    final synchronized Object a(Object obj) {
        return this.a.get(obj);
    }

    final synchronized void a(Object obj, Object obj2) {
        this.a.put(obj, obj2);
    }

    final synchronized int a() {
        return this.a.size();
    }

    final synchronized List b() {
        return new ArrayList(this.a.entrySet());
    }

    final synchronized Set c() {
        return this.a.keySet();
    }
}
