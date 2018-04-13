package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.internal.observers.DeferredScalarDisposable;
import io.reactivex.plugins.RxJavaPlugins;

final class AsyncSubject$AsyncDisposable<T> extends DeferredScalarDisposable<T> {
    private static final long serialVersionUID = 5629876084736248016L;
    final AsyncSubject<T> parent;

    AsyncSubject$AsyncDisposable(Observer<? super T> observer, AsyncSubject<T> asyncSubject) {
        super(observer);
        this.parent = asyncSubject;
    }

    public void dispose() {
        if (super.tryDispose()) {
            this.parent.remove(this);
        }
    }

    void onComplete() {
        if (!isDisposed()) {
            this.actual.onComplete();
        }
    }

    void onError(Throwable th) {
        if (isDisposed()) {
            RxJavaPlugins.onError(th);
        } else {
            this.actual.onError(th);
        }
    }
}
