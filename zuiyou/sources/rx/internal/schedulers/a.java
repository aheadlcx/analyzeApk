package rx.internal.schedulers;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import rx.g;
import rx.g.e;
import rx.internal.util.RxThreadFactory;
import rx.k;

public final class a extends g implements i {
    static final c a = new c(RxThreadFactory.NONE);
    static final a b = new a(null, 0, null);
    private static final long e = ((long) Integer.getInteger("rx.io-scheduler.keepalive", 60).intValue());
    private static final TimeUnit f = TimeUnit.SECONDS;
    final ThreadFactory c;
    final AtomicReference<a> d = new AtomicReference(b);

    static final class a {
        private final ThreadFactory a;
        private final long b;
        private final ConcurrentLinkedQueue<c> c;
        private final rx.g.b d;
        private final ScheduledExecutorService e;
        private final Future<?> f;

        a(final ThreadFactory threadFactory, long j, TimeUnit timeUnit) {
            Future scheduleWithFixedDelay;
            ScheduledExecutorService scheduledExecutorService = null;
            this.a = threadFactory;
            this.b = timeUnit != null ? timeUnit.toNanos(j) : 0;
            this.c = new ConcurrentLinkedQueue();
            this.d = new rx.g.b();
            if (timeUnit != null) {
                ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, new ThreadFactory(this) {
                    final /* synthetic */ a b;

                    public Thread newThread(Runnable runnable) {
                        Thread newThread = threadFactory.newThread(runnable);
                        newThread.setName(newThread.getName() + " (Evictor)");
                        return newThread;
                    }
                });
                g.b(newScheduledThreadPool);
                scheduledExecutorService = newScheduledThreadPool;
                scheduleWithFixedDelay = newScheduledThreadPool.scheduleWithFixedDelay(new Runnable(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b();
                    }
                }, this.b, this.b, TimeUnit.NANOSECONDS);
            } else {
                scheduleWithFixedDelay = null;
            }
            this.e = scheduledExecutorService;
            this.f = scheduleWithFixedDelay;
        }

        c a() {
            if (this.d.isUnsubscribed()) {
                return a.a;
            }
            while (!this.c.isEmpty()) {
                c cVar = (c) this.c.poll();
                if (cVar != null) {
                    return cVar;
                }
            }
            k cVar2 = new c(this.a);
            this.d.a(cVar2);
            return cVar2;
        }

        void a(c cVar) {
            cVar.a(c() + this.b);
            this.c.offer(cVar);
        }

        void b() {
            if (!this.c.isEmpty()) {
                long c = c();
                Iterator it = this.c.iterator();
                while (it.hasNext()) {
                    c cVar = (c) it.next();
                    if (cVar.b() > c) {
                        return;
                    }
                    if (this.c.remove(cVar)) {
                        this.d.b(cVar);
                    }
                }
            }
        }

        long c() {
            return System.nanoTime();
        }

        void d() {
            try {
                if (this.f != null) {
                    this.f.cancel(true);
                }
                if (this.e != null) {
                    this.e.shutdownNow();
                }
                this.d.unsubscribe();
            } catch (Throwable th) {
                this.d.unsubscribe();
            }
        }
    }

    static final class b extends rx.g.a implements rx.b.a {
        final AtomicBoolean a;
        private final rx.g.b b = new rx.g.b();
        private final a c;
        private final c d;

        b(a aVar) {
            this.c = aVar;
            this.a = new AtomicBoolean();
            this.d = aVar.a();
        }

        public void unsubscribe() {
            if (this.a.compareAndSet(false, true)) {
                this.d.a((rx.b.a) this);
            }
            this.b.unsubscribe();
        }

        public void call() {
            this.c.a(this.d);
        }

        public boolean isUnsubscribed() {
            return this.b.isUnsubscribed();
        }

        public k a(rx.b.a aVar) {
            return a(aVar, 0, null);
        }

        public k a(final rx.b.a aVar, long j, TimeUnit timeUnit) {
            if (this.b.isUnsubscribed()) {
                return e.a();
            }
            k b = this.d.b(new rx.b.a(this) {
                final /* synthetic */ b b;

                public void call() {
                    if (!this.b.isUnsubscribed()) {
                        aVar.call();
                    }
                }
            }, j, timeUnit);
            this.b.a(b);
            b.addParent(this.b);
            return b;
        }
    }

    static final class c extends g {
        private long c = 0;

        c(ThreadFactory threadFactory) {
            super(threadFactory);
        }

        public long b() {
            return this.c;
        }

        public void a(long j) {
            this.c = j;
        }
    }

    static {
        a.unsubscribe();
        b.d();
    }

    public a(ThreadFactory threadFactory) {
        this.c = threadFactory;
        c();
    }

    public void c() {
        a aVar = new a(this.c, e, f);
        if (!this.d.compareAndSet(b, aVar)) {
            aVar.d();
        }
    }

    public void d() {
        a aVar;
        do {
            aVar = (a) this.d.get();
            if (aVar == b) {
                return;
            }
        } while (!this.d.compareAndSet(aVar, b));
        aVar.d();
    }

    public rx.g.a a() {
        return new b((a) this.d.get());
    }
}
