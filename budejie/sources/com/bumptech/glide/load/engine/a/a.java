package com.bumptech.glide.load.engine.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.bumptech.glide.i.h;

class a implements g {
    private final b a = new b();
    private final e<a, Bitmap> b = new e();

    static class a implements h {
        private final b a;
        private int b;
        private int c;
        private Config d;

        public a(b bVar) {
            this.a = bVar;
        }

        public void a(int i, int i2, Config config) {
            this.b = i;
            this.c = i2;
            this.d = config;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (this.b == aVar.b && this.c == aVar.c && this.d == aVar.d) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.d != null ? this.d.hashCode() : 0) + (((this.b * 31) + this.c) * 31);
        }

        public String toString() {
            return a.d(this.b, this.c, this.d);
        }

        public void a() {
            this.a.a(this);
        }
    }

    static class b extends b<a> {
        b() {
        }

        protected /* synthetic */ h b() {
            return a();
        }

        public a a(int i, int i2, Config config) {
            a aVar = (a) c();
            aVar.a(i, i2, config);
            return aVar;
        }

        protected a a() {
            return new a(this);
        }
    }

    a() {
    }

    public void a(Bitmap bitmap) {
        this.b.a(this.a.a(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig()), bitmap);
    }

    public Bitmap a(int i, int i2, Config config) {
        return (Bitmap) this.b.a(this.a.a(i, i2, config));
    }

    public Bitmap a() {
        return (Bitmap) this.b.a();
    }

    public String b(Bitmap bitmap) {
        return d(bitmap);
    }

    public String b(int i, int i2, Config config) {
        return d(i, i2, config);
    }

    public int c(Bitmap bitmap) {
        return h.a(bitmap);
    }

    public String toString() {
        return "AttributeStrategy:\n  " + this.b;
    }

    private static String d(Bitmap bitmap) {
        return d(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
    }

    private static String d(int i, int i2, Config config) {
        return "[" + i + "x" + i2 + "], " + config;
    }
}
