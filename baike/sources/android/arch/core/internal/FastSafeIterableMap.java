package android.arch.core.internal;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.HashMap;
import java.util.Map.Entry;

@RestrictTo({Scope.LIBRARY_GROUP})
public class FastSafeIterableMap<K, V> extends SafeIterableMap<K, V> {
    private HashMap<K, c<K, V>> a = new HashMap();

    protected c<K, V> a(K k) {
        return (c) this.a.get(k);
    }

    public V putIfAbsent(@NonNull K k, @NonNull V v) {
        c a = a(k);
        if (a != null) {
            return a.b;
        }
        this.a.put(k, a(k, v));
        return null;
    }

    public V remove(@NonNull K k) {
        V remove = super.remove(k);
        this.a.remove(k);
        return remove;
    }

    public boolean contains(K k) {
        return this.a.containsKey(k);
    }

    public Entry<K, V> ceil(K k) {
        if (contains(k)) {
            return ((c) this.a.get(k)).d;
        }
        return null;
    }
}
