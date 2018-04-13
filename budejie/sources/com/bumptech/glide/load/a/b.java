package com.bumptech.glide.load.a;

import com.bumptech.glide.Priority;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class b implements c<InputStream> {
    private final byte[] a;
    private final String b;

    public /* synthetic */ Object a(Priority priority) throws Exception {
        return b(priority);
    }

    public b(byte[] bArr, String str) {
        this.a = bArr;
        this.b = str;
    }

    public InputStream b(Priority priority) {
        return new ByteArrayInputStream(this.a);
    }

    public void a() {
    }

    public String b() {
        return this.b;
    }

    public void c() {
    }
}
