package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.b;
import com.xiaomi.channel.commonutils.android.e;

public class a {
    private static a a;
    private Context b;
    private a c;

    private class a {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public boolean h;
        public boolean i;
        public int j;
        final /* synthetic */ a k;

        private a(a aVar) {
            this.k = aVar;
            this.h = true;
            this.i = false;
            this.j = 1;
        }

        private String d() {
            return b.a(this.k.b, this.k.b.getPackageName());
        }

        public void a(int i) {
            this.j = i;
        }

        public void a(String str, String str2) {
            this.c = str;
            this.d = str2;
            this.f = e.e(this.k.b);
            this.e = d();
            this.h = true;
            Editor edit = this.k.j().edit();
            edit.putString("regId", str);
            edit.putString("regSec", str2);
            edit.putString("devId", this.f);
            edit.putString("vName", d());
            edit.putBoolean("valid", true);
            edit.commit();
        }

        public void a(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.g = str3;
            Editor edit = this.k.j().edit();
            edit.putString("appId", this.a);
            edit.putString("appToken", str2);
            edit.putString("regResource", str3);
            edit.commit();
        }

        public void a(boolean z) {
            this.i = z;
        }

        public boolean a() {
            return b(this.a, this.b);
        }

        public void b() {
            this.k.j().edit().clear().commit();
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.f = null;
            this.e = null;
            this.h = false;
            this.i = false;
            this.j = 1;
        }

        public boolean b(String str, String str2) {
            return TextUtils.equals(this.a, str) && TextUtils.equals(this.b, str2) && !TextUtils.isEmpty(this.c) && !TextUtils.isEmpty(this.d) && TextUtils.equals(this.f, e.e(this.k.b));
        }

        public void c() {
            this.h = false;
            this.k.j().edit().putBoolean("valid", this.h).commit();
        }
    }

    private a(Context context) {
        this.b = context;
        o();
    }

    public static a a(Context context) {
        if (a == null) {
            a = new a(context);
        }
        return a;
    }

    private void o() {
        this.c = new a();
        SharedPreferences j = j();
        this.c.a = j.getString("appId", null);
        this.c.b = j.getString("appToken", null);
        this.c.c = j.getString("regId", null);
        this.c.d = j.getString("regSec", null);
        this.c.f = j.getString("devId", null);
        if (!TextUtils.isEmpty(this.c.f) && this.c.f.startsWith("a-")) {
            this.c.f = e.e(this.b);
            j.edit().putString("devId", this.c.f).commit();
        }
        this.c.e = j.getString("vName", null);
        this.c.h = j.getBoolean("valid", true);
        this.c.i = j.getBoolean("paused", false);
        this.c.j = j.getInt("envType", 1);
        this.c.g = j.getString("regResource", null);
    }

    public void a(int i) {
        this.c.a(i);
        j().edit().putInt("envType", i).commit();
    }

    public void a(String str) {
        Editor edit = j().edit();
        edit.putString("vName", str);
        edit.commit();
        this.c.e = str;
    }

    public void a(String str, String str2, String str3) {
        this.c.a(str, str2, str3);
    }

    public void a(boolean z) {
        this.c.a(z);
        j().edit().putBoolean("paused", z).commit();
    }

    public boolean a() {
        return !TextUtils.equals(b.a(this.b, this.b.getPackageName()), this.c.e);
    }

    public boolean a(String str, String str2) {
        return this.c.b(str, str2);
    }

    public void b(String str, String str2) {
        this.c.a(str, str2);
    }

    public boolean b() {
        if (this.c.a()) {
            return true;
        }
        com.xiaomi.channel.commonutils.logger.b.a("Don't send message before initialization succeeded!");
        return false;
    }

    public String c() {
        return this.c.a;
    }

    public String d() {
        return this.c.b;
    }

    public String e() {
        return this.c.c;
    }

    public String f() {
        return this.c.d;
    }

    public String g() {
        return this.c.g;
    }

    public void h() {
        this.c.b();
    }

    public boolean i() {
        return this.c.a();
    }

    public SharedPreferences j() {
        return this.b.getSharedPreferences("mipush", 0);
    }

    public void k() {
        this.c.c();
    }

    public boolean l() {
        return this.c.i;
    }

    public int m() {
        return this.c.j;
    }

    public boolean n() {
        return !this.c.h;
    }
}
