package com.bumptech.glide;

import android.content.Context;
import android.os.Build.VERSION;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.a.d;
import com.bumptech.glide.load.engine.a.f;
import com.bumptech.glide.load.engine.b;
import com.bumptech.glide.load.engine.b.a.a;
import com.bumptech.glide.load.engine.b.g;
import com.bumptech.glide.load.engine.b.h;
import com.bumptech.glide.load.engine.b.i;
import com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor;
import java.util.concurrent.ExecutorService;

public class j {
    private final Context a;
    private b b;
    private c c;
    private i d;
    private ExecutorService e;
    private ExecutorService f;
    private DecodeFormat g;
    private a h;

    public j(Context context) {
        this.a = context.getApplicationContext();
    }

    public j a(a aVar) {
        this.h = aVar;
        return this;
    }

    public j a(DecodeFormat decodeFormat) {
        this.g = decodeFormat;
        return this;
    }

    i a() {
        if (this.e == null) {
            this.e = new FifoPriorityThreadPoolExecutor(Math.max(1, Runtime.getRuntime().availableProcessors()));
        }
        if (this.f == null) {
            this.f = new FifoPriorityThreadPoolExecutor(1);
        }
        com.bumptech.glide.load.engine.b.j jVar = new com.bumptech.glide.load.engine.b.j(this.a);
        if (this.c == null) {
            if (VERSION.SDK_INT >= 11) {
                this.c = new f(jVar.b());
            } else {
                this.c = new d();
            }
        }
        if (this.d == null) {
            this.d = new h(jVar.a());
        }
        if (this.h == null) {
            this.h = new g(this.a);
        }
        if (this.b == null) {
            this.b = new b(this.d, this.h, this.f, this.e);
        }
        if (this.g == null) {
            this.g = DecodeFormat.DEFAULT;
        }
        return new i(this.b, this.d, this.c, this.a, this.g);
    }
}
