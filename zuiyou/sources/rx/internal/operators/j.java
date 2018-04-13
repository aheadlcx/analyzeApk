package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import rx.d$b;
import rx.e.c;
import rx.exceptions.MissingBackpressureException;
import rx.f;
import rx.g;
import rx.internal.schedulers.k;
import rx.internal.util.a.l;
import rx.internal.util.a.y;
import rx.internal.util.atomic.b;
import rx.internal.util.e;

public final class j<T> implements d$b<T, T> {
    private final g a;
    private final boolean b;
    private final int c;

    static final class a<T> extends rx.j<T> implements rx.b.a {
        final rx.j<? super T> a;
        final rx.g.a b;
        final boolean c;
        final Queue<Object> d;
        final int e;
        volatile boolean f;
        final AtomicLong g = new AtomicLong();
        final AtomicLong h = new AtomicLong();
        Throwable i;
        long j;

        public a(g gVar, rx.j<? super T> jVar, boolean z, int i) {
            this.a = jVar;
            this.b = gVar.a();
            this.c = z;
            if (i <= 0) {
                i = e.b;
            }
            this.e = i - (i >> 2);
            if (y.a()) {
                this.d = new l(i);
            } else {
                this.d = new b(i);
            }
            request((long) i);
        }

        void a() {
            rx.j jVar = this.a;
            jVar.setProducer(new f(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void request(long j) {
                    if (j > 0) {
                        a.a(this.a.g, j);
                        this.a.b();
                    }
                }
            });
            jVar.add(this.b);
            jVar.add(this);
        }

        public void onNext(T t) {
            if (!isUnsubscribed() && !this.f) {
                if (this.d.offer(NotificationLite.a((Object) t))) {
                    b();
                } else {
                    onError(new MissingBackpressureException());
                }
            }
        }

        public void onCompleted() {
            if (!isUnsubscribed() && !this.f) {
                this.f = true;
                b();
            }
        }

        public void onError(Throwable th) {
            if (isUnsubscribed() || this.f) {
                c.a(th);
                return;
            }
            this.i = th;
            this.f = true;
            b();
        }

        protected void b() {
            if (this.h.getAndIncrement() == 0) {
                this.b.a(this);
            }
        }

        public void call() {
            long j = this.j;
            Queue queue = this.d;
            rx.j jVar = this.a;
            long j2 = 1;
            do {
                long j3 = this.g.get();
                while (j3 != j) {
                    boolean z = this.f;
                    Object poll = queue.poll();
                    boolean z2 = poll == null;
                    if (!a(z, z2, jVar, queue)) {
                        if (z2) {
                            break;
                        }
                        jVar.onNext(NotificationLite.d(poll));
                        long j4 = j + 1;
                        if (j4 == ((long) this.e)) {
                            j = a.b(this.g, j4);
                            request(j4);
                            j4 = 0;
                        } else {
                            j = j3;
                        }
                        j3 = j;
                        j = j4;
                    } else {
                        return;
                    }
                }
                if (j3 != j || !a(this.f, queue.isEmpty(), jVar, queue)) {
                    this.j = j;
                    j2 = this.h.addAndGet(-j2);
                } else {
                    return;
                }
            } while (j2 != 0);
        }

        boolean a(boolean z, boolean z2, rx.j<? super T> jVar, Queue<Object> queue) {
            if (jVar.isUnsubscribed()) {
                queue.clear();
                return true;
            }
            if (z) {
                if (!this.c) {
                    Throwable th = this.i;
                    if (th != null) {
                        queue.clear();
                        try {
                            jVar.onError(th);
                            return true;
                        } finally {
                            this.b.unsubscribe();
                        }
                    } else if (z2) {
                        try {
                            jVar.onCompleted();
                            return true;
                        } finally {
                            this.b.unsubscribe();
                        }
                    }
                } else if (z2) {
                    Throwable th2 = this.i;
                    if (th2 != null) {
                        try {
                            jVar.onError(th2);
                        } catch (Throwable th3) {
                            this.b.unsubscribe();
                        }
                    } else {
                        jVar.onCompleted();
                    }
                    this.b.unsubscribe();
                }
            }
            return false;
        }
    }

    public /* synthetic */ Object call(Object obj) {
        return a((rx.j) obj);
    }

    public j(g gVar, boolean z, int i) {
        this.a = gVar;
        this.b = z;
        if (i <= 0) {
            i = e.b;
        }
        this.c = i;
    }

    public rx.j<? super T> a(rx.j<? super T> jVar) {
        if ((this.a instanceof rx.internal.schedulers.e) || (this.a instanceof k)) {
            return jVar;
        }
        rx.j aVar = new a(this.a, jVar, this.b, this.c);
        aVar.a();
        return aVar;
    }
}
