package com.bumptech.glide.load.engine.b;

import java.io.File;

public class d implements com.bumptech.glide.load.engine.b.a.a {
    private final int a;
    private final a b;

    public interface a {
        File a();
    }

    public d(a aVar, int i) {
        this.a = i;
        this.b = aVar;
    }

    public a a() {
        File a = this.b.a();
        if (a == null) {
            return null;
        }
        if (a.mkdirs() || (a.exists() && a.isDirectory())) {
            return e.a(a, this.a);
        }
        return null;
    }
}
