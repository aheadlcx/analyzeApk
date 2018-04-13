package com.liulishuo.filedownloader.e;

import android.app.NotificationManager;
import com.liulishuo.filedownloader.g.c;

public abstract class a {
    private int a;
    private int b;
    private int c;
    private String d;
    private String e;
    private int f = 0;
    private int g = 0;
    private NotificationManager h;

    public abstract void a(boolean z, int i, boolean z2);

    public a(int i, String str, String str2) {
        this.a = i;
        this.d = str;
        this.e = str2;
    }

    public void a(boolean z) {
        a(i(), h(), z);
    }

    public void a(int i, int i2) {
        this.b = i;
        this.c = i2;
        a(true);
    }

    public void a(int i) {
        this.f = i;
    }

    public void a() {
        b().cancel(this.a);
    }

    protected NotificationManager b() {
        if (this.h == null) {
            this.h = (NotificationManager) c.a().getSystemService("notification");
        }
        return this.h;
    }

    public int c() {
        return this.a;
    }

    public int d() {
        return this.b;
    }

    public int e() {
        return this.c;
    }

    public String f() {
        return this.d;
    }

    public String g() {
        return this.e;
    }

    public int h() {
        this.g = this.f;
        return this.f;
    }

    public boolean i() {
        return this.g != this.f;
    }
}
