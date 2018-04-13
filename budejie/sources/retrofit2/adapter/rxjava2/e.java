package retrofit2.adapter.rxjava2;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.l;

final class e<T> extends Observable<d<T>> {
    private final Observable<l<T>> a;

    private static class a<R> implements Observer<l<R>> {
        private final Observer<? super d<R>> a;

        public /* synthetic */ void onNext(Object obj) {
            a((l) obj);
        }

        a(Observer<? super d<R>> observer) {
            this.a = observer;
        }

        public void onSubscribe(Disposable disposable) {
            this.a.onSubscribe(disposable);
        }

        public void a(l<R> lVar) {
            this.a.onNext(d.a((l) lVar));
        }

        public void onError(Throwable th) {
            try {
                this.a.onNext(d.a(th));
                this.a.onComplete();
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                RxJavaPlugins.onError(new CompositeException(new Throwable[]{th, th2}));
            }
        }

        public void onComplete() {
            this.a.onComplete();
        }
    }

    e(Observable<l<T>> observable) {
        this.a = observable;
    }

    protected void subscribeActual(Observer<? super d<T>> observer) {
        this.a.subscribe(new a(observer));
    }
}
