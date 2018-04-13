package com.baidu.mobstat;

import android.content.Context;
import android.content.SharedPreferences;

abstract class bk {
    public abstract SharedPreferences a(Context context);

    bk() {
    }

    public boolean a(Context context, String str, boolean z) {
        return a(context).getBoolean(str, z);
    }

    public void b(Context context, String str, boolean z) {
        a(context).edit().putBoolean(str, z).commit();
    }

    public int a(Context context, String str, int i) {
        return a(context).getInt(str, i);
    }

    public void b(Context context, String str, int i) {
        a(context).edit().putInt(str, i).commit();
    }

    public long a(Context context, String str, long j) {
        return a(context).getLong(str, j);
    }

    public void b(Context context, String str, long j) {
        a(context).edit().putLong(str, j).commit();
    }

    public String a(Context context, String str, String str2) {
        return a(context).getString(str, str2);
    }

    public void b(Context context, String str, String str2) {
        a(context).edit().putString(str, str2).commit();
    }

    public void g(Context context, String str) {
        a(context).edit().remove(str).commit();
    }
}
