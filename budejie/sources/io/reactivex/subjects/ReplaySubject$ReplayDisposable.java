package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicInteger;

final class ReplaySubject$ReplayDisposable<T> extends AtomicInteger implements Disposable {
    private static final long serialVersionUID = 466549804534799122L;
    final Observer<? super T> actual;
    volatile boolean cancelled;
    Object index;
    final ReplaySubject<T> state;

    ReplaySubject$ReplayDisposable(Observer<? super T> observer, ReplaySubject<T> replaySubject) {
        this.actual = observer;
        this.state = replaySubject;
    }

    public void dispose() {
        if (!this.cancelled) {
            this.cancelled = true;
            this.state.remove(this);
        }
    }

    public boolean isDisposed() {
        return this.cancelled;
    }
}
