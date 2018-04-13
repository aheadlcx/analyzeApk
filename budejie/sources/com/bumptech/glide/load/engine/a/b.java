package com.bumptech.glide.load.engine.a;

import com.bumptech.glide.i.h;
import java.util.Queue;

abstract class b<T extends h> {
    private final Queue<T> a = h.a(20);

    protected abstract T b();

    b() {
    }

    protected T c() {
        h hVar = (h) this.a.poll();
        if (hVar == null) {
            return b();
        }
        return hVar;
    }

    public void a(T t) {
        if (this.a.size() < 20) {
            this.a.offer(t);
        }
    }
}
