package com.facebook.common.memory;

import com.facebook.common.references.c;

public interface e<V> extends b, c<V> {
    V get(int i);

    void release(V v);
}
