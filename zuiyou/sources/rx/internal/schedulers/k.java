package rx.internal.schedulers;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import rx.g;
import rx.g.e;

public final class k extends g {
    public static final k a = new k();

    static final class a extends rx.g.a implements rx.k {
        final AtomicInteger a = new AtomicInteger();
        final PriorityBlockingQueue<b> b = new PriorityBlockingQueue();
        private final rx.g.a c = new rx.g.a();
        private final AtomicInteger d = new AtomicInteger();

        a() {
        }

        public rx.k a(rx.b.a aVar) {
            return a(aVar, a());
        }

        public rx.k a(rx.b.a aVar, long j, TimeUnit timeUnit) {
            long a = a() + timeUnit.toMillis(j);
            return a(new j(aVar, this, a), a);
        }

        private rx.k a(rx.b.a aVar, long j) {
            if (this.c.isUnsubscribed()) {
                return e.a();
            }
            final b bVar = new b(aVar, Long.valueOf(j), this.a.incrementAndGet());
            this.b.add(bVar);
            if (this.d.getAndIncrement() != 0) {
                return e.a(new rx.b.a(this) {
                    final /* synthetic */ a b;

                    public void call() {
                        this.b.b.remove(bVar);
                    }
                });
            }
            do {
                bVar = (b) this.b.poll();
                if (bVar != null) {
                    bVar.a.call();
                }
            } while (this.d.decrementAndGet() > 0);
            return e.a();
        }

        public void unsubscribe() {
            this.c.unsubscribe();
        }

        public boolean isUnsubscribed() {
            return this.c.isUnsubscribed();
        }
    }

    static final class b implements Comparable<b> {
        final rx.b.a a;
        final Long b;
        final int c;

        public /* synthetic */ int compareTo(Object obj) {
            return a((b) obj);
        }

        b(rx.b.a aVar, Long l, int i) {
            this.a = aVar;
            this.b = l;
            this.c = i;
        }

        public int a(b bVar) {
            int compareTo = this.b.compareTo(bVar.b);
            if (compareTo == 0) {
                return k.a(this.c, bVar.c);
            }
            return compareTo;
        }
    }

    public rx.g.a a() {
        return new a();
    }

    private k() {
    }

    static int a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }
}
