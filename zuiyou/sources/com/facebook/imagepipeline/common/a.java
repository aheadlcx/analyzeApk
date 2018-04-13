package com.facebook.imagepipeline.common;

import android.graphics.Bitmap.Config;
import com.facebook.imagepipeline.f.b;
import java.util.Locale;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class a {
    private static final a h = b().h();
    public final int a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final Config f;
    @Nullable
    public final b g;

    public a(b bVar) {
        this.a = bVar.a();
        this.b = bVar.b();
        this.c = bVar.c();
        this.d = bVar.d();
        this.e = bVar.f();
        this.f = bVar.g();
        this.g = bVar.e();
    }

    public static a a() {
        return h;
    }

    public static b b() {
        return new b();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        if (this.b != aVar.b) {
            return false;
        }
        if (this.c != aVar.c) {
            return false;
        }
        if (this.d != aVar.d) {
            return false;
        }
        if (this.e != aVar.e) {
            return false;
        }
        if (this.f != aVar.f) {
            return false;
        }
        if (this.g != aVar.g) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2 = 1;
        int i3 = 0;
        int i4 = ((this.b ? 1 : 0) + (this.a * 31)) * 31;
        if (this.c) {
            i = 1;
        } else {
            i = 0;
        }
        i4 = (i + i4) * 31;
        if (this.d) {
            i = 1;
        } else {
            i = 0;
        }
        i = (i + i4) * 31;
        if (!this.e) {
            i2 = 0;
        }
        i = (((i + i2) * 31) + this.f.ordinal()) * 31;
        if (this.g != null) {
            i3 = this.g.hashCode();
        }
        return i + i3;
    }

    public String toString() {
        return String.format((Locale) null, "%d-%b-%b-%b-%b-%s-%s", new Object[]{Integer.valueOf(this.a), Boolean.valueOf(this.b), Boolean.valueOf(this.c), Boolean.valueOf(this.d), Boolean.valueOf(this.e), this.f.name(), this.g});
    }
}
