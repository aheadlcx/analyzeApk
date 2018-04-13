package com.umeng.analytics.pro;

import android.content.Context;

public class ab extends y {
    private static final String a = "idfa";
    private Context b;

    public ab(Context context) {
        super(a);
        this.b = context;
    }

    public String f() {
        String a = br.a(this.b);
        return a == null ? "" : a;
    }
}
