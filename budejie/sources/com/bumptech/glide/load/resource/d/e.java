package com.bumptech.glide.load.resource.d;

import com.bumptech.glide.load.b.g;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.j;
import java.io.IOException;
import java.io.InputStream;

public class e implements d<InputStream, a> {
    private final d<g, a> a;

    public e(d<g, a> dVar) {
        this.a = dVar;
    }

    public j<a> a(InputStream inputStream, int i, int i2) throws IOException {
        return this.a.a(new g(inputStream, null), i, i2);
    }

    public String a() {
        return this.a.a();
    }
}
