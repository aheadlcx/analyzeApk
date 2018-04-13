package com.umeng.analytics.pro;

import android.content.Context;
import android.provider.Settings.Secure;

public class z extends y {
    private static final String a = "android_id";
    private Context b;

    public z(Context context) {
        super(a);
        this.b = context;
    }

    public String f() {
        String str = null;
        try {
            str = Secure.getString(this.b.getContentResolver(), a);
        } catch (Exception e) {
        }
        return str;
    }
}
