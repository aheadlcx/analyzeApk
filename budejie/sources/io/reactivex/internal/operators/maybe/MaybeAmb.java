package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;

public final class MaybeAmb<T> extends Maybe<T> {
    private final MaybeSource<? extends T>[] sources;
    private final Iterable<? extends MaybeSource<? extends T>> sourcesIterable;

    static final class AmbMaybeObserver<T> extends AtomicBoolean implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = -7044685185359438206L;
        final MaybeObserver<? super T> actual;
        final CompositeDisposable set = new CompositeDisposable();

        AmbMaybeObserver(MaybeObserver<? super T> maybeObserver) {
            this.actual = maybeObserver;
        }

        public void dispose() {
            if (compareAndSet(false, true)) {
                this.set.dispose();
            }
        }

        public boolean isDisposed() {
            return get();
        }

        public void onSubscribe(Disposable disposable) {
            this.set.add(disposable);
        }

        public void onSuccess(T t) {
            if (compareAndSet(false, true)) {
                this.set.dispose();
                this.actual.onSuccess(t);
            }
        }

        public void onError(Throwable th) {
            if (compareAndSet(false, true)) {
                this.set.dispose();
                this.actual.onError(th);
                return;
            }
            RxJavaPlugins.onError(th);
        }

        public void onComplete() {
            if (compareAndSet(false, true)) {
                this.set.dispose();
                this.actual.onComplete();
            }
        }
    }

    public MaybeAmb(MaybeSource<? extends T>[] maybeSourceArr, Iterable<? extends MaybeSource<? extends T>> iterable) {
        this.sources = maybeSourceArr;
        this.sourcesIterable = iterable;
    }

    protected void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        Object obj;
        MaybeSource[] maybeSourceArr = this.sources;
        if (maybeSourceArr == null) {
            Object obj2 = new MaybeSource[8];
            try {
                int i = 0;
                for (MaybeSource maybeSource : this.sourcesIterable) {
                    if (maybeSource == null) {
                        EmptyDisposable.error(new NullPointerException("One of the sources is null"), maybeObserver);
                        return;
                    }
                    if (i == obj2.length) {
                        obj = new MaybeSource[((i >> 2) + i)];
                        System.arraycopy(obj2, 0, obj, 0, i);
                        obj2 = obj;
                    }
                    int i2 = i + 1;
                    obj2[i] = maybeSource;
                    i = i2;
                }
                int i3 = i;
                maybeSourceArr = obj2;
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptyDisposable.error(th, maybeObserver);
                return;
            }
        }
        i3 = maybeSourceArr.length;
        obj = new AmbMaybeObserver(maybeObserver);
        maybeObserver.onSubscribe(obj);
        int i4 = 0;
        while (i4 < i3) {
            MaybeSource maybeSource2 = maybeSourceArr[i4];
            if (!obj.isDisposed()) {
                if (maybeSource2 == null) {
                    obj.onError(new NullPointerException("One of the MaybeSources is null"));
                    return;
                } else {
                    maybeSource2.subscribe(obj);
                    i4++;
                }
            } else {
                return;
            }
        }
        if (i3 == 0) {
            maybeObserver.onComplete();
        }
    }
}
