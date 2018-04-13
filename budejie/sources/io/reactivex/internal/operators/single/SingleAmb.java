package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;

public final class SingleAmb<T> extends Single<T> {
    private final SingleSource<? extends T>[] sources;
    private final Iterable<? extends SingleSource<? extends T>> sourcesIterable;

    static final class AmbSingleObserver<T> extends AtomicBoolean implements SingleObserver<T> {
        private static final long serialVersionUID = -1944085461036028108L;
        final SingleObserver<? super T> s;
        final CompositeDisposable set;

        AmbSingleObserver(SingleObserver<? super T> singleObserver, CompositeDisposable compositeDisposable) {
            this.s = singleObserver;
            this.set = compositeDisposable;
        }

        public void onSubscribe(Disposable disposable) {
            this.set.add(disposable);
        }

        public void onSuccess(T t) {
            if (compareAndSet(false, true)) {
                this.set.dispose();
                this.s.onSuccess(t);
            }
        }

        public void onError(Throwable th) {
            if (compareAndSet(false, true)) {
                this.set.dispose();
                this.s.onError(th);
                return;
            }
            RxJavaPlugins.onError(th);
        }
    }

    public SingleAmb(SingleSource<? extends T>[] singleSourceArr, Iterable<? extends SingleSource<? extends T>> iterable) {
        this.sources = singleSourceArr;
        this.sourcesIterable = iterable;
    }

    protected void subscribeActual(SingleObserver<? super T> singleObserver) {
        Object obj;
        Throwable th;
        SingleSource[] singleSourceArr = this.sources;
        if (singleSourceArr == null) {
            Object obj2 = new SingleSource[8];
            try {
                int i = 0;
                for (SingleSource singleSource : this.sourcesIterable) {
                    if (singleSource == null) {
                        EmptyDisposable.error(new NullPointerException("One of the sources is null"), singleObserver);
                        return;
                    }
                    if (i == obj2.length) {
                        obj = new SingleSource[((i >> 2) + i)];
                        System.arraycopy(obj2, 0, obj, 0, i);
                        obj2 = obj;
                    }
                    int i2 = i + 1;
                    obj2[i] = singleSource;
                    i = i2;
                }
                int i3 = i;
                singleSourceArr = obj2;
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                EmptyDisposable.error(th2, singleObserver);
                return;
            }
        }
        i3 = singleSourceArr.length;
        obj = new CompositeDisposable();
        Object ambSingleObserver = new AmbSingleObserver(singleObserver, obj);
        singleObserver.onSubscribe(obj);
        int i4 = 0;
        while (i4 < i3) {
            SingleSource singleSource2 = singleSourceArr[i4];
            if (!ambSingleObserver.get()) {
                if (singleSource2 == null) {
                    obj.dispose();
                    th2 = new NullPointerException("One of the sources is null");
                    if (ambSingleObserver.compareAndSet(false, true)) {
                        singleObserver.onError(th2);
                        return;
                    } else {
                        RxJavaPlugins.onError(th2);
                        return;
                    }
                }
                singleSource2.subscribe(ambSingleObserver);
                i4++;
            } else {
                return;
            }
        }
    }
}
