package com.qiushibaike.statsdk;

import android.content.Context;
import android.content.SharedPreferences;

public class BaseStoreTool {
    private SharedPreferences a;

    private SharedPreferences a(Context context) {
        if (this.a == null) {
            this.a = context.getSharedPreferences("__Qiubai_Stat_SDK_Cfg", 0);
        }
        return this.a;
    }

    public void putInt(Context context, String str, int i) {
        a(context).edit().putInt(str, i).commit();
    }

    public int getInt(Context context, String str, int i) {
        return a(context).getInt(str, i);
    }

    public void putString(Context context, String str, String str2) {
        a(context).edit().putString(str, str2).commit();
    }

    public String getString(Context context, String str, String str2) {
        return a(context).getString(str, str2);
    }

    public void putLong(Context context, String str, long j) {
        a(context).edit().putLong(str, j).commit();
    }

    public long getLong(Context context, String str, long j) {
        return a(context).getLong(str, j);
    }

    public void putBoolean(Context context, String str, boolean z) {
        a(context).edit().putBoolean(str, z).commit();
    }

    public boolean getBoolean(Context context, String str, boolean z) {
        return a(context).getBoolean(str, z);
    }

    public void remove(Context context, String str) {
        a(context).edit().remove(str).commit();
    }
}
