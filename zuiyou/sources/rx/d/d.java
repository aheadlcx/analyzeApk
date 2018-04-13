package rx.d;

import rx.j;

public final class d {
    public static <T> j<T> a(final j<? super T> jVar) {
        return new j<T>(jVar) {
            public void onCompleted() {
                jVar.onCompleted();
            }

            public void onError(Throwable th) {
                jVar.onError(th);
            }

            public void onNext(T t) {
                jVar.onNext(t);
            }
        };
    }
}
