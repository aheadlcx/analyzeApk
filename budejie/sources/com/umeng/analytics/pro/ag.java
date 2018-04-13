package com.umeng.analytics.pro;

import android.content.Context;

public class ag extends y {
    private static final String a = "mac";
    private Context b;

    public ag(Context context) {
        super(a);
        this.b = context;
    }

    public String f() {
        String str = null;
        try {
            str = bv.q(this.b);
        } catch (Exception e) {
        }
        return str;
    }
}
