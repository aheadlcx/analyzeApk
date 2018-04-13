package rx.internal.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import rx.a.d;
import rx.d$b;
import rx.f;
import rx.internal.util.BackpressureDrainManager;
import rx.j;

public class k<T> implements d$b<T, T> {
    private final Long a = null;
    private final rx.b.a b = null;
    private final d c = rx.a.b;

    static final class a<T> extends j<T> implements rx.internal.util.BackpressureDrainManager.a {
        private final ConcurrentLinkedQueue<Object> a = new ConcurrentLinkedQueue();
        private final AtomicLong b;
        private final j<? super T> c;
        private final AtomicBoolean d = new AtomicBoolean(false);
        private final BackpressureDrainManager e;
        private final rx.b.a f;
        private final d g;

        public a(j<? super T> jVar, Long l, rx.b.a aVar, d dVar) {
            this.c = jVar;
            this.b = l != null ? new AtomicLong(l.longValue()) : null;
            this.f = aVar;
            this.e = new BackpressureDrainManager(this);
            this.g = dVar;
        }

        public void onStart() {
            request(Long.MAX_VALUE);
        }

        public void onCompleted() {
            if (!this.d.get()) {
                this.e.terminateAndDrain();
            }
        }

        public void onError(Throwable th) {
            if (!this.d.get()) {
                this.e.terminateAndDrain(th);
            }
        }

        public void onNext(T t) {
            if (d()) {
                this.a.offer(NotificationLite.a((Object) t));
                this.e.drain();
            }
        }

        public boolean a(Object obj) {
            return NotificationLite.a(this.c, obj);
        }

        public void a(Throwable th) {
            if (th != null) {
                this.c.onError(th);
            } else {
                this.c.onCompleted();
            }
        }

        public Object a() {
            return this.a.peek();
        }

        public Object b() {
            Object poll = this.a.poll();
            if (!(this.b == null || poll == null)) {
                this.b.incrementAndGet();
            }
            return poll;
        }

        private boolean d() {
            if (this.b == null) {
                return true;
            }
            long j;
            do {
                j = this.b.get();
                if (j <= 0) {
                    boolean z;
                    try {
                        z = this.g.a() && b() != null;
                    } catch (Throwable e) {
                        if (this.d.compareAndSet(false, true)) {
                            unsubscribe();
                            this.c.onError(e);
                        }
                        z = false;
                    }
                    if (this.f != null) {
                        try {
                            this.f.call();
                        } catch (Throwable e2) {
                            rx.exceptions.a.b(e2);
                            this.e.terminateAndDrain(e2);
                            return false;
                        }
                    }
                    if (!z) {
                        return false;
                    }
                }
            } while (!this.b.compareAndSet(j, j - 1));
            return true;
        }

        protected f c() {
            return this.e;
        }
    }

    static final class b {
        static final k<?> a = new k();
    }

    public /* synthetic */ Object call(Object obj) {
        return a((j) obj);
    }

    public static <T> k<T> a() {
        return b.a;
    }

    k() {
    }

    public j<? super T> a(j<? super T> jVar) {
        j<? super T> aVar = new a(jVar, this.a, this.b, this.c);
        jVar.add(aVar);
        jVar.setProducer(aVar.c());
        return aVar;
    }
}
