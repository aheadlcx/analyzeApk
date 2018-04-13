package rx.internal.operators;

import rx.d$a;
import rx.d$b;
import rx.exceptions.a;
import rx.j;

public final class c<T, R> implements d$a<R> {
    final d$a<T> a;
    final d$b<? extends R, ? super T> b;

    public /* synthetic */ void call(Object obj) {
        a((j) obj);
    }

    public c(d$a<T> d_a, d$b<? extends R, ? super T> d_b) {
        this.a = d_a;
        this.b = d_b;
    }

    public void a(j<? super R> jVar) {
        j jVar2;
        try {
            jVar2 = (j) rx.e.c.a(this.b).call(jVar);
            jVar2.onStart();
            this.a.call(jVar2);
        } catch (Throwable th) {
            a.b(th);
            jVar.onError(th);
        }
    }
}
