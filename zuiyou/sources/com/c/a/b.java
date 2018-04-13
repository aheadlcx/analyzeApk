package com.c.a;

import android.os.Build.VERSION;

public class b implements f {
    public e a;
    private int b = 500;

    public b(e eVar) {
        this.a = eVar;
    }

    public void a(boolean z) {
        if (z) {
            this.a.a((f) this);
        } else {
            this.a.b((f) this);
        }
    }

    public void a(float f, int i) {
        if (VERSION.SDK_INT > 11) {
            e a = c.a(this.a);
            if (a != null) {
                a.c().setX((float) ((int) Math.min((((float) (-this.b)) * Math.max(1.0f - f, 0.0f)) + 40.0f, 0.0f)));
                if (f == 0.0f) {
                    a.c().setX(0.0f);
                }
            }
        }
    }

    public void a() {
    }

    public void b() {
        e a = c.a(this.a);
        if (VERSION.SDK_INT > 11 && a != null) {
            a.c().setX(0.0f);
        }
    }
}
