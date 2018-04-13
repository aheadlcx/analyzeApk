package retrofit2.adapter.rxjava;

import retrofit2.d;
import retrofit2.l;
import rx.d$a;
import rx.exceptions.a;
import rx.j;

final class b<T> implements d$a<l<T>> {
    private final retrofit2.b<T> a;

    public /* synthetic */ void call(Object obj) {
        a((j) obj);
    }

    b(retrofit2.b<T> bVar) {
        this.a = bVar;
    }

    public void a(j<? super l<T>> jVar) {
        retrofit2.b d = this.a.d();
        final CallArbiter callArbiter = new CallArbiter(d, jVar);
        jVar.add(callArbiter);
        jVar.setProducer(callArbiter);
        d.a(new d<T>(this) {
            final /* synthetic */ b b;

            public void a(retrofit2.b<T> bVar, l<T> lVar) {
                callArbiter.emitResponse(lVar);
            }

            public void a(retrofit2.b<T> bVar, Throwable th) {
                a.b(th);
                callArbiter.emitError(th);
            }
        });
    }
}
