package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.AtomicThrowable;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public final class CompletableMergeDelayErrorIterable extends Completable {
    final Iterable<? extends CompletableSource> sources;

    public CompletableMergeDelayErrorIterable(Iterable<? extends CompletableSource> iterable) {
        this.sources = iterable;
    }

    public void subscribeActual(CompletableObserver completableObserver) {
        Throwable th;
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        completableObserver.onSubscribe(compositeDisposable);
        try {
            Iterator it = (Iterator) ObjectHelper.requireNonNull(this.sources.iterator(), "The source iterator returned is null");
            AtomicInteger atomicInteger = new AtomicInteger(1);
            AtomicThrowable atomicThrowable = new AtomicThrowable();
            while (!compositeDisposable.isDisposed()) {
                try {
                    if (it.hasNext()) {
                        if (!compositeDisposable.isDisposed()) {
                            try {
                                CompletableSource completableSource = (CompletableSource) ObjectHelper.requireNonNull(it.next(), "The iterator returned a null CompletableSource");
                                if (!compositeDisposable.isDisposed()) {
                                    atomicInteger.getAndIncrement();
                                    completableSource.subscribe(new MergeInnerCompletableObserver(completableObserver, compositeDisposable, atomicThrowable, atomicInteger));
                                } else {
                                    return;
                                }
                            } catch (Throwable th2) {
                                Exceptions.throwIfFatal(th2);
                                atomicThrowable.addThrowable(th2);
                            }
                        } else {
                            return;
                        }
                    } else if (atomicInteger.decrementAndGet() == 0) {
                        th2 = atomicThrowable.terminate();
                        if (th2 == null) {
                            completableObserver.onComplete();
                            return;
                        } else {
                            completableObserver.onError(th2);
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th22) {
                    Exceptions.throwIfFatal(th22);
                    atomicThrowable.addThrowable(th22);
                }
            }
        } catch (Throwable th222) {
            Exceptions.throwIfFatal(th222);
            completableObserver.onError(th222);
        }
    }
}
