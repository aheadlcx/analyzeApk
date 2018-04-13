package com.alipay.sdk.util;

import android.content.pm.Signature;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.c;
import com.alipay.sdk.cons.a;

public class l$a {
    public Signature[] a;
    public int b;

    public final boolean a() {
        if (this.a == null || this.a.length <= 0) {
            return false;
        }
        int i = 0;
        while (i < this.a.length) {
            String a = l.a(this.a[i].toByteArray());
            if (a == null || TextUtils.equals(a, a.g)) {
                i++;
            } else {
                com.alipay.sdk.app.statistic.a.a("biz", c.t, a);
                return true;
            }
        }
        return false;
    }
}
