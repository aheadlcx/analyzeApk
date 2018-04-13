package com.umeng.analytics;

import java.util.Locale;

enum e extends Gender {
    e(String str, int i, int i2) {
        super(str, i, i2);
    }

    public String toString() {
        return String.format(Locale.US, "Male:%d", new Object[]{Integer.valueOf(this.value)});
    }
}
