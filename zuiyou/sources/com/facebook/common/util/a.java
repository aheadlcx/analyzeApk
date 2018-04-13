package com.facebook.common.util;

import javax.annotation.Nullable;

public class a {
    public static int a(@Nullable Object obj, @Nullable Object obj2) {
        int i;
        int i2 = 0;
        if (obj == null) {
            i = 0;
        } else {
            i = obj.hashCode();
        }
        if (obj2 != null) {
            i2 = obj2.hashCode();
        }
        return a(i, i2);
    }

    public static int a(@Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object obj5, @Nullable Object obj6) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = 0;
        if (obj == null) {
            i = 0;
        } else {
            i = obj.hashCode();
        }
        if (obj2 == null) {
            i2 = 0;
        } else {
            i2 = obj2.hashCode();
        }
        if (obj3 == null) {
            i3 = 0;
        } else {
            i3 = obj3.hashCode();
        }
        if (obj4 == null) {
            i4 = 0;
        } else {
            i4 = obj4.hashCode();
        }
        if (obj5 == null) {
            i5 = 0;
        } else {
            i5 = obj5.hashCode();
        }
        if (obj6 != null) {
            i6 = obj6.hashCode();
        }
        return a(i, i2, i3, i4, i5, i6);
    }

    public static int a(int i, int i2) {
        return ((i + 31) * 31) + i2;
    }

    public static int a(int i, int i2, int i3, int i4, int i5, int i6) {
        return ((((((((((i + 31) * 31) + i2) * 31) + i3) * 31) + i4) * 31) + i5) * 31) + i6;
    }
}
