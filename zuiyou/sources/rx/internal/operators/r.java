package rx.internal.operators;

import java.util.NoSuchElementException;
import rx.d$a;
import rx.e.c;
import rx.i;
import rx.j;
import rx.k;

public final class r<T> implements rx.h.a<T> {
    final d$a<T> a;

    static final class a<T> extends j<T> {
        final i<? super T> a;
        T b;
        int c;

        a(i<? super T> iVar) {
            this.a = iVar;
        }

        public void onNext(T t) {
            int i = this.c;
            if (i == 0) {
                this.c = 1;
                this.b = t;
            } else if (i == 1) {
                this.c = 2;
                this.a.a(new IndexOutOfBoundsException("The upstream produced more than one value"));
            }
        }

        public void onError(Throwable th) {
            if (this.c == 2) {
                c.a(th);
                return;
            }
            this.b = null;
            this.a.a(th);
        }

        public void onCompleted() {
            int i = this.c;
            if (i == 0) {
                this.a.a(new NoSuchElementException());
            } else if (i == 1) {
                this.c = 2;
                Object obj = this.b;
                this.b = null;
                this.a.a(obj);
            }
        }
    }

    public /* synthetic */ void call(Object obj) {
        a((i) obj);
    }

    public r(d$a<T> d_a) {
        this.a = d_a;
    }

    public void a(i<? super T> iVar) {
        k aVar = new a(iVar);
        iVar.a(aVar);
        this.a.call(aVar);
    }
}
