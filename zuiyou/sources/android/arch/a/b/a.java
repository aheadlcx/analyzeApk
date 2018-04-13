package android.arch.a.b;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.HashMap;

@RestrictTo({Scope.LIBRARY_GROUP})
public class a<K, V> extends b<K, V> {
    private HashMap<K, c<K, V>> a = new HashMap();

    protected c<K, V> a(K k) {
        return (c) this.a.get(k);
    }

    public V b(@NonNull K k) {
        V b = super.b(k);
        this.a.remove(k);
        return b;
    }

    public boolean c(K k) {
        return this.a.containsKey(k);
    }
}
