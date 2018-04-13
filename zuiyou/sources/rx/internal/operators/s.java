package rx.internal.operators;

import rx.d$b;
import rx.e.c;
import rx.i;
import rx.internal.producers.SingleProducer;
import rx.j;
import rx.k;

public final class s<T, R> implements rx.h.a<R> {
    final rx.h.a<T> a;
    final d$b<? extends R, ? super T> b;

    static final class a<T> extends i<T> {
        final j<? super T> a;

        a(j<? super T> jVar) {
            this.a = jVar;
        }

        public void a(T t) {
            this.a.setProducer(new SingleProducer(this.a, t));
        }

        public void a(Throwable th) {
            this.a.onError(th);
        }
    }

    public /* synthetic */ void call(Object obj) {
        a((i) obj);
    }

    public void a(i<? super R> iVar) {
        k aVar = new a(iVar);
        iVar.a(aVar);
        try {
            j jVar = (j) c.b(this.b).call(aVar);
            i a = a(jVar);
            jVar.onStart();
            this.a.call(a);
        } catch (Throwable th) {
            rx.exceptions.a.a(th, (i) iVar);
        }
    }

    public static <T> i<T> a(j<T> jVar) {
        Object aVar = new a(jVar);
        jVar.add(aVar);
        return aVar;
    }
}
