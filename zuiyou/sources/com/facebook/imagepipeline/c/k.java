package com.facebook.imagepipeline.c;

import com.facebook.common.internal.i;

public class k implements i<u> {
    public /* synthetic */ Object b() {
        return a();
    }

    public u a() {
        int c = c();
        return new u(c, Integer.MAX_VALUE, c, Integer.MAX_VALUE, c / 8);
    }

    private int c() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min < 16777216) {
            return 1048576;
        }
        if (min < 33554432) {
            return 2097152;
        }
        return 4194304;
    }
}
