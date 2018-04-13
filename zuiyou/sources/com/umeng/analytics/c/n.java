package com.umeng.analytics.c;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.analytics.a;
import com.umeng.analytics.d.m;

public class n extends a {
    private static final String a = "uop";
    private Context b;

    public n(Context context) {
        super(a);
        this.b = context;
    }

    public String f() {
        String str = "";
        SharedPreferences a = m.a(this.b);
        if (a != null) {
            return a.getString(a.r, "");
        }
        return str;
    }
}
