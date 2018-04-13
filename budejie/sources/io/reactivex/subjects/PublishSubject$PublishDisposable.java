package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;

final class PublishSubject$PublishDisposable<T> extends AtomicBoolean implements Disposable {
    private static final long serialVersionUID = 3562861878281475070L;
    final Observer<? super T> actual;
    final PublishSubject<T> parent;

    PublishSubject$PublishDisposable(Observer<? super T> observer, PublishSubject<T> publishSubject) {
        this.actual = observer;
        this.parent = publishSubject;
    }

    public void onNext(T t) {
        if (!get()) {
            this.actual.onNext(t);
        }
    }

    public void onError(Throwable th) {
        if (get()) {
            RxJavaPlugins.onError(th);
        } else {
            this.actual.onError(th);
        }
    }

    public void onComplete() {
        if (!get()) {
            this.actual.onComplete();
        }
    }

    public void dispose() {
        if (compareAndSet(false, true)) {
            this.parent.remove(this);
        }
    }

    public boolean isDisposed() {
        return get();
    }
}
