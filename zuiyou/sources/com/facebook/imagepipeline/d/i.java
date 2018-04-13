package com.facebook.imagepipeline.d;

import com.facebook.common.g.b;
import com.facebook.common.g.b.a;
import com.facebook.imagepipeline.c.q;
import javax.annotation.Nullable;

public class i {
    private final int a;
    private final boolean b;
    private final boolean c;
    private final com.facebook.common.internal.i<Boolean> d;
    private final q e;
    private final a f;
    private final boolean g;
    private final b h;
    private final boolean i;
    private final boolean j;

    private i(i$a i_a, h$a h_a) {
        this.a = i$a.a(i_a);
        this.b = i$a.b(i_a);
        this.c = i$a.c(i_a);
        if (i$a.d(i_a) != null) {
            this.d = i$a.d(i_a);
        } else {
            this.d = new i$1(this);
        }
        this.e = i$a.e(i_a);
        this.f = i$a.f(i_a);
        this.g = i$a.g(i_a);
        this.h = i$a.h(i_a);
        this.i = i$a.i(i_a);
        this.j = i$a.j(i_a);
    }

    public boolean a() {
        return this.c;
    }

    public int b() {
        return this.a;
    }

    public boolean c() {
        return ((Boolean) this.d.b()).booleanValue();
    }

    @Nullable
    public q d() {
        return this.e;
    }

    public boolean e() {
        return this.j;
    }

    public boolean f() {
        return this.b;
    }

    public boolean g() {
        return this.g;
    }

    public a h() {
        return this.f;
    }

    public b i() {
        return this.h;
    }
}
