package retrofit2.adapter.rxjava2;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.b;
import retrofit2.l;

final class c<T> extends Observable<l<T>> {
    private final b<T> a;

    private static final class a implements Disposable {
        private final b<?> a;

        a(b<?> bVar) {
            this.a = bVar;
        }

        public void dispose() {
            this.a.b();
        }

        public boolean isDisposed() {
            return this.a.c();
        }
    }

    c(b<T> bVar) {
        this.a = bVar;
    }

    protected void subscribeActual(Observer<? super l<T>> observer) {
        Throwable th;
        int i;
        b d = this.a.d();
        observer.onSubscribe(new a(d));
        try {
            l a = d.a();
            if (!d.c()) {
                observer.onNext(a);
            }
            if (!d.c()) {
                try {
                    observer.onComplete();
                } catch (Throwable th2) {
                    th = th2;
                    i = 1;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            i = 0;
            Exceptions.throwIfFatal(th);
            if (i != 0) {
                RxJavaPlugins.onError(th);
            } else if (!d.c()) {
                try {
                    observer.onError(th);
                } catch (Throwable th4) {
                    Exceptions.throwIfFatal(th4);
                    RxJavaPlugins.onError(new CompositeException(new Throwable[]{th, th4}));
                }
            }
        }
    }
}
