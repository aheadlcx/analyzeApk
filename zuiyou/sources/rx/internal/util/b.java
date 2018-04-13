package rx.internal.util;

import rx.e;
import rx.j;

public final class b<T> extends j<T> {
    final e<? super T> a;

    public b(e<? super T> eVar) {
        this.a = eVar;
    }

    public void onNext(T t) {
        this.a.onNext(t);
    }

    public void onError(Throwable th) {
        this.a.onError(th);
    }

    public void onCompleted() {
        this.a.onCompleted();
    }
}
