package com.nostra13.universalimageloader.core;

import android.content.res.Resources;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public final class c {
    private final int a;
    private final int b;
    private final int c;
    private final Drawable d;
    private final Drawable e;
    private final Drawable f;
    private final boolean g;
    private final boolean h;
    private final boolean i;
    private final ImageScaleType j;
    private final Options k;
    private final int l;
    private final boolean m;
    private final Object n;
    private final com.nostra13.universalimageloader.core.e.a o;
    private final com.nostra13.universalimageloader.core.e.a p;
    private final com.nostra13.universalimageloader.core.b.a q;
    private final Handler r;
    private final boolean s;

    public static class a {
        private int a;
        private int b;
        private int c;
        private Drawable d;
        private Drawable e;
        private Drawable f;
        private boolean g;
        private boolean h;
        private boolean i;
        private ImageScaleType j;
        private Options k;
        private int l;
        private boolean m;
        private Object n;
        private com.nostra13.universalimageloader.core.e.a o;
        private com.nostra13.universalimageloader.core.e.a p;
        private com.nostra13.universalimageloader.core.b.a q;
        private Handler r;
        private boolean s;

        public a() {
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = false;
            this.h = false;
            this.i = false;
            this.j = ImageScaleType.IN_SAMPLE_POWER_OF_2;
            this.k = new Options();
            this.l = 0;
            this.m = false;
            this.n = null;
            this.o = null;
            this.p = null;
            this.q = a.c();
            this.r = null;
            this.s = false;
            this.k.inPurgeable = true;
            this.k.inInputShareable = true;
        }

        public a a(int i) {
            this.a = i;
            return this;
        }

        public a a(Drawable drawable) {
            this.d = drawable;
            return this;
        }

        public a b(int i) {
            this.b = i;
            return this;
        }

        public a c(int i) {
            this.c = i;
            return this;
        }

        public a b(Drawable drawable) {
            this.f = drawable;
            return this;
        }

        public a a(boolean z) {
            this.h = z;
            return this;
        }

        public a b(boolean z) {
            this.i = z;
            return this;
        }

        public a a(ImageScaleType imageScaleType) {
            this.j = imageScaleType;
            return this;
        }

        public a a(Config config) {
            if (config == null) {
                throw new IllegalArgumentException("bitmapConfig can't be null");
            }
            this.k.inPreferredConfig = config;
            return this;
        }

        public a c(boolean z) {
            this.m = z;
            return this;
        }

        public a a(com.nostra13.universalimageloader.core.b.a aVar) {
            if (aVar == null) {
                throw new IllegalArgumentException("displayer can't be null");
            }
            this.q = aVar;
            return this;
        }

        public a a(c cVar) {
            this.a = cVar.a;
            this.b = cVar.b;
            this.c = cVar.c;
            this.d = cVar.d;
            this.e = cVar.e;
            this.f = cVar.f;
            this.g = cVar.g;
            this.h = cVar.h;
            this.i = cVar.i;
            this.j = cVar.j;
            this.k = cVar.k;
            this.l = cVar.l;
            this.m = cVar.m;
            this.n = cVar.n;
            this.o = cVar.o;
            this.p = cVar.p;
            this.q = cVar.q;
            this.r = cVar.r;
            this.s = cVar.s;
            return this;
        }

        public c a() {
            return new c();
        }
    }

    private c(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
        this.l = aVar.l;
        this.m = aVar.m;
        this.n = aVar.n;
        this.o = aVar.o;
        this.p = aVar.p;
        this.q = aVar.q;
        this.r = aVar.r;
        this.s = aVar.s;
    }

    public boolean a() {
        return (this.d == null && this.a == 0) ? false : true;
    }

    public boolean b() {
        return (this.e == null && this.b == 0) ? false : true;
    }

    public boolean c() {
        return (this.f == null && this.c == 0) ? false : true;
    }

    public boolean d() {
        return this.o != null;
    }

    public boolean e() {
        return this.p != null;
    }

    public boolean f() {
        return this.l > 0;
    }

    public Drawable a(Resources resources) {
        return this.a != 0 ? resources.getDrawable(this.a) : this.d;
    }

    public Drawable b(Resources resources) {
        return this.b != 0 ? resources.getDrawable(this.b) : this.e;
    }

    public Drawable c(Resources resources) {
        return this.c != 0 ? resources.getDrawable(this.c) : this.f;
    }

    public boolean g() {
        return this.g;
    }

    public boolean h() {
        return this.h;
    }

    public boolean i() {
        return this.i;
    }

    public ImageScaleType j() {
        return this.j;
    }

    public Options k() {
        return this.k;
    }

    public int l() {
        return this.l;
    }

    public boolean m() {
        return this.m;
    }

    public Object n() {
        return this.n;
    }

    public com.nostra13.universalimageloader.core.e.a o() {
        return this.o;
    }

    public com.nostra13.universalimageloader.core.e.a p() {
        return this.p;
    }

    public com.nostra13.universalimageloader.core.b.a q() {
        return this.q;
    }

    public Handler r() {
        return this.r;
    }

    boolean s() {
        return this.s;
    }

    public static c t() {
        return new a().a();
    }
}
