package android.arch.core.internal;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.WeakHashMap;

@RestrictTo({Scope.LIBRARY_GROUP})
public class SafeIterableMap<K, V> implements Iterable<Entry<K, V>> {
    private c<K, V> a;
    private c<K, V> b;
    private WeakHashMap<f<K, V>, Boolean> c = new WeakHashMap();
    private int d = 0;

    interface f<K, V> {
        void supportRemove(@NonNull c<K, V> cVar);
    }

    private static abstract class e<K, V> implements f<K, V>, Iterator<Entry<K, V>> {
        c<K, V> a;
        c<K, V> b;

        abstract c<K, V> a(c<K, V> cVar);

        abstract c<K, V> b(c<K, V> cVar);

        e(c<K, V> cVar, c<K, V> cVar2) {
            this.a = cVar2;
            this.b = cVar;
        }

        public boolean hasNext() {
            return this.b != null;
        }

        public void supportRemove(@NonNull c<K, V> cVar) {
            if (this.a == cVar && cVar == this.b) {
                this.b = null;
                this.a = null;
            }
            if (this.a == cVar) {
                this.a = b(this.a);
            }
            if (this.b == cVar) {
                this.b = a();
            }
        }

        private c<K, V> a() {
            if (this.b == this.a || this.a == null) {
                return null;
            }
            return a(this.b);
        }

        public Entry<K, V> next() {
            Entry entry = this.b;
            this.b = a();
            return entry;
        }
    }

    static class a<K, V> extends e<K, V> {
        a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        c<K, V> a(c<K, V> cVar) {
            return cVar.c;
        }

        c<K, V> b(c<K, V> cVar) {
            return cVar.d;
        }
    }

    private static class b<K, V> extends e<K, V> {
        b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        c<K, V> a(c<K, V> cVar) {
            return cVar.d;
        }

        c<K, V> b(c<K, V> cVar) {
            return cVar.c;
        }
    }

    static class c<K, V> implements Entry<K, V> {
        @NonNull
        final K a;
        @NonNull
        final V b;
        c<K, V> c;
        c<K, V> d;

        c(@NonNull K k, @NonNull V v) {
            this.a = k;
            this.b = v;
        }

        @NonNull
        public K getKey() {
            return this.a;
        }

        @NonNull
        public V getValue() {
            return this.b;
        }

        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.a + "=" + this.b;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            if (this.a.equals(cVar.a) && this.b.equals(cVar.b)) {
                return true;
            }
            return false;
        }
    }

    private class d implements f<K, V>, Iterator<Entry<K, V>> {
        final /* synthetic */ SafeIterableMap a;
        private c<K, V> b;
        private boolean c;

        private d(SafeIterableMap safeIterableMap) {
            this.a = safeIterableMap;
            this.c = true;
        }

        public void supportRemove(@NonNull c<K, V> cVar) {
            if (cVar == this.b) {
                this.b = this.b.d;
                this.c = this.b == null;
            }
        }

        public boolean hasNext() {
            if (this.c) {
                if (this.a.a != null) {
                    return true;
                }
                return false;
            } else if (this.b == null || this.b.c == null) {
                return false;
            } else {
                return true;
            }
        }

        public Entry<K, V> next() {
            if (this.c) {
                this.c = false;
                this.b = this.a.a;
            } else {
                this.b = this.b != null ? this.b.c : null;
            }
            return this.b;
        }
    }

    protected c<K, V> a(K k) {
        c<K, V> cVar = this.a;
        while (cVar != null && !cVar.a.equals(k)) {
            cVar = cVar.c;
        }
        return cVar;
    }

    public V putIfAbsent(@NonNull K k, @NonNull V v) {
        c a = a((Object) k);
        if (a != null) {
            return a.b;
        }
        a(k, v);
        return null;
    }

    protected c<K, V> a(@NonNull K k, @NonNull V v) {
        c<K, V> cVar = new c(k, v);
        this.d++;
        if (this.b == null) {
            this.a = cVar;
            this.b = this.a;
        } else {
            this.b.c = cVar;
            cVar.d = this.b;
            this.b = cVar;
        }
        return cVar;
    }

    public V remove(@NonNull K k) {
        c a = a((Object) k);
        if (a == null) {
            return null;
        }
        this.d--;
        if (!this.c.isEmpty()) {
            for (f supportRemove : this.c.keySet()) {
                supportRemove.supportRemove(a);
            }
        }
        if (a.d != null) {
            a.d.c = a.c;
        } else {
            this.a = a.c;
        }
        if (a.c != null) {
            a.c.d = a.d;
        } else {
            this.b = a.d;
        }
        a.c = null;
        a.d = null;
        return a.b;
    }

    public int size() {
        return this.d;
    }

    @NonNull
    public Iterator<Entry<K, V>> iterator() {
        Iterator aVar = new a(this.a, this.b);
        this.c.put(aVar, Boolean.valueOf(false));
        return aVar;
    }

    public Iterator<Entry<K, V>> descendingIterator() {
        Iterator bVar = new b(this.b, this.a);
        this.c.put(bVar, Boolean.valueOf(false));
        return bVar;
    }

    public d iteratorWithAdditions() {
        d dVar = new d();
        this.c.put(dVar, Boolean.valueOf(false));
        return dVar;
    }

    public Entry<K, V> eldest() {
        return this.a;
    }

    public Entry<K, V> newest() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SafeIterableMap)) {
            return false;
        }
        SafeIterableMap safeIterableMap = (SafeIterableMap) obj;
        if (size() != safeIterableMap.size()) {
            return false;
        }
        Iterator it = iterator();
        Iterator it2 = safeIterableMap.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Entry entry = (Entry) it.next();
            Object next = it2.next();
            if (entry == null && next != null) {
                return false;
            }
            if (entry != null && !entry.equals(next)) {
                return false;
            }
        }
        boolean z = (it.hasNext() || it2.hasNext()) ? false : true;
        return z;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        Iterator it = iterator();
        while (it.hasNext()) {
            stringBuilder.append(((Entry) it.next()).toString());
            if (it.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
