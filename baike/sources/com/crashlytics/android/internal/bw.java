package com.crashlytics.android.internal;

final class bw {
    static final bw a = new bw(0, 0);
    final int b;
    final int c;

    bw(int i, int i2) {
        this.b = i;
        this.c = i2;
    }

    public final String toString() {
        return getClass().getSimpleName() + "[position = " + this.b + ", length = " + this.c + "]";
    }
}
