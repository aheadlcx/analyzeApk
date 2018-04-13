package io.reactivex.internal.operators.single;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public final class SingleTimeout<T> extends Single<T> {
    final SingleSource<? extends T> other;
    final Scheduler scheduler;
    final SingleSource<T> source;
    final long timeout;
    final TimeUnit unit;

    final class TimeoutDispose implements Runnable {
        private final AtomicBoolean once;
        final SingleObserver<? super T> s;
        final CompositeDisposable set;

        final class TimeoutObserver implements SingleObserver<T> {
            TimeoutObserver() {
            }

            public void onError(Throwable th) {
                TimeoutDispose.this.set.dispose();
                TimeoutDispose.this.s.onError(th);
            }

            public void onSubscribe(Disposable disposable) {
                TimeoutDispose.this.set.add(disposable);
            }

            public void onSuccess(T t) {
                TimeoutDispose.this.set.dispose();
                TimeoutDispose.this.s.onSuccess(t);
            }
        }

        TimeoutDispose(AtomicBoolean atomicBoolean, CompositeDisposable compositeDisposable, SingleObserver<? super T> singleObserver) {
            this.once = atomicBoolean;
            this.set = compositeDisposable;
            this.s = singleObserver;
        }

        public void run() {
            if (!this.once.compareAndSet(false, true)) {
                return;
            }
            if (SingleTimeout.this.other != null) {
                this.set.clear();
                SingleTimeout.this.other.subscribe(new TimeoutObserver());
                return;
            }
            this.set.dispose();
            this.s.onError(new TimeoutException());
        }
    }

    final class TimeoutObserver implements SingleObserver<T> {
        private final AtomicBoolean once;
        private final SingleObserver<? super T> s;
        private final CompositeDisposable set;

        TimeoutObserver(AtomicBoolean atomicBoolean, CompositeDisposable compositeDisposable, SingleObserver<? super T> singleObserver) {
            this.once = atomicBoolean;
            this.set = compositeDisposable;
            this.s = singleObserver;
        }

        public void onError(Throwable th) {
            if (this.once.compareAndSet(false, true)) {
                this.set.dispose();
                this.s.onError(th);
            }
        }

        public void onSubscribe(Disposable disposable) {
            this.set.add(disposable);
        }

        public void onSuccess(T t) {
            if (this.once.compareAndSet(false, true)) {
                this.set.dispose();
                this.s.onSuccess(t);
            }
        }
    }

    public SingleTimeout(SingleSource<T> singleSource, long j, TimeUnit timeUnit, Scheduler scheduler, SingleSource<? extends T> singleSource2) {
        this.source = singleSource;
        this.timeout = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.other = singleSource2;
    }

    protected void subscribeActual(SingleObserver<? super T> singleObserver) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        singleObserver.onSubscribe(compositeDisposable);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        compositeDisposable.add(this.scheduler.scheduleDirect(new TimeoutDispose(atomicBoolean, compositeDisposable, singleObserver), this.timeout, this.unit));
        this.source.subscribe(new TimeoutObserver(atomicBoolean, compositeDisposable, singleObserver));
    }
}
