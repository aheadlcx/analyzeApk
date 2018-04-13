package com.umeng.analytics;

import java.util.Locale;

enum f extends Gender {
    f(String str, int i, int i2) {
        super(str, i, i2);
    }

    public String toString() {
        return String.format(Locale.US, "Female:%d", new Object[]{Integer.valueOf(this.value)});
    }
}
