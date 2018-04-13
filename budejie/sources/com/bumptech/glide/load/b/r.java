package com.bumptech.glide.load.b;

import com.bumptech.glide.load.a.c;
import java.net.URL;

public class r<T> implements l<URL, T> {
    private final l<d, T> a;

    public r(l<d, T> lVar) {
        this.a = lVar;
    }

    public c<T> a(URL url, int i, int i2) {
        return this.a.a(new d(url), i, i2);
    }
}
