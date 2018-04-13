package com.bumptech.glide.load.engine.b;

import android.annotation.SuppressLint;
import com.bumptech.glide.i.e;
import com.bumptech.glide.load.b;
import com.bumptech.glide.load.engine.b.i.a;
import com.bumptech.glide.load.engine.j;

public class h extends e<b, j<?>> implements i {
    private a a;

    public /* synthetic */ j a(b bVar) {
        return (j) super.c(bVar);
    }

    public /* bridge */ /* synthetic */ j b(b bVar, j jVar) {
        return (j) super.b(bVar, jVar);
    }

    public h(int i) {
        super(i);
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    protected void a(b bVar, j<?> jVar) {
        if (this.a != null) {
            this.a.b(jVar);
        }
    }

    protected int a(j<?> jVar) {
        return jVar.c();
    }

    @SuppressLint({"InlinedApi"})
    public void a(int i) {
        if (i >= 60) {
            a();
        } else if (i >= 40) {
            b(b() / 2);
        }
    }
}
