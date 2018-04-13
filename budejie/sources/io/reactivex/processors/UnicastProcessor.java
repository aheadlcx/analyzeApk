package io.reactivex.processors;

import com.facebook.common.time.Clock;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.Experimental;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class UnicastProcessor<T> extends FlowableProcessor<T> {
    final AtomicReference<c<? super T>> actual;
    volatile boolean cancelled;
    final boolean delayError;
    volatile boolean done;
    boolean enableOperatorFusion;
    Throwable error;
    final AtomicReference<Runnable> onTerminate;
    final AtomicBoolean once;
    final SpscLinkedArrayQueue<T> queue;
    final AtomicLong requested;
    final BasicIntQueueSubscription<T> wip;

    @CheckReturnValue
    public static <T> UnicastProcessor<T> create() {
        return new UnicastProcessor(bufferSize());
    }

    @CheckReturnValue
    public static <T> UnicastProcessor<T> create(int i) {
        return new UnicastProcessor(i);
    }

    @CheckReturnValue
    @Experimental
    public static <T> UnicastProcessor<T> create(boolean z) {
        return new UnicastProcessor(bufferSize(), null, z);
    }

    @CheckReturnValue
    public static <T> UnicastProcessor<T> create(int i, Runnable runnable) {
        ObjectHelper.requireNonNull(runnable, "onTerminate");
        return new UnicastProcessor(i, runnable);
    }

    @CheckReturnValue
    @Experimental
    public static <T> UnicastProcessor<T> create(int i, Runnable runnable, boolean z) {
        ObjectHelper.requireNonNull(runnable, "onTerminate");
        return new UnicastProcessor(i, runnable, z);
    }

    UnicastProcessor(int i) {
        this(i, null, true);
    }

    UnicastProcessor(int i, Runnable runnable) {
        this(i, runnable, true);
    }

    UnicastProcessor(int i, Runnable runnable, boolean z) {
        this.queue = new SpscLinkedArrayQueue(ObjectHelper.verifyPositive(i, "capacityHint"));
        this.onTerminate = new AtomicReference(runnable);
        this.delayError = z;
        this.actual = new AtomicReference();
        this.once = new AtomicBoolean();
        this.wip = new UnicastProcessor$UnicastQueueSubscription(this);
        this.requested = new AtomicLong();
    }

    void doTerminate() {
        Runnable runnable = (Runnable) this.onTerminate.get();
        if (runnable != null && this.onTerminate.compareAndSet(runnable, null)) {
            runnable.run();
        }
    }

    void drainRegular(c<? super T> cVar) {
        SpscLinkedArrayQueue spscLinkedArrayQueue = this.queue;
        boolean z = !this.delayError;
        int i = 1;
        while (true) {
            long j = this.requested.get();
            long j2 = 0;
            while (j != j2) {
                boolean z2 = this.done;
                Object poll = spscLinkedArrayQueue.poll();
                boolean z3 = poll == null;
                if (!checkTerminated(z, z2, z3, cVar, spscLinkedArrayQueue)) {
                    if (z3) {
                        break;
                    }
                    cVar.onNext(poll);
                    j2 = 1 + j2;
                } else {
                    return;
                }
            }
            if (j != j2 || !checkTerminated(z, this.done, spscLinkedArrayQueue.isEmpty(), cVar, spscLinkedArrayQueue)) {
                if (!(j2 == 0 || j == Clock.MAX_TIME)) {
                    this.requested.addAndGet(-j2);
                }
                int addAndGet = this.wip.addAndGet(-i);
                if (addAndGet != 0) {
                    i = addAndGet;
                } else {
                    return;
                }
            }
            return;
        }
    }

    void drainFused(c<? super T> cVar) {
        int i = 1;
        SpscLinkedArrayQueue spscLinkedArrayQueue = this.queue;
        int i2 = !this.delayError ? 1 : 0;
        while (!this.cancelled) {
            boolean z = this.done;
            if (i2 == 0 || !z || this.error == null) {
                cVar.onNext(null);
                if (z) {
                    this.actual.lazySet(null);
                    Throwable th = this.error;
                    if (th != null) {
                        cVar.onError(th);
                        return;
                    } else {
                        cVar.onComplete();
                        return;
                    }
                }
                i = this.wip.addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
            spscLinkedArrayQueue.clear();
            this.actual.lazySet(null);
            cVar.onError(this.error);
            return;
        }
        spscLinkedArrayQueue.clear();
        this.actual.lazySet(null);
    }

    void drain() {
        if (this.wip.getAndIncrement() == 0) {
            int i = 1;
            c cVar = (c) this.actual.get();
            while (cVar == null) {
                i = this.wip.addAndGet(-i);
                if (i != 0) {
                    cVar = (c) this.actual.get();
                } else {
                    return;
                }
            }
            if (this.enableOperatorFusion) {
                drainFused(cVar);
            } else {
                drainRegular(cVar);
            }
        }
    }

    boolean checkTerminated(boolean z, boolean z2, boolean z3, c<? super T> cVar, SpscLinkedArrayQueue<T> spscLinkedArrayQueue) {
        if (this.cancelled) {
            spscLinkedArrayQueue.clear();
            this.actual.lazySet(null);
            return true;
        }
        if (z2) {
            if (z && this.error != null) {
                spscLinkedArrayQueue.clear();
                this.actual.lazySet(null);
                cVar.onError(this.error);
                return true;
            } else if (z3) {
                Throwable th = this.error;
                this.actual.lazySet(null);
                if (th != null) {
                    cVar.onError(th);
                    return true;
                }
                cVar.onComplete();
                return true;
            }
        }
        return false;
    }

    public void onSubscribe(d dVar) {
        if (this.done || this.cancelled) {
            dVar.cancel();
        } else {
            dVar.request(Clock.MAX_TIME);
        }
    }

    public void onNext(T t) {
        if (!this.done && !this.cancelled) {
            if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                return;
            }
            this.queue.offer(t);
            drain();
        }
    }

    public void onError(Throwable th) {
        if (this.done || this.cancelled) {
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
        if (!this.done && !this.cancelled) {
            this.done = true;
            doTerminate();
            drain();
        }
    }

    protected void subscribeActual(c<? super T> cVar) {
        if (this.once.get() || !this.once.compareAndSet(false, true)) {
            EmptySubscription.error(new IllegalStateException("This processor allows only a single Subscriber"), cVar);
            return;
        }
        cVar.onSubscribe(this.wip);
        this.actual.set(cVar);
        if (this.cancelled) {
            this.actual.lazySet(null);
        } else {
            drain();
        }
    }

    public boolean hasSubscribers() {
        return this.actual.get() != null;
    }

    public Throwable getThrowable() {
        if (this.done) {
            return this.error;
        }
        return null;
    }

    public boolean hasComplete() {
        return this.done && this.error == null;
    }

    public boolean hasThrowable() {
        return this.done && this.error != null;
    }
}
