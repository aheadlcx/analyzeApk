package com.bumptech.glide.load.engine.a;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class e<K extends h, V> {
    private final a<K, V> a = new a();
    private final Map<K, a<K, V>> b = new HashMap();

    private static class a<K, V> {
        a<K, V> a;
        a<K, V> b;
        private final K c;
        private List<V> d;

        public a() {
            this(null);
        }

        public a(K k) {
            this.b = this;
            this.a = this;
            this.c = k;
        }

        public V a() {
            int b = b();
            return b > 0 ? this.d.remove(b - 1) : null;
        }

        public int b() {
            return this.d != null ? this.d.size() : 0;
        }

        public void a(V v) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            this.d.add(v);
        }
    }

    e() {
    }

    public void a(K k, V v) {
        a aVar = (a) this.b.get(k);
        if (aVar == null) {
            aVar = new a(k);
            b(aVar);
            this.b.put(k, aVar);
        } else {
            k.a();
        }
        aVar.a((Object) v);
    }

    public V a(K k) {
        a aVar = (a) this.b.get(k);
        if (aVar == null) {
            aVar = new a(k);
            this.b.put(k, aVar);
        } else {
            k.a();
        }
        a(aVar);
        return aVar.a();
    }

    public V a() {
        for (a aVar = this.a.b; !aVar.equals(this.a); aVar = aVar.b) {
            V a = aVar.a();
            if (a != null) {
                return a;
            }
            d(aVar);
            this.b.remove(aVar.c);
            ((h) aVar.c).a();
        }
        return null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("GroupedLinkedMap( ");
        Object obj = null;
        for (a aVar = this.a.a; !aVar.equals(this.a); aVar = aVar.a) {
            obj = 1;
            stringBuilder.append('{').append(aVar.c).append(':').append(aVar.b()).append("}, ");
        }
        if (obj != null) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        return stringBuilder.append(" )").toString();
    }

    private void a(a<K, V> aVar) {
        d(aVar);
        aVar.b = this.a;
        aVar.a = this.a.a;
        c(aVar);
    }

    private void b(a<K, V> aVar) {
        d(aVar);
        aVar.b = this.a.b;
        aVar.a = this.a;
        c(aVar);
    }

    private static <K, V> void c(a<K, V> aVar) {
        aVar.a.b = aVar;
        aVar.b.a = aVar;
    }

    private static <K, V> void d(a<K, V> aVar) {
        aVar.b.a = aVar.a;
        aVar.a.b = aVar.b;
    }
}
