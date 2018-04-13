package com.danikula.videocache.a;

import java.io.File;

public class h extends e {
    private final long a;

    public h(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("Max size must be positive number!");
        }
        this.a = j;
    }

    protected boolean a(File file, long j, int i) {
        return j <= this.a;
    }
}
