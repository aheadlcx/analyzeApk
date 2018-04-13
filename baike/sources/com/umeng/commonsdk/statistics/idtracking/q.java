package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;

public class q extends a {
    public static final String a = "uopdta";
    private Context b;

    public q(Context context) {
        super("uop");
        this.b = context;
    }

    public String f() {
        String str = "";
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(this.b);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(a, "");
        }
        return str;
    }
}
