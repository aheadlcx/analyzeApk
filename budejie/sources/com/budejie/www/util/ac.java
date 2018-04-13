package com.budejie.www.util;

import android.content.Context;
import android.media.MediaPlayer;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.BudejieApplication.Status;
import com.budejie.www.f.b;
import com.budejie.www.service.MediaPlayerServer.a;

public class ac {
    private static ac b;
    Context a;
    private MediaPlayer c;
    private a d;
    private BudejieApplication e;

    private ac(Context context) {
        b(context);
    }

    public static ac a(Context context) {
        if (b == null) {
            b = new ac(context);
        }
        return b;
    }

    private void b(Context context) {
        try {
            this.a = context;
            this.e = (BudejieApplication) context.getApplicationContext();
            if (this.e != null) {
                this.d = this.e.f();
            }
            if (this.d != null) {
                this.d.a(context);
            }
        } catch (Exception e) {
        }
    }

    public void a(b bVar) {
        if (this.d != null) {
            this.d.a(bVar);
        }
    }

    public void a(String str) {
        if (this.d != null) {
            this.d.a(str);
        }
    }

    public void b(String str) {
        if (this.d != null) {
            this.d.a(str);
        }
    }

    public void a(int i) {
        if (this.d != null) {
            this.d.a(i);
        }
    }

    public boolean a() {
        if (this.d == null) {
            return false;
        }
        return this.d.m();
    }

    public void a(boolean z) {
        if (this.d != null) {
            this.d.a(z);
        }
    }

    public boolean b() {
        if (this.d == null) {
            return false;
        }
        return this.d.n();
    }

    public boolean c() {
        if (this.d == null) {
            return false;
        }
        return this.d.a();
    }

    public void d() {
        if (this.d != null) {
            this.d.b();
            this.e.a(Status.start);
        }
    }

    public void e() {
        if (this.d != null) {
            this.d.c();
            this.e.a(Status.stop);
        }
    }

    public void f() {
        try {
            if (this.d != null && this.d.a()) {
                this.d.d();
            }
            if (this.d != null && this.d.j() != null) {
                this.d.j().b();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void g() {
        if (this.d != null) {
            this.d.d();
        }
    }

    public void h() {
        if (this.c != null) {
            this.c.release();
        }
    }

    public void i() {
        if (this.d != null) {
            this.d.e();
        }
    }

    public int j() {
        if (this.d == null) {
            return 0;
        }
        return this.d.g();
    }

    public int k() {
        if (this.d == null) {
            return 0;
        }
        return this.d.f();
    }

    public String l() {
        if (this.d == null) {
            return "";
        }
        return this.d.o();
    }

    public String m() {
        if (this.d == null) {
            return "";
        }
        return com.lt.a.a(this.a).b(this.d.o());
    }

    public void c(String str) {
        if (this.d != null) {
            this.d.d(str);
        }
    }

    public int n() {
        if (this.d == null) {
            return 0;
        }
        return this.d.l();
    }

    public int o() {
        if (this.d == null) {
            return 0;
        }
        return this.d.k();
    }

    public static String a(long j) {
        if (j <= 0) {
            return "00:00";
        }
        try {
            int i = (int) ((j / 1000) / 60);
            int i2 = (int) ((j / 1000) % 60);
            return (i < 10 ? "0" + i : Integer.valueOf(i)) + ":" + (i2 < 10 ? "0" + i2 : Integer.valueOf(i2));
        } catch (Exception e) {
            return "00:00";
        }
    }
}
