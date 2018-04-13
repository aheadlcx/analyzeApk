package master.flame.danmaku.danmaku.model;

import java.util.Comparator;

public interface l {

    public static abstract class b<Progress, Result> {
        public abstract int a(Progress progress);

        public void c() {
        }

        public void d() {
        }

        public Result b() {
            return null;
        }
    }

    public static abstract class c<Progress> extends b<Progress, Void> {
    }

    public static class a implements Comparator<d> {
        protected boolean a;

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((d) obj, (d) obj2);
        }

        public a(boolean z) {
            a(z);
        }

        public void a(boolean z) {
            this.a = z;
        }

        public int a(d dVar, d dVar2) {
            if (this.a && master.flame.danmaku.danmaku.c.a.a(dVar, dVar2)) {
                return 0;
            }
            return master.flame.danmaku.danmaku.c.a.b(dVar, dVar2);
        }
    }

    public static class d extends a {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((d) obj, (d) obj2);
        }

        public d(boolean z) {
            super(z);
        }

        public int a(d dVar, d dVar2) {
            return super.a(dVar, dVar2);
        }
    }

    public static class e extends a {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((d) obj, (d) obj2);
        }

        public e(boolean z) {
            super(z);
        }

        public int a(d dVar, d dVar2) {
            if (this.a && master.flame.danmaku.danmaku.c.a.a(dVar, dVar2)) {
                return 0;
            }
            return Float.compare(dVar.l(), dVar2.l());
        }
    }

    public static class f extends a {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((d) obj, (d) obj2);
        }

        public f(boolean z) {
            super(z);
        }

        public int a(d dVar, d dVar2) {
            if (this.a && master.flame.danmaku.danmaku.c.a.a(dVar, dVar2)) {
                return 0;
            }
            return Float.compare(dVar2.l(), dVar.l());
        }
    }

    int a();

    l a(long j, long j2);

    void a(b<? super d, ?> bVar);

    boolean a(d dVar);

    l b(long j, long j2);

    void b();

    void b(b<? super d, ?> bVar);

    boolean b(d dVar);

    d c();

    boolean c(d dVar);

    d d();

    boolean e();
}
