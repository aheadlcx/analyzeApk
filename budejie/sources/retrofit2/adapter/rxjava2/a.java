package retrofit2.adapter.rxjava2;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.l;

final class a<T> extends Observable<T> {
    private final Observable<l<T>> a;

    private static class a<R> implements Observer<l<R>> {
        private final Observer<? super R> a;
        private boolean b;

        public /* synthetic */ void onNext(Object obj) {
            a((l) obj);
        }

        a(Observer<? super R> observer) {
            this.a = observer;
        }

        public void onSubscribe(Disposable disposable) {
            this.a.onSubscribe(disposable);
        }

        public void a(l<R> lVar) {
            if (lVar.d()) {
                this.a.onNext(lVar.e());
                return;
            }
            this.b = true;
            try {
                this.a.onError(new HttpException(lVar));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(new CompositeException(new Throwable[]{r1, th}));
            }
        }

        public void onComplete() {
            if (!this.b) {
                this.a.onComplete();
            }
        }

        public void onError(Throwable th) {
            if (this.b) {
                Throwable assertionError = new AssertionError("This should never happen! Report as a bug with the full stacktrace.");
                assertionError.initCause(th);
                RxJavaPlugins.onError(assertionError);
                return;
            }
            this.a.onError(th);
        }
    }

    a(Observable<l<T>> observable) {
        this.a = observable;
    }

    protected void subscribeActual(Observer<? super T> observer) {
        this.a.subscribe(new a(observer));
    }
}
