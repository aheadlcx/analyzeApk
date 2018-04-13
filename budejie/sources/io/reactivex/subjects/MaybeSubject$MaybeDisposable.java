package io.reactivex.subjects;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;

final class MaybeSubject$MaybeDisposable<T> extends AtomicReference<MaybeSubject<T>> implements Disposable {
    private static final long serialVersionUID = -7650903191002190468L;
    final MaybeObserver<? super T> actual;

    MaybeSubject$MaybeDisposable(MaybeObserver<? super T> maybeObserver, MaybeSubject<T> maybeSubject) {
        this.actual = maybeObserver;
        lazySet(maybeSubject);
    }

    public void dispose() {
        MaybeSubject maybeSubject = (MaybeSubject) getAndSet(null);
        if (maybeSubject != null) {
            maybeSubject.remove(this);
        }
    }

    public boolean isDisposed() {
        return get() == null;
    }
}
