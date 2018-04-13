package rx.d;

import rx.e;
import rx.j;

public class c<T> extends j<T> {
    private final e<T> a;

    public c(j<? super T> jVar) {
        this(jVar, true);
    }

    public c(j<? super T> jVar, boolean z) {
        super(jVar, z);
        this.a = new b(jVar);
    }

    public void onCompleted() {
        this.a.onCompleted();
    }

    public void onError(Throwable th) {
        this.a.onError(th);
    }

    public void onNext(T t) {
        this.a.onNext(t);
    }
}
