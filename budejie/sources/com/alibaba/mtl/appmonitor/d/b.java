package com.alibaba.mtl.appmonitor.d;

import android.text.TextUtils;
import java.util.Set;

public class b {
    private a a;
    private Set<String> c;

    private enum a {
        IN,
        NOT_IN
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        boolean contains = this.c.contains(str);
        if (this.a == a.IN) {
            return contains;
        }
        if (contains) {
            return false;
        }
        return true;
    }
}
