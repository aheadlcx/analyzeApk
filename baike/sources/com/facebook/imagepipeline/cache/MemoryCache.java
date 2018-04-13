package com.facebook.imagepipeline.cache;

import com.android.internal.util.Predicate;
import com.facebook.common.references.CloseableReference;
import javax.annotation.Nullable;

public interface MemoryCache<K, V> {
    @Nullable
    CloseableReference<V> cache(K k, CloseableReference<V> closeableReference);

    boolean contains(Predicate<K> predicate);

    @Nullable
    CloseableReference<V> get(K k);

    int removeAll(Predicate<K> predicate);
}
