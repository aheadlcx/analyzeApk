package com.bumptech.glide.load.b.b;

import com.bumptech.glide.load.a.c;
import java.io.InputStream;

public class b implements d<byte[]> {
    private final String a;

    public b() {
        this("");
    }

    @Deprecated
    public b(String str) {
        this.a = str;
    }

    public c<InputStream> a(byte[] bArr, int i, int i2) {
        return new com.bumptech.glide.load.a.b(bArr, this.a);
    }
}
