package com.umeng.analytics.c;

import android.content.Context;
import com.umeng.a.a;

public class d extends a {
    private static final String a = "idfa";
    private Context b;

    public d(Context context) {
        super(a);
        this.b = context;
    }

    public String f() {
        String a = a.a(this.b);
        return a == null ? "" : a;
    }
}
