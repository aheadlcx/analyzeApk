package com.facebook.imagepipeline.c;

import com.android.internal.util.Predicate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class g<K, V> {
    private final ac<V> a;
    @GuardedBy
    private final LinkedHashMap<K, V> b = new LinkedHashMap();
    @GuardedBy
    private int c = 0;

    public g(ac<V> acVar) {
        this.a = acVar;
    }

    public synchronized int a() {
        return this.b.size();
    }

    public synchronized int b() {
        return this.c;
    }

    @Nullable
    public synchronized K c() {
        return this.b.isEmpty() ? null : this.b.keySet().iterator().next();
    }

    @Nullable
    public synchronized V a(K k) {
        return this.b.get(k);
    }

    @Nullable
    public synchronized V a(K k, V v) {
        V remove;
        remove = this.b.remove(k);
        this.c -= c(remove);
        this.b.put(k, v);
        this.c += c(v);
        return remove;
    }

    @Nullable
    public synchronized V b(K k) {
        V remove;
        remove = this.b.remove(k);
        this.c -= c(remove);
        return remove;
    }

    public synchronized ArrayList<V> a(@Nullable Predicate<K> predicate) {
        ArrayList<V> arrayList;
        arrayList = new ArrayList();
        Iterator it = this.b.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (predicate == null || predicate.apply(entry.getKey())) {
                arrayList.add(entry.getValue());
                this.c -= c(entry.getValue());
                it.remove();
            }
        }
        return arrayList;
    }

    private int c(V v) {
        return v == null ? 0 : this.a.a(v);
    }
}
