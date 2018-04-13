package com.facebook.imagepipeline.animated.impl;

import com.facebook.imagepipeline.animated.a.a;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
class h {
    private final boolean[] a;

    h(int i) {
        this.a = new boolean[i];
    }

    boolean a(int i) {
        return this.a[i];
    }

    void a(boolean z) {
        for (int i = 0; i < this.a.length; i++) {
            this.a[i] = z;
        }
    }

    void a(int i, int i2) {
        for (int i3 = 0; i3 < this.a.length; i3++) {
            if (a.a(i, i2, i3)) {
                this.a[i3] = false;
            }
        }
    }

    void a(int i, boolean z) {
        this.a[i] = z;
    }
}
