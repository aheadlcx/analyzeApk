package com.bdj.picture.edit.util;

import com.nostra13.universalimageloader.core.b.d;
import com.nostra13.universalimageloader.core.c;

public class h {
    private static c a;

    public static c a() {
        if (a == null) {
            synchronized (h.class) {
                if (a == null) {
                    a = g.a(new d());
                }
            }
        }
        return a;
    }
}
