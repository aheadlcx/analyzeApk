package com.huawei.hms.update.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

class c {
    private String a;
    private int b;
    private String c;
    private int d;

    c() {
    }

    public void a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.huawei.hms.update.DOWNLOAD_RECORD", 0);
        this.a = sharedPreferences.getString("mUri", "");
        this.b = sharedPreferences.getInt("mSize", 0);
        this.c = sharedPreferences.getString("mHash", "");
        this.d = sharedPreferences.getInt("mReceived", 0);
    }

    public void a(String str, int i, String str2) {
        this.a = str;
        this.b = i;
        this.c = str2;
        this.d = 0;
    }

    public void a(Context context, int i) {
        this.d = i;
        b(context);
    }

    private void b(Context context) {
        Editor edit = context.getSharedPreferences("com.huawei.hms.update.DOWNLOAD_RECORD", 0).edit();
        edit.putString("mUri", this.a);
        edit.putInt("mSize", this.b);
        edit.putString("mHash", this.c);
        edit.putInt("mReceived", this.d);
        edit.commit();
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.d;
    }

    public boolean b(String str, int i, String str2) {
        if (str == null || str2 == null || this.a == null || !this.a.equals(str) || this.b != i || this.c == null || !this.c.equals(str2) || this.d > this.b) {
            return false;
        }
        return true;
    }
}
