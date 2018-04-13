package rx.internal.operators;

import java.util.NoSuchElementException;
import rx.d;
import rx.h.a;
import rx.i;
import rx.j;
import rx.k;

public class e<T> implements a<T> {
    private final d<T> a;

    public /* synthetic */ void call(Object obj) {
        a((i) obj);
    }

    public e(d<T> dVar) {
        this.a = dVar;
    }

    public void a(final i<? super T> iVar) {
        k anonymousClass1 = new j<T>(this) {
            final /* synthetic */ e b;
            private boolean c;
            private boolean d;
            private T e;

            public void onStart() {
                request(2);
            }

            public void onCompleted() {
                if (!this.c) {
                    if (this.d) {
                        iVar.a(this.e);
                    } else {
                        iVar.a(new NoSuchElementException("Observable emitted no items"));
                    }
                }
            }

            public void onError(Throwable th) {
                iVar.a(th);
                unsubscribe();
            }

            public void onNext(T t) {
                if (this.d) {
                    this.c = true;
                    iVar.a(new IllegalArgumentException("Observable emitted too many elements"));
                    unsubscribe();
                    return;
                }
                this.d = true;
                this.e = t;
            }
        };
        iVar.a(anonymousClass1);
        this.a.a(anonymousClass1);
    }

    public static <T> e<T> a(d<T> dVar) {
        return new e(dVar);
    }
}
