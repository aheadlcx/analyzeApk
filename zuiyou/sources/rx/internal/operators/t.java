package rx.internal.operators;

import rx.d$a;
import rx.h.a;
import rx.j;
import rx.k;

public final class t<T> implements d$a<T> {
    final a<T> a;

    public /* synthetic */ void call(Object obj) {
        a((j) obj);
    }

    public t(a<T> aVar) {
        this.a = aVar;
    }

    public void a(j<? super T> jVar) {
        k aVar = new a(jVar);
        jVar.add(aVar);
        this.a.call(aVar);
    }
}
