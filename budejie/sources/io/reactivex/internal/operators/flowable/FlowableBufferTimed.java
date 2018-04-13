package io.reactivex.internal.operators.flowable;

import com.facebook.common.time.Clock;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.Scheduler$Worker;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.subscribers.QueueDrainSubscriber;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.QueueDrainHelper;
import io.reactivex.subscribers.SerializedSubscriber;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class FlowableBufferTimed<T, U extends Collection<? super T>> extends AbstractFlowableWithUpstream<T, U> {
    final Callable<U> bufferSupplier;
    final int maxSize;
    final boolean restartTimerOnMaxSize;
    final Scheduler scheduler;
    final long timeskip;
    final long timespan;
    final TimeUnit unit;

    static final class BufferExactBoundedSubscriber<T, U extends Collection<? super T>> extends QueueDrainSubscriber<T, U, U> implements Disposable, Runnable, d {
        U buffer;
        final Callable<U> bufferSupplier;
        long consumerIndex;
        final int maxSize;
        long producerIndex;
        final boolean restartTimerOnMaxSize;
        d s;
        Disposable timer;
        final long timespan;
        final TimeUnit unit;
        final Scheduler$Worker w;

        BufferExactBoundedSubscriber(c<? super U> cVar, Callable<U> callable, long j, TimeUnit timeUnit, int i, boolean z, Scheduler$Worker scheduler$Worker) {
            super(cVar, new MpscLinkedQueue());
            this.bufferSupplier = callable;
            this.timespan = j;
            this.unit = timeUnit;
            this.maxSize = i;
            this.restartTimerOnMaxSize = z;
            this.w = scheduler$Worker;
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.s, dVar)) {
                this.s = dVar;
                try {
                    this.buffer = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The supplied buffer is null");
                    this.actual.onSubscribe(this);
                    this.timer = this.w.schedulePeriodically(this, this.timespan, this.timespan, this.unit);
                    dVar.request(Clock.MAX_TIME);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.w.dispose();
                    dVar.cancel();
                    EmptySubscription.error(th, this.actual);
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onNext(T r8) {
            /*
            r7 = this;
            r4 = 1;
            monitor-enter(r7);
            r0 = r7.buffer;	 Catch:{ all -> 0x0016 }
            if (r0 != 0) goto L_0x0009;
        L_0x0007:
            monitor-exit(r7);	 Catch:{ all -> 0x0016 }
        L_0x0008:
            return;
        L_0x0009:
            r0.add(r8);	 Catch:{ all -> 0x0016 }
            r1 = r0.size();	 Catch:{ all -> 0x0016 }
            r2 = r7.maxSize;	 Catch:{ all -> 0x0016 }
            if (r1 >= r2) goto L_0x0019;
        L_0x0014:
            monitor-exit(r7);	 Catch:{ all -> 0x0016 }
            goto L_0x0008;
        L_0x0016:
            r0 = move-exception;
            monitor-exit(r7);	 Catch:{ all -> 0x0016 }
            throw r0;
        L_0x0019:
            monitor-exit(r7);	 Catch:{ all -> 0x0016 }
            r1 = r7.restartTimerOnMaxSize;
            if (r1 == 0) goto L_0x002b;
        L_0x001e:
            r1 = 0;
            r7.buffer = r1;
            r2 = r7.producerIndex;
            r2 = r2 + r4;
            r7.producerIndex = r2;
            r1 = r7.timer;
            r1.dispose();
        L_0x002b:
            r1 = 0;
            r7.fastPathOrderedEmitMax(r0, r1, r7);
            r0 = r7.bufferSupplier;	 Catch:{ Throwable -> 0x005a }
            r0 = r0.call();	 Catch:{ Throwable -> 0x005a }
            r1 = "The supplied buffer is null";
            r0 = io.reactivex.internal.functions.ObjectHelper.requireNonNull(r0, r1);	 Catch:{ Throwable -> 0x005a }
            r0 = (java.util.Collection) r0;	 Catch:{ Throwable -> 0x005a }
            r1 = r7.restartTimerOnMaxSize;
            if (r1 == 0) goto L_0x006a;
        L_0x0041:
            monitor-enter(r7);
            r7.buffer = r0;	 Catch:{ all -> 0x0067 }
            r0 = r7.consumerIndex;	 Catch:{ all -> 0x0067 }
            r0 = r0 + r4;
            r7.consumerIndex = r0;	 Catch:{ all -> 0x0067 }
            monitor-exit(r7);	 Catch:{ all -> 0x0067 }
            r0 = r7.w;
            r2 = r7.timespan;
            r4 = r7.timespan;
            r6 = r7.unit;
            r1 = r7;
            r0 = r0.schedulePeriodically(r1, r2, r4, r6);
            r7.timer = r0;
            goto L_0x0008;
        L_0x005a:
            r0 = move-exception;
            io.reactivex.exceptions.Exceptions.throwIfFatal(r0);
            r7.cancel();
            r1 = r7.actual;
            r1.onError(r0);
            goto L_0x0008;
        L_0x0067:
            r0 = move-exception;
            monitor-exit(r7);	 Catch:{ all -> 0x0067 }
            throw r0;
        L_0x006a:
            monitor-enter(r7);
            r7.buffer = r0;	 Catch:{ all -> 0x006f }
            monitor-exit(r7);	 Catch:{ all -> 0x006f }
            goto L_0x0008;
        L_0x006f:
            r0 = move-exception;
            monitor-exit(r7);	 Catch:{ all -> 0x006f }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableBufferTimed.BufferExactBoundedSubscriber.onNext(java.lang.Object):void");
        }

        public void onError(Throwable th) {
            synchronized (this) {
                this.buffer = null;
            }
            this.actual.onError(th);
            this.w.dispose();
        }

        public void onComplete() {
            Collection collection;
            synchronized (this) {
                collection = this.buffer;
                this.buffer = null;
            }
            this.queue.offer(collection);
            this.done = true;
            if (enter()) {
                QueueDrainHelper.drainMaxLoop(this.queue, this.actual, false, this, this);
            }
            this.w.dispose();
        }

        public boolean accept(c<? super U> cVar, U u) {
            cVar.onNext(u);
            return true;
        }

        public void request(long j) {
            requested(j);
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                dispose();
            }
        }

        public void dispose() {
            synchronized (this) {
                this.buffer = null;
            }
            this.s.cancel();
            this.w.dispose();
        }

        public boolean isDisposed() {
            return this.w.isDisposed();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r6 = this;
            r0 = r6.bufferSupplier;	 Catch:{ Throwable -> 0x001d }
            r0 = r0.call();	 Catch:{ Throwable -> 0x001d }
            r1 = "The supplied buffer is null";
            r0 = io.reactivex.internal.functions.ObjectHelper.requireNonNull(r0, r1);	 Catch:{ Throwable -> 0x001d }
            r0 = (java.util.Collection) r0;	 Catch:{ Throwable -> 0x001d }
            monitor-enter(r6);
            r1 = r6.buffer;	 Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x001b;
        L_0x0013:
            r2 = r6.producerIndex;	 Catch:{ all -> 0x0032 }
            r4 = r6.consumerIndex;	 Catch:{ all -> 0x0032 }
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 == 0) goto L_0x002a;
        L_0x001b:
            monitor-exit(r6);	 Catch:{ all -> 0x0032 }
        L_0x001c:
            return;
        L_0x001d:
            r0 = move-exception;
            io.reactivex.exceptions.Exceptions.throwIfFatal(r0);
            r6.cancel();
            r1 = r6.actual;
            r1.onError(r0);
            goto L_0x001c;
        L_0x002a:
            r6.buffer = r0;	 Catch:{ all -> 0x0032 }
            monitor-exit(r6);	 Catch:{ all -> 0x0032 }
            r0 = 0;
            r6.fastPathOrderedEmitMax(r1, r0, r6);
            goto L_0x001c;
        L_0x0032:
            r0 = move-exception;
            monitor-exit(r6);	 Catch:{ all -> 0x0032 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableBufferTimed.BufferExactBoundedSubscriber.run():void");
        }
    }

    static final class BufferExactUnboundedSubscriber<T, U extends Collection<? super T>> extends QueueDrainSubscriber<T, U, U> implements Disposable, Runnable, d {
        U buffer;
        final Callable<U> bufferSupplier;
        d s;
        final Scheduler scheduler;
        final AtomicReference<Disposable> timer = new AtomicReference();
        final long timespan;
        final TimeUnit unit;

        BufferExactUnboundedSubscriber(c<? super U> cVar, Callable<U> callable, long j, TimeUnit timeUnit, Scheduler scheduler) {
            super(cVar, new MpscLinkedQueue());
            this.bufferSupplier = callable;
            this.timespan = j;
            this.unit = timeUnit;
            this.scheduler = scheduler;
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.s, dVar)) {
                this.s = dVar;
                try {
                    this.buffer = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The supplied buffer is null");
                    this.actual.onSubscribe(this);
                    if (!this.cancelled) {
                        dVar.request(Clock.MAX_TIME);
                        Disposable schedulePeriodicallyDirect = this.scheduler.schedulePeriodicallyDirect(this, this.timespan, this.timespan, this.unit);
                        if (!this.timer.compareAndSet(null, schedulePeriodicallyDirect)) {
                            schedulePeriodicallyDirect.dispose();
                        }
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    cancel();
                    EmptySubscription.error(th, this.actual);
                }
            }
        }

        public void onNext(T t) {
            synchronized (this) {
                Collection collection = this.buffer;
                if (collection != null) {
                    collection.add(t);
                }
            }
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this.timer);
            synchronized (this) {
                this.buffer = null;
            }
            this.actual.onError(th);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onComplete() {
            /*
            r3 = this;
            r0 = r3.timer;
            io.reactivex.internal.disposables.DisposableHelper.dispose(r0);
            monitor-enter(r3);
            r0 = r3.buffer;	 Catch:{ all -> 0x0027 }
            if (r0 != 0) goto L_0x000c;
        L_0x000a:
            monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        L_0x000b:
            return;
        L_0x000c:
            r1 = 0;
            r3.buffer = r1;	 Catch:{ all -> 0x0027 }
            monitor-exit(r3);	 Catch:{ all -> 0x0027 }
            r1 = r3.queue;
            r1.offer(r0);
            r0 = 1;
            r3.done = r0;
            r0 = r3.enter();
            if (r0 == 0) goto L_0x000b;
        L_0x001e:
            r0 = r3.queue;
            r1 = r3.actual;
            r2 = 0;
            io.reactivex.internal.util.QueueDrainHelper.drainMaxLoop(r0, r1, r2, r3, r3);
            goto L_0x000b;
        L_0x0027:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0027 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableBufferTimed.BufferExactUnboundedSubscriber.onComplete():void");
        }

        public void request(long j) {
            requested(j);
        }

        public void cancel() {
            this.s.cancel();
            DisposableHelper.dispose(this.timer);
        }

        public void run() {
            try {
                Collection collection;
                Collection collection2 = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The supplied buffer is null");
                synchronized (this) {
                    collection = this.buffer;
                    if (collection != null) {
                        this.buffer = collection2;
                    }
                }
                if (collection == null) {
                    DisposableHelper.dispose(this.timer);
                } else {
                    fastPathEmitMax(collection, false, this);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                cancel();
                this.actual.onError(th);
            }
        }

        public boolean accept(c<? super U> cVar, U u) {
            this.actual.onNext(u);
            return true;
        }

        public void dispose() {
            cancel();
        }

        public boolean isDisposed() {
            return this.timer.get() == DisposableHelper.DISPOSED;
        }
    }

    static final class BufferSkipBoundedSubscriber<T, U extends Collection<? super T>> extends QueueDrainSubscriber<T, U, U> implements Runnable, d {
        final Callable<U> bufferSupplier;
        final List<U> buffers = new LinkedList();
        d s;
        final long timeskip;
        final long timespan;
        final TimeUnit unit;
        final Scheduler$Worker w;

        final class RemoveFromBuffer implements Runnable {
            private final U buffer;

            RemoveFromBuffer(U u) {
                this.buffer = u;
            }

            public void run() {
                synchronized (BufferSkipBoundedSubscriber.this) {
                    BufferSkipBoundedSubscriber.this.buffers.remove(this.buffer);
                }
                BufferSkipBoundedSubscriber.this.fastPathOrderedEmitMax(this.buffer, false, BufferSkipBoundedSubscriber.this.w);
            }
        }

        BufferSkipBoundedSubscriber(c<? super U> cVar, Callable<U> callable, long j, long j2, TimeUnit timeUnit, Scheduler$Worker scheduler$Worker) {
            super(cVar, new MpscLinkedQueue());
            this.bufferSupplier = callable;
            this.timespan = j;
            this.timeskip = j2;
            this.unit = timeUnit;
            this.w = scheduler$Worker;
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.s, dVar)) {
                this.s = dVar;
                try {
                    Collection collection = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The supplied buffer is null");
                    this.buffers.add(collection);
                    this.actual.onSubscribe(this);
                    dVar.request(Clock.MAX_TIME);
                    this.w.schedulePeriodically(this, this.timeskip, this.timeskip, this.unit);
                    this.w.schedule(new RemoveFromBuffer(collection), this.timespan, this.unit);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.w.dispose();
                    dVar.cancel();
                    EmptySubscription.error(th, this.actual);
                }
            }
        }

        public void onNext(T t) {
            synchronized (this) {
                for (Collection add : this.buffers) {
                    add.add(t);
                }
            }
        }

        public void onError(Throwable th) {
            this.done = true;
            this.w.dispose();
            clear();
            this.actual.onError(th);
        }

        public void onComplete() {
            synchronized (this) {
                List<Collection> arrayList = new ArrayList(this.buffers);
                this.buffers.clear();
            }
            for (Collection offer : arrayList) {
                this.queue.offer(offer);
            }
            this.done = true;
            if (enter()) {
                QueueDrainHelper.drainMaxLoop(this.queue, this.actual, false, this.w, this);
            }
        }

        public void request(long j) {
            requested(j);
        }

        public void cancel() {
            clear();
            this.s.cancel();
            this.w.dispose();
        }

        void clear() {
            synchronized (this) {
                this.buffers.clear();
            }
        }

        public void run() {
            if (!this.cancelled) {
                try {
                    Collection collection = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The supplied buffer is null");
                    synchronized (this) {
                        if (this.cancelled) {
                            return;
                        }
                        this.buffers.add(collection);
                        this.w.schedule(new RemoveFromBuffer(collection), this.timespan, this.unit);
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    cancel();
                    this.actual.onError(th);
                }
            }
        }

        public boolean accept(c<? super U> cVar, U u) {
            cVar.onNext(u);
            return true;
        }
    }

    public FlowableBufferTimed(Flowable<T> flowable, long j, long j2, TimeUnit timeUnit, Scheduler scheduler, Callable<U> callable, int i, boolean z) {
        super(flowable);
        this.timespan = j;
        this.timeskip = j2;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.bufferSupplier = callable;
        this.maxSize = i;
        this.restartTimerOnMaxSize = z;
    }

    protected void subscribeActual(c<? super U> cVar) {
        if (this.timespan == this.timeskip && this.maxSize == Integer.MAX_VALUE) {
            this.source.subscribe(new BufferExactUnboundedSubscriber(new SerializedSubscriber(cVar), this.bufferSupplier, this.timespan, this.unit, this.scheduler));
            return;
        }
        Scheduler$Worker createWorker = this.scheduler.createWorker();
        if (this.timespan == this.timeskip) {
            this.source.subscribe(new BufferExactBoundedSubscriber(new SerializedSubscriber(cVar), this.bufferSupplier, this.timespan, this.unit, this.maxSize, this.restartTimerOnMaxSize, createWorker));
        } else {
            this.source.subscribe(new BufferSkipBoundedSubscriber(new SerializedSubscriber(cVar), this.bufferSupplier, this.timespan, this.timeskip, this.unit, createWorker));
        }
    }
}
