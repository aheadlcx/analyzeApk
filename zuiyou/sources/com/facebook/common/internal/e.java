package com.facebook.common.internal;

public class e {
    public static int a(int... iArr) {
        boolean z;
        int i = 1;
        if (iArr.length > 0) {
            z = true;
        } else {
            z = false;
        }
        g.a(z);
        int i2 = iArr[0];
        while (i < iArr.length) {
            if (iArr[i] > i2) {
                i2 = iArr[i];
            }
            i++;
        }
        return i2;
    }
}
