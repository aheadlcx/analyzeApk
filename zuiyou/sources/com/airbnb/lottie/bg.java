package com.airbnb.lottie;

import android.support.annotation.FloatRange;
import java.util.Collections;

class bg<T> extends ag<T> {
    private final T c;

    bg(T t) {
        super(Collections.emptyList());
        this.c = t;
    }

    public void a(@FloatRange(from = 0.0d, to = 1.0d) float f) {
    }

    public T b() {
        return this.c;
    }

    public T a(af<T> afVar, float f) {
        return this.c;
    }
}
