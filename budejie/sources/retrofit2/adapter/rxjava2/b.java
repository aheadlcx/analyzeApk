package retrofit2.adapter.rxjava2;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.d;
import retrofit2.l;

final class b<T> extends Observable<l<T>> {
    private final retrofit2.b<T> a;

    private static final class a<T> implements Disposable, d<T> {
        boolean a = false;
        private final retrofit2.b<?> b;
        private final Observer<? super l<T>> c;

        a(retrofit2.b<?> bVar, Observer<? super l<T>> observer) {
            this.b = bVar;
            this.c = observer;
        }

        public void onResponse(retrofit2.b<T> bVar, l<T> lVar) {
            if (!bVar.c()) {
                try {
                    this.c.onNext(lVar);
                    if (!bVar.c()) {
                        this.a = true;
                        this.c.onComplete();
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    RxJavaPlugins.onError(new CompositeException(new Throwable[]{th, th}));
                }
            }
        }

        public void onFailure(retrofit2.b<T> bVar, Throwable th) {
            if (!bVar.c()) {
                try {
                    this.c.onError(th);
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    RxJavaPlugins.onError(new CompositeException(new Throwable[]{th, th2}));
                }
            }
        }

        public void dispose() {
            this.b.b();
        }

        public boolean isDisposed() {
            return this.b.c();
        }
    }

    b(retrofit2.b<T> bVar) {
        this.a = bVar;
    }

    protected void subscribeActual(Observer<? super l<T>> observer) {
        retrofit2.b d = this.a.d();
        Object aVar = new a(d, observer);
        observer.onSubscribe(aVar);
        d.a(aVar);
    }
}
