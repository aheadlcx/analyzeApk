package io.reactivex.subjects;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;

final class CompletableSubject$CompletableDisposable extends AtomicReference<CompletableSubject> implements Disposable {
    private static final long serialVersionUID = -7650903191002190468L;
    final CompletableObserver actual;

    CompletableSubject$CompletableDisposable(CompletableObserver completableObserver, CompletableSubject completableSubject) {
        this.actual = completableObserver;
        lazySet(completableSubject);
    }

    public void dispose() {
        CompletableSubject completableSubject = (CompletableSubject) getAndSet(null);
        if (completableSubject != null) {
            completableSubject.remove(this);
        }
    }

    public boolean isDisposed() {
        return get() == null;
    }
}
