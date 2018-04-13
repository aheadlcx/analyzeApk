package rx.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.g;
import rx.g.e;
import rx.internal.util.RxThreadFactory;
import rx.internal.util.f;
import rx.k;

public final class b extends g implements i {
    static final int a;
    static final c b = new c(RxThreadFactory.NONE);
    static final b c = new b(null, 0);
    final ThreadFactory d;
    final AtomicReference<b> e = new AtomicReference(c);

    static final class a extends rx.g.a {
        private final f a = new f();
        private final rx.g.b b = new rx.g.b();
        private final f c = new f(this.a, this.b);
        private final c d;

        a(c cVar) {
            this.d = cVar;
        }

        public void unsubscribe() {
            this.c.unsubscribe();
        }

        public boolean isUnsubscribed() {
            return this.c.isUnsubscribed();
        }

        public k a(final rx.b.a aVar) {
            if (isUnsubscribed()) {
                return e.a();
            }
            return this.d.a(new rx.b.a(this) {
                final /* synthetic */ a b;

                public void call() {
                    if (!this.b.isUnsubscribed()) {
                        aVar.call();
                    }
                }
            }, 0, null, this.a);
        }

        public k a(final rx.b.a aVar, long j, TimeUnit timeUnit) {
            if (isUnsubscribed()) {
                return e.a();
            }
            return this.d.a(new rx.b.a(this) {
                final /* synthetic */ a b;

                public void call() {
                    if (!this.b.isUnsubscribed()) {
                        aVar.call();
                    }
                }
            }, j, timeUnit, this.b);
        }
    }

    static final class b {
        final int a;
        final c[] b;
        long c;

        b(ThreadFactory threadFactory, int i) {
            this.a = i;
            this.b = new c[i];
            for (int i2 = 0; i2 < i; i2++) {
                this.b[i2] = new c(threadFactory);
            }
        }

        public c a() {
            int i = this.a;
            if (i == 0) {
                return b.b;
            }
            c[] cVarArr = this.b;
            long j = this.c;
            this.c = 1 + j;
            return cVarArr[(int) (j % ((long) i))];
        }

        public void b() {
            for (c unsubscribe : this.b) {
                unsubscribe.unsubscribe();
            }
        }
    }

    static final class c extends g {
        c(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }

    static {
        int intValue = Integer.getInteger("rx.scheduler.max-computation-threads", 0).intValue();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (intValue <= 0 || intValue > availableProcessors) {
            intValue = availableProcessors;
        }
        a = intValue;
        b.unsubscribe();
    }

    public b(ThreadFactory threadFactory) {
        this.d = threadFactory;
        c();
    }

    public rx.g.a a() {
        return new a(((b) this.e.get()).a());
    }

    public void c() {
        b bVar = new b(this.d, a);
        if (!this.e.compareAndSet(c, bVar)) {
            bVar.b();
        }
    }

    public void d() {
        b bVar;
        do {
            bVar = (b) this.e.get();
            if (bVar == c) {
                return;
            }
        } while (!this.e.compareAndSet(bVar, c));
        bVar.b();
    }

    public k a(rx.b.a aVar) {
        return ((b) this.e.get()).a().b(aVar, -1, TimeUnit.NANOSECONDS);
    }
}
