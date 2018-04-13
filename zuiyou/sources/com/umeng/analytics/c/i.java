package com.umeng.analytics.c;

import android.content.Context;
import com.umeng.a.d;

public class i extends a {
    private static final String a = "mac";
    private Context b;

    public i(Context context) {
        super(a);
        this.b = context;
    }

    public String f() {
        String str = null;
        try {
            str = d.n(this.b);
        } catch (Exception e) {
        }
        return str;
    }
}
