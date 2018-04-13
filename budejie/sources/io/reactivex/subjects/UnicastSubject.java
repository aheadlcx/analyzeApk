package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.Experimental;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class UnicastSubject<T> extends Subject<T> {
    final AtomicReference<Observer<? super T>> actual;
    final boolean delayError;
    volatile boolean disposed;
    volatile boolean done;
    boolean enableOperatorFusion;
    Throwable error;
    final AtomicReference<Runnable> onTerminate;
    final AtomicBoolean once;
    final SpscLinkedArrayQueue<T> queue;
    final BasicIntQueueDisposable<T> wip;

    @CheckReturnValue
    public static <T> UnicastSubject<T> create() {
        return new UnicastSubject(bufferSize(), true);
    }

    @CheckReturnValue
    public static <T> UnicastSubject<T> create(int i) {
        return new UnicastSubject(i, true);
    }

    @CheckReturnValue
    public static <T> UnicastSubject<T> create(int i, Runnable runnable) {
        return new UnicastSubject(i, runnable, true);
    }

    @CheckReturnValue
    @Experimental
    public static <T> UnicastSubject<T> create(int i, Runnable runnable, boolean z) {
        return new UnicastSubject(i, runnable, z);
    }

    @CheckReturnValue
    @Experimental
    public static <T> UnicastSubject<T> create(boolean z) {
        return new UnicastSubject(bufferSize(), z);
    }

    UnicastSubject(int i, boolean z) {
        this.queue = new SpscLinkedArrayQueue(ObjectHelper.verifyPositive(i, "capacityHint"));
        this.onTerminate = new AtomicReference();
        this.delayError = z;
        this.actual = new AtomicReference();
        this.once = new AtomicBoolean();
        this.wip = new UnicastSubject$UnicastQueueDisposable(this);
    }

    UnicastSubject(int i, Runnable runnable) {
        this(i, runnable, true);
    }

    UnicastSubject(int i, Runnable runnable, boolean z) {
        this.queue = new SpscLinkedArrayQueue(ObjectHelper.verifyPositive(i, "capacityHint"));
        this.onTerminate = new AtomicReference(ObjectHelper.requireNonNull(runnable, "onTerminate"));
        this.delayError = z;
        this.actual = new AtomicReference();
        this.once = new AtomicBoolean();
        this.wip = new UnicastSubject$UnicastQueueDisposable(this);
    }

    protected void subscribeActual(Observer<? super T> observer) {
        if (this.once.get() || !this.once.compareAndSet(false, true)) {
            EmptyDisposable.error(new IllegalStateException("Only a single observer allowed."), observer);
            return;
        }
        observer.onSubscribe(this.wip);
        this.actual.lazySet(observer);
        if (this.disposed) {
            this.actual.lazySet(null);
        } else {
            drain();
        }
    }

    void doTerminate() {
        Runnable runnable = (Runnable) this.onTerminate.get();
        if (runnable != null && this.onTerminate.compareAndSet(runnable, null)) {
            runnable.run();
        }
    }

    public void onSubscribe(Disposable disposable) {
        if (this.done || this.disposed) {
            disposable.dispose();
        }
    }

    public void onNext(T t) {
        if (!this.done && !this.disposed) {
            if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                return;
            }
            this.queue.offer(t);
            drain();
        }
    }

    public void onError(Throwable th) {
        if (this.done || this.disposed) {
            RxJavaPlugins.onError(th);
            return;
        }
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        this.error = th;
        this.done = true;
        doTerminate();
        drain();
    }

    public void onComplete() {
        if (!this.done && !this.disposed) {
            this.done = true;
            doTerminate();
            drain();
        }
    }

    void drainNormal(Observer<? super T> observer) {
        SimpleQueue simpleQueue = this.queue;
        Object obj = !this.delayError ? 1 : null;
        Object obj2 = 1;
        int i = 1;
        while (!this.disposed) {
            boolean z = this.done;
            Object poll = this.queue.poll();
            Object obj3 = poll == null ? 1 : null;
            if (z) {
                if (!(obj == null || r3 == null)) {
                    if (!failedFast(simpleQueue, observer)) {
                        obj2 = null;
                    } else {
                        return;
                    }
                }
                if (obj3 != null) {
                    errorOrComplete(observer);
                    return;
                }
            }
            if (obj3 != null) {
                i = this.wip.addAndGet(-i);
                if (i == 0) {
                    return;
                }
            } else {
                observer.onNext(poll);
            }
        }
        this.actual.lazySet(null);
        simpleQueue.clear();
    }

    void drainFused(Observer<? super T> observer) {
        int i = 1;
        Object obj = this.queue;
        int i2 = !this.delayError ? 1 : 0;
        while (!this.disposed) {
            boolean z = this.done;
            if (i2 == 0 || !z || !failedFast(obj, observer)) {
                observer.onNext(null);
                if (z) {
                    errorOrComplete(observer);
                    return;
                }
                i = this.wip.addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
            return;
        }
        this.actual.lazySet(null);
        obj.clear();
    }

    void errorOrComplete(Observer<? super T> observer) {
        this.actual.lazySet(null);
        Throwable th = this.error;
        if (th != null) {
            observer.onError(th);
        } else {
            observer.onComplete();
        }
    }

    boolean failedFast(SimpleQueue<T> simpleQueue, Observer<? super T> observer) {
        Throwable th = this.error;
        if (th == null) {
            return false;
        }
        this.actual.lazySet(null);
        simpleQueue.clear();
        observer.onError(th);
        return true;
    }

    void drain() {
        if (this.wip.getAndIncrement() == 0) {
            Observer observer = (Observer) this.actual.get();
            int i = 1;
            while (observer == null) {
                int addAndGet = this.wip.addAndGet(-i);
                if (addAndGet != 0) {
                    int i2 = addAndGet;
                    observer = (Observer) this.actual.get();
                    i = i2;
                } else {
                    return;
                }
            }
            if (this.enableOperatorFusion) {
                drainFused(observer);
            } else {
                drainNormal(observer);
            }
        }
    }

    public boolean hasObservers() {
        return this.actual.get() != null;
    }

    public Throwable getThrowable() {
        if (this.done) {
            return this.error;
        }
        return null;
    }

    public boolean hasThrowable() {
        return this.done && this.error != null;
    }

    public boolean hasComplete() {
        return this.done && this.error == null;
    }
}
