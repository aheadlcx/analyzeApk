package rx.internal.operators;

import rx.b.a;
import rx.d$b;
import rx.d.d;
import rx.g.e;
import rx.j;

public class i<T> implements d$b<T, T> {
    private final a a;

    public /* synthetic */ Object call(Object obj) {
        return a((j) obj);
    }

    public i(a aVar) {
        this.a = aVar;
    }

    public j<? super T> a(j<? super T> jVar) {
        jVar.add(e.a(this.a));
        return d.a(jVar);
    }
}
