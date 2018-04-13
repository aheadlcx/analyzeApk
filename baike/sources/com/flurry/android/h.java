package com.flurry.android;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

final class h extends LinkedHashMap {
    private /* synthetic */ af a;

    h(af afVar, int i, float f) {
        this.a = afVar;
        super(i, f, true);
    }

    protected final boolean removeEldestEntry(Entry entry) {
        return size() > this.a.b;
    }
}
