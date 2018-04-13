package io.reactivex.subjects;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;

final class SingleSubject$SingleDisposable<T> extends AtomicReference<SingleSubject<T>> implements Disposable {
    private static final long serialVersionUID = -7650903191002190468L;
    final SingleObserver<? super T> actual;

    SingleSubject$SingleDisposable(SingleObserver<? super T> singleObserver, SingleSubject<T> singleSubject) {
        this.actual = singleObserver;
        lazySet(singleSubject);
    }

    public void dispose() {
        SingleSubject singleSubject = (SingleSubject) getAndSet(null);
        if (singleSubject != null) {
            singleSubject.remove(this);
        }
    }

    public boolean isDisposed() {
        return get() == null;
    }
}
