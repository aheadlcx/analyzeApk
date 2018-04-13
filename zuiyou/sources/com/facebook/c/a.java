package com.facebook.c;

import com.facebook.common.g.c;
import com.facebook.common.internal.e;
import com.facebook.common.internal.g;
import javax.annotation.Nullable;

public class a implements c$a {
    private static final byte[] b = new byte[]{(byte) -1, (byte) -40, (byte) -1};
    private static final int c = b.length;
    private static final byte[] d = new byte[]{(byte) -119, (byte) 80, (byte) 78, (byte) 71, (byte) 13, (byte) 10, (byte) 26, (byte) 10};
    private static final int e = d.length;
    private static final byte[] f = e.a("GIF87a");
    private static final byte[] g = e.a("GIF89a");
    private static final byte[] h = e.a("BM");
    private static final int i = h.length;
    final int a = e.a(new int[]{21, 20, c, e, 6, i});

    public int a() {
        return this.a;
    }

    @Nullable
    public final c a(byte[] bArr, int i) {
        g.a(bArr);
        if (c.b(bArr, 0, i)) {
            return b(bArr, i);
        }
        if (c(bArr, i)) {
            return b.a;
        }
        if (d(bArr, i)) {
            return b.b;
        }
        if (e(bArr, i)) {
            return b.c;
        }
        if (f(bArr, i)) {
            return b.d;
        }
        return c.a;
    }

    private static c b(byte[] bArr, int i) {
        g.a(c.b(bArr, 0, i));
        if (c.b(bArr, 0)) {
            return b.e;
        }
        if (c.c(bArr, 0)) {
            return b.f;
        }
        if (!c.a(bArr, 0, i)) {
            return c.a;
        }
        if (c.a(bArr, 0)) {
            return b.i;
        }
        if (c.d(bArr, 0)) {
            return b.h;
        }
        return b.g;
    }

    private static boolean c(byte[] bArr, int i) {
        return i >= b.length && e.a(bArr, b);
    }

    private static boolean d(byte[] bArr, int i) {
        return i >= d.length && e.a(bArr, d);
    }

    private static boolean e(byte[] bArr, int i) {
        if (i < 6) {
            return false;
        }
        if (e.a(bArr, f) || e.a(bArr, g)) {
            return true;
        }
        return false;
    }

    private static boolean f(byte[] bArr, int i) {
        if (i < h.length) {
            return false;
        }
        return e.a(bArr, h);
    }
}
