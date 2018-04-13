package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;

public class be implements av {
    private static final String h = "successful_request";
    private static final String i = "failed_requests ";
    private static final String j = "last_request_spent_ms";
    private static final String k = "last_request_time";
    private static final String l = "first_activate_time";
    private static final String m = "last_req";
    public int a;
    public int b;
    public long c;
    private final int d = 3600000;
    private int e;
    private long f = 0;
    private long g = 0;
    private Context n;

    public be(Context context) {
        a(context);
    }

    private void a(Context context) {
        this.n = context.getApplicationContext();
        SharedPreferences a = ba.a(context);
        this.a = a.getInt(h, 0);
        this.b = a.getInt(i, 0);
        this.e = a.getInt(j, 0);
        this.c = a.getLong(k, 0);
        this.f = a.getLong(m, 0);
    }

    public int e() {
        return this.e > 3600000 ? 3600000 : this.e;
    }

    public boolean f() {
        boolean z;
        if (this.c == 0) {
            z = true;
        } else {
            z = false;
        }
        boolean z2;
        if (cc.a(this.n).h()) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z && r3) {
            return true;
        }
        return false;
    }

    public void g() {
        this.a++;
        this.c = this.f;
    }

    public void h() {
        this.b++;
    }

    public void i() {
        this.f = System.currentTimeMillis();
    }

    public void j() {
        this.e = (int) (System.currentTimeMillis() - this.f);
    }

    public void k() {
        ba.a(this.n).edit().putInt(h, this.a).putInt(i, this.b).putInt(j, this.e).putLong(k, this.c).putLong(m, this.f).commit();
    }

    public long l() {
        SharedPreferences a = ba.a(this.n);
        this.g = ba.a(this.n).getLong(l, 0);
        if (this.g == 0) {
            this.g = System.currentTimeMillis();
            a.edit().putLong(l, this.g).commit();
        }
        return this.g;
    }

    public long m() {
        return this.f;
    }

    public void a() {
        i();
    }

    public void b() {
        j();
    }

    public void c() {
        g();
    }

    public void d() {
        h();
    }
}
