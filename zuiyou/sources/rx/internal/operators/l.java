package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.d$b;
import rx.d.c;
import rx.e;
import rx.g;
import rx.j;

public final class l<T> implements d$b<T, T> {
    final long a;
    final TimeUnit b;
    final g c;

    static final class a<T> extends j<T> implements rx.b.a {
        private static final Object c = new Object();
        final AtomicReference<Object> a = new AtomicReference(c);
        private final j<? super T> b;

        public a(j<? super T> jVar) {
            this.b = jVar;
        }

        public void onStart() {
            request(Long.MAX_VALUE);
        }

        public void onNext(T t) {
            this.a.set(t);
        }

        public void onError(Throwable th) {
            this.b.onError(th);
            unsubscribe();
        }

        public void onCompleted() {
            a();
            this.b.onCompleted();
            unsubscribe();
        }

        public void call() {
            a();
        }

        private void a() {
            Object andSet = this.a.getAndSet(c);
            if (andSet != c) {
                try {
                    this.b.onNext(andSet);
                } catch (Throwable th) {
                    rx.exceptions.a.a(th, (e) this);
                }
            }
        }
    }

    public /* synthetic */ Object call(Object obj) {
        return a((j) obj);
    }

    public l(long j, TimeUnit timeUnit, g gVar) {
        this.a = j;
        this.b = timeUnit;
        this.c = gVar;
    }

    public j<? super T> a(j<? super T> jVar) {
        j cVar = new c(jVar);
        Object a = this.c.a();
        jVar.add(a);
        j<? super T> aVar = new a(cVar);
        jVar.add(aVar);
        a.a(aVar, this.a, this.a, this.b);
        return aVar;
    }
}
