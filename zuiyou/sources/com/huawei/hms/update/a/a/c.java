package com.huawei.hms.update.a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class c {
    public int a = 0;
    public String b = "";
    public int c = 0;
    public String d = "";

    public c(int i, String str, int i2, String str2) {
        this.a = i;
        this.b = str;
        this.c = i2;
        this.d = str2;
    }

    public void a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.huawei.hms.update.UPDATE_INFO", 0);
        this.a = sharedPreferences.getInt("mNewVersionCode", 0);
        this.b = sharedPreferences.getString("mUri", "");
        this.c = sharedPreferences.getInt("mSize", 0);
        this.d = sharedPreferences.getString("mHash", "");
    }

    public void b(Context context) {
        Editor edit = context.getSharedPreferences("com.huawei.hms.update.UPDATE_INFO", 0).edit();
        edit.putInt("mNewVersionCode", this.a);
        edit.putString("mUri", this.b);
        edit.putInt("mSize", this.c);
        edit.putString("mHash", this.d);
        edit.commit();
    }

    public void c(Context context) {
        Editor edit = context.getSharedPreferences("com.huawei.hms.update.UPDATE_INFO", 0).edit();
        edit.clear();
        edit.commit();
    }

    public boolean a() {
        return this.a > 0 && this.c > 0 && this.b != null && !this.b.isEmpty();
    }
}
