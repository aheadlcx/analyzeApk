package com.airbnb.lottie;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

abstract class m<V, O> implements k<V, O> {
    final List<af<V>> a;
    final ai b;
    final V c;

    m(ai aiVar, V v) {
        this(Collections.emptyList(), aiVar, v);
    }

    m(List<af<V>> list, ai aiVar, V v) {
        this.a = list;
        this.b = aiVar;
        this.c = v;
    }

    O a(V v) {
        return v;
    }

    public boolean e() {
        return !this.a.isEmpty();
    }

    public O d() {
        return a(this.c);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("parseInitialValue=").append(this.c);
        if (!this.a.isEmpty()) {
            stringBuilder.append(", values=").append(Arrays.toString(this.a.toArray()));
        }
        return stringBuilder.toString();
    }
}
