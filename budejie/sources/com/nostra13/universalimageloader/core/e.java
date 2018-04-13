package com.nostra13.universalimageloader.core;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.nostra13.universalimageloader.a.a.b;
import com.nostra13.universalimageloader.a.b.a.c;
import com.nostra13.universalimageloader.b.f;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.util.concurrent.Executor;

public final class e {
    final Resources a;
    final int b;
    final int c;
    final int d;
    final int e;
    final com.nostra13.universalimageloader.core.e.a f;
    final Executor g;
    final Executor h;
    final boolean i;
    final boolean j;
    final int k;
    final int l;
    final QueueProcessingType m;
    final c n;
    final com.nostra13.universalimageloader.a.b.b.a o;
    final b p;
    final ImageDownloader q;
    final com.nostra13.universalimageloader.core.a.b r;
    final c s;
    final ImageDownloader t;
    final ImageDownloader u;

    public static class a {
        public static final QueueProcessingType a = QueueProcessingType.FIFO;
        private Context b;
        private int c = 0;
        private int d = 0;
        private int e = 0;
        private int f = 0;
        private com.nostra13.universalimageloader.core.e.a g = null;
        private Executor h = null;
        private Executor i = null;
        private boolean j = false;
        private boolean k = false;
        private int l = 3;
        private int m = 4;
        private boolean n = false;
        private QueueProcessingType o = a;
        private int p = 0;
        private long q = 0;
        private int r = 0;
        private c s = null;
        private com.nostra13.universalimageloader.a.b.b.a t = null;
        private b u = null;
        private com.nostra13.universalimageloader.a.a.b.a v = null;
        private ImageDownloader w = null;
        private com.nostra13.universalimageloader.core.a.b x;
        private c y = null;
        private boolean z = false;

        public a(Context context) {
            this.b = context.getApplicationContext();
        }

        public a a(int i) {
            if (!(this.h == null && this.i == null)) {
                com.nostra13.universalimageloader.b.e.c("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.", new Object[0]);
            }
            this.l = i;
            return this;
        }

        public a b(int i) {
            if (!(this.h == null && this.i == null)) {
                com.nostra13.universalimageloader.b.e.c("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.", new Object[0]);
            }
            if (i < 1) {
                this.m = 1;
            } else if (i > 10) {
                this.m = 10;
            } else {
                this.m = i;
            }
            return this;
        }

        public a a() {
            this.n = true;
            return this;
        }

        public a c(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("memoryCacheSize must be a positive number");
            }
            if (this.s != null) {
                com.nostra13.universalimageloader.b.e.c("memoryCache() and memoryCacheSize() calls overlap each other", new Object[0]);
            }
            if (this.t != null) {
                com.nostra13.universalimageloader.b.e.c("memoryCache() and gif memoryCacheSize() calls overlap each other", new Object[0]);
            }
            this.p = i;
            return this;
        }

        public a a(c cVar) {
            if (this.p != 0) {
                com.nostra13.universalimageloader.b.e.c("memoryCache() and memoryCacheSize() calls overlap each other", new Object[0]);
            }
            this.s = cVar;
            return this;
        }

        public a d(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("maxCacheSize must be a positive number");
            }
            if (this.u != null) {
                com.nostra13.universalimageloader.b.e.c("diskCache(), diskCacheSize() and diskCacheFileCount calls overlap each other", new Object[0]);
            }
            this.q = (long) i;
            return this;
        }

        public a a(com.nostra13.universalimageloader.a.a.b.a aVar) {
            if (this.u != null) {
                com.nostra13.universalimageloader.b.e.c("diskCache() and diskCacheFileNameGenerator() calls overlap each other", new Object[0]);
            }
            this.v = aVar;
            return this;
        }

        public a a(b bVar) {
            if (this.q > 0 || this.r > 0) {
                com.nostra13.universalimageloader.b.e.c("diskCache(), diskCacheSize() and diskCacheFileCount calls overlap each other", new Object[0]);
            }
            if (this.v != null) {
                com.nostra13.universalimageloader.b.e.c("diskCache() and diskCacheFileNameGenerator() calls overlap each other", new Object[0]);
            }
            this.u = bVar;
            return this;
        }

        public a a(ImageDownloader imageDownloader) {
            this.w = imageDownloader;
            return this;
        }

        public a a(com.nostra13.universalimageloader.core.a.b bVar) {
            this.x = bVar;
            return this;
        }

        public a b() {
            this.z = true;
            return this;
        }

        public e c() {
            d();
            return new e();
        }

        private void d() {
            if (this.h == null) {
                this.h = a.a(this.l, this.m, this.o);
            } else {
                this.j = true;
            }
            if (this.i == null) {
                this.i = a.a(this.l, this.m, this.o);
            } else {
                this.k = true;
            }
            if (this.u == null) {
                if (this.v == null) {
                    this.v = a.b();
                }
                this.u = a.a(this.b, this.v, this.q, this.r);
            }
            if (this.s == null) {
                this.s = a.a(this.p);
            }
            if (this.t == null) {
                this.t = a.b(this.p);
            }
            if (this.n) {
                this.s = new com.nostra13.universalimageloader.a.b.a.a.a(this.s, f.a());
                this.t = new com.nostra13.universalimageloader.a.b.b.a.a(this.t, f.a());
            }
            if (this.w == null) {
                this.w = a.a(this.b);
            }
            if (this.x == null) {
                this.x = a.a(this.z);
            }
            if (this.y == null) {
                this.y = c.t();
            }
        }
    }

    private e(a aVar) {
        this.a = aVar.b.getResources();
        this.b = aVar.c;
        this.c = aVar.d;
        this.d = aVar.e;
        this.e = aVar.f;
        this.f = aVar.g;
        this.g = aVar.h;
        this.h = aVar.i;
        this.k = aVar.l;
        this.l = aVar.m;
        this.m = aVar.o;
        this.p = aVar.u;
        this.n = aVar.s;
        this.o = aVar.t;
        this.s = aVar.y;
        this.q = aVar.w;
        this.r = aVar.x;
        this.i = aVar.j;
        this.j = aVar.k;
        this.t = new e$b(this.q);
        this.u = new e$c(this.q);
        com.nostra13.universalimageloader.b.e.a(aVar.z);
    }

    com.nostra13.universalimageloader.core.assist.c a() {
        DisplayMetrics displayMetrics = this.a.getDisplayMetrics();
        int i = this.b;
        if (i <= 0) {
            i = displayMetrics.widthPixels;
        }
        int i2 = this.c;
        if (i2 <= 0) {
            i2 = displayMetrics.heightPixels;
        }
        return new com.nostra13.universalimageloader.core.assist.c(i, i2);
    }
}
