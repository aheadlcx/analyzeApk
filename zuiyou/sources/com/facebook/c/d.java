package com.facebook.c;

import com.facebook.common.internal.a;
import com.facebook.common.internal.g;
import com.facebook.common.internal.k;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.annotation.Nullable;

public class d {
    private static d a;
    private int b;
    @Nullable
    private List<c$a> c;
    private final c$a d = new a();

    private d() {
        b();
    }

    public void a(@Nullable List<c$a> list) {
        this.c = list;
        b();
    }

    public c a(InputStream inputStream) throws IOException {
        c a;
        g.a(inputStream);
        byte[] bArr = new byte[this.b];
        int a2 = a(this.b, inputStream, bArr);
        if (this.c != null) {
            for (c$a a3 : this.c) {
                a = a3.a(bArr, a2);
                if (a != null && a != c.a) {
                    return a;
                }
            }
        }
        a = this.d.a(bArr, a2);
        if (a == null) {
            return c.a;
        }
        return a;
    }

    private void b() {
        this.b = this.d.a();
        if (this.c != null) {
            for (c$a a : this.c) {
                this.b = Math.max(this.b, a.a());
            }
        }
    }

    private static int a(int i, InputStream inputStream, byte[] bArr) throws IOException {
        g.a(inputStream);
        g.a(bArr);
        g.a(bArr.length >= i);
        if (!inputStream.markSupported()) {
            return a.a(inputStream, bArr, 0, i);
        }
        try {
            inputStream.mark(i);
            int a = a.a(inputStream, bArr, 0, i);
            return a;
        } finally {
            inputStream.reset();
        }
    }

    public static synchronized d a() {
        d dVar;
        synchronized (d.class) {
            if (a == null) {
                a = new d();
            }
            dVar = a;
        }
        return dVar;
    }

    public static c b(InputStream inputStream) throws IOException {
        return a().a(inputStream);
    }

    public static c c(InputStream inputStream) {
        try {
            return b(inputStream);
        } catch (Throwable e) {
            throw k.b(e);
        }
    }
}
