package rx.internal.operators;

import rx.d;
import rx.d$a;
import rx.j;

public enum EmptyObservableHolder implements d$a<Object> {
    INSTANCE;
    
    static final d<Object> EMPTY = null;

    static {
        EMPTY = d.b(INSTANCE);
    }

    public static <T> d<T> instance() {
        return EMPTY;
    }

    public void call(j<? super Object> jVar) {
        jVar.onCompleted();
    }
}
