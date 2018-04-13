package com.umeng.analytics;

import java.util.Locale;

enum g extends Gender {
    g(String str, int i, int i2) {
        super(str, i, i2);
    }

    public String toString() {
        return String.format(Locale.US, "Unknown:%d", new Object[]{Integer.valueOf(this.value)});
    }
}
