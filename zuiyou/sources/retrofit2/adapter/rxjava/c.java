package retrofit2.adapter.rxjava;

import retrofit2.b;
import retrofit2.l;
import rx.d$a;
import rx.exceptions.a;
import rx.j;

final class c<T> implements d$a<l<T>> {
    private final b<T> a;

    public /* synthetic */ void call(Object obj) {
        a((j) obj);
    }

    c(b<T> bVar) {
        this.a = bVar;
    }

    public void a(j<? super l<T>> jVar) {
        b d = this.a.d();
        CallArbiter callArbiter = new CallArbiter(d, jVar);
        jVar.add(callArbiter);
        jVar.setProducer(callArbiter);
        try {
            callArbiter.emitResponse(d.a());
        } catch (Throwable th) {
            a.b(th);
            callArbiter.emitError(th);
        }
    }
}
