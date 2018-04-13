package com.bumptech.glide.load.resource.c;

import android.content.Context;
import com.bumptech.glide.f.b;
import com.bumptech.glide.load.a;
import com.bumptech.glide.load.b.o;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.e;
import java.io.File;
import java.io.InputStream;

public class c implements b<InputStream, b> {
    private final i a;
    private final j b;
    private final o c;
    private final com.bumptech.glide.load.resource.b.c<b> d = new com.bumptech.glide.load.resource.b.c(this.a);

    public c(Context context, com.bumptech.glide.load.engine.a.c cVar) {
        this.a = new i(context, cVar);
        this.b = new j(cVar);
        this.c = new o();
    }

    public d<File, b> a() {
        return this.d;
    }

    public d<InputStream, b> b() {
        return this.a;
    }

    public a<InputStream> c() {
        return this.c;
    }

    public e<b> d() {
        return this.b;
    }
}
