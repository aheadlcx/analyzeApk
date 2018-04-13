package master.flame.danmaku.danmaku.b;

import master.flame.danmaku.danmaku.model.android.e;
import master.flame.danmaku.danmaku.model.d;
import master.flame.danmaku.danmaku.model.f;
import master.flame.danmaku.danmaku.model.k;
import master.flame.danmaku.danmaku.model.l;
import master.flame.danmaku.danmaku.model.m;

public interface a {

    public interface a {
        void a(d dVar);
    }

    public static class b {
        public boolean a;
        public f b = new f();
        public int c;
        public int d;
        public d e;
        public int f;
        public int g;
        public int h;
        public int i;
        public int j;
        public int k;
        public int l;
        public long m;
        public long n;
        public long o;
        public boolean p;
        public long q;
        public long r;
        public long s;
        private l t = new e(4);
        private boolean u;

        public int a(int i) {
            this.k += i;
            return this.k;
        }

        public int a(int i, int i2) {
            switch (i) {
                case 1:
                    this.f += i2;
                    return this.f;
                case 4:
                    this.i += i2;
                    return this.i;
                case 5:
                    this.h += i2;
                    return this.h;
                case 6:
                    this.g += i2;
                    return this.g;
                case 7:
                    this.j += i2;
                    return this.j;
                default:
                    return 0;
            }
        }

        public void a() {
            this.l = this.k;
            this.k = 0;
            this.j = 0;
            this.i = 0;
            this.h = 0;
            this.g = 0;
            this.f = 0;
            this.m = 0;
            this.o = 0;
            this.n = 0;
            this.q = 0;
            this.p = false;
            synchronized (this) {
                this.t.b();
            }
        }

        public void a(b bVar) {
            if (bVar != null) {
                this.l = bVar.l;
                this.f = bVar.f;
                this.g = bVar.g;
                this.h = bVar.h;
                this.i = bVar.i;
                this.j = bVar.j;
                this.k = bVar.k;
                this.m = bVar.m;
                this.n = bVar.n;
                this.o = bVar.o;
                this.p = bVar.p;
                this.q = bVar.q;
                this.r = bVar.r;
                this.s = bVar.s;
            }
        }

        public void a(d dVar) {
            if (!this.u) {
                this.t.a(dVar);
            }
        }

        public l b() {
            l lVar;
            this.u = true;
            synchronized (this) {
                lVar = this.t;
                this.t = new e(4);
            }
            this.u = false;
            return lVar;
        }
    }

    void a();

    void a(a aVar);

    void a(k kVar);

    void a(m mVar, l lVar, long j, b bVar);

    void a(boolean z);

    void b();

    void b(boolean z);

    void c();
}
