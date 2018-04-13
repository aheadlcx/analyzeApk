package com.facebook.imagepipeline.c;

import com.android.internal.util.Predicate;
import com.facebook.common.references.a;
import javax.annotation.Nullable;

public interface t<K, V> {
    int a(Predicate<K> predicate);

    @Nullable
    a<V> a(K k);

    @Nullable
    a<V> a(K k, a<V> aVar);
}
