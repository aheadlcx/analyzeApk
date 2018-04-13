package rx.internal.util;

import rx.b.b;
import rx.j;

public final class a<T> extends j<T> {
    final b<? super T> a;
    final b<Throwable> b;
    final rx.b.a c;

    public a(b<? super T> bVar, b<Throwable> bVar2, rx.b.a aVar) {
        this.a = bVar;
        this.b = bVar2;
        this.c = aVar;
    }

    public void onNext(T t) {
        this.a.call(t);
    }

    public void onError(Throwable th) {
        this.b.call(th);
    }

    public void onCompleted() {
        this.c.call();
    }
}
