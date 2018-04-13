package com.budejie.www.wallpaper;

import android.content.Context;
import android.text.TextUtils;
import com.budejie.www.goddubbing.c.d;
import com.zxt.download2.f;
import com.zxt.download2.g;

public class a implements com.zxt.download2.b {
    private Context a;
    private b b;
    private f c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private boolean i;
    private int j;

    public interface b {
        void a();

        void a(int i);
    }

    private a(a$a a_a) {
        this.g = a$a.a(a_a);
        this.e = a$a.b(a_a);
        this.f = a$a.c(a_a);
        this.h = a$a.d(a_a);
        this.j = a$a.e(a_a);
        this.a = a$a.f(a_a);
        this.b = a$a.g(a_a);
        this.d = this.g + this.e;
    }

    public void a(String str) {
        if (!(this.b == null || this.i)) {
            this.i = true;
            this.b.a();
        }
        d.a(this.a, str);
    }

    public void a() {
        this.b = null;
    }

    public void b() {
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public void a(int i, int i2, int i3) {
        if (!this.i) {
            long j = (((long) i) * ((long) this.j)) / ((long) i2);
            if (this.b != null) {
                this.b.a((int) j);
            }
            if (j == ((long) this.j) && this.b != null && !this.i) {
                this.i = true;
                this.b.a();
                d.a(this.a, this.d);
            }
        }
    }

    public void f() {
        String str = "";
        if (!TextUtils.isEmpty(this.f)) {
            this.c = new f(this.f, this.g, this.e, str, null);
            g.a(this.a).a(this.c, (com.zxt.download2.b) this);
            g.a(this.a).a(this.c);
        }
    }

    public void g() {
        if (this.c != null) {
            g.a(this.a).a(this.c, true);
            g.a(this.a).h(this.c);
            g.a(this.a).e(this.c);
        }
    }
}
