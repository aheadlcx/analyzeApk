package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.analytics.a;

public class al extends y {
    private static final String a = "uop";
    private Context b;

    public al(Context context) {
        super(a);
        this.b = context;
    }

    public String f() {
        String str = "";
        SharedPreferences a = ba.a(this.b);
        if (a != null) {
            return a.getString(a.r, "");
        }
        return str;
    }
}
