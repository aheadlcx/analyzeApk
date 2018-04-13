package com.facebook.imagepipeline.common;

import com.facebook.common.util.a;
import java.util.Locale;

public class d {
    private static final d c = new d(-1, false);
    private static final d d = new d(-2, false);
    private static final d e = new d(-1, true);
    private final int a;
    private final boolean b;

    public static d a() {
        return c;
    }

    public static d b() {
        return d;
    }

    public static d c() {
        return e;
    }

    private d(int i, boolean z) {
        this.a = i;
        this.b = z;
    }

    public boolean d() {
        return this.a == -1;
    }

    public boolean e() {
        return this.a != -2;
    }

    public int f() {
        if (!d()) {
            return this.a;
        }
        throw new IllegalStateException("Rotation is set to use EXIF");
    }

    public boolean g() {
        return this.b;
    }

    public int hashCode() {
        return a.a(Integer.valueOf(this.a), Boolean.valueOf(this.b));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        if (this.a == dVar.a && this.b == dVar.b) {
            return true;
        }
        return false;
    }

    public String toString() {
        return String.format((Locale) null, "%d defer:%b", new Object[]{Integer.valueOf(this.a), Boolean.valueOf(this.b)});
    }
}
