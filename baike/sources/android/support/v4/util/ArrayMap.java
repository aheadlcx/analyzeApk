package android.support.v4.util;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ArrayMap<K, V> extends SimpleArrayMap<K, V> implements Map<K, V> {
    d<K, V> a;

    public ArrayMap(int i) {
        super(i);
    }

    public ArrayMap(SimpleArrayMap simpleArrayMap) {
        super(simpleArrayMap);
    }

    private d<K, V> b() {
        if (this.a == null) {
            this.a = new a(this);
        }
        return this.a;
    }

    public boolean containsAll(Collection<?> collection) {
        return d.containsAllHelper(this, collection);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        ensureCapacity(this.h + map.size());
        for (Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public boolean removeAll(Collection<?> collection) {
        return d.removeAllHelper(this, collection);
    }

    public boolean retainAll(Collection<?> collection) {
        return d.retainAllHelper(this, collection);
    }

    public Set<Entry<K, V>> entrySet() {
        return b().getEntrySet();
    }

    public Set<K> keySet() {
        return b().getKeySet();
    }

    public Collection<V> values() {
        return b().getValues();
    }
}
