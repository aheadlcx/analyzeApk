package com.budejie.www.activity.video.barrage.danmaku.b;

import com.budejie.www.activity.video.barrage.danmaku.model.k;
import com.budejie.www.activity.video.barrage.danmaku.model.l;

public interface a {

    public static class a {
        public int a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public long h;
        public long i;
        public long j;
        public boolean k;
        public long l;
        public long m;
        public long n;

        public int a(int i) {
            this.f += i;
            return this.f;
        }

        public int a(int i, int i2) {
            switch (i) {
                case 1:
                    this.a += i2;
                    return this.a;
                case 4:
                    this.d += i2;
                    return this.d;
                case 5:
                    this.c += i2;
                    return this.c;
                case 6:
                    this.b += i2;
                    return this.b;
                case 7:
                    this.e += i2;
                    return this.e;
                default:
                    return 0;
            }
        }

        public void a() {
            this.f = 0;
            this.e = 0;
            this.d = 0;
            this.c = 0;
            this.b = 0;
            this.a = 0;
            this.h = 0;
            this.j = 0;
            this.i = 0;
            this.l = 0;
            this.k = false;
        }

        public void a(a aVar) {
            if (aVar != null) {
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
            }
        }
    }

    a a(l lVar, k kVar, long j);

    void a();

    void a(boolean z);

    void b();

    void c();
}
