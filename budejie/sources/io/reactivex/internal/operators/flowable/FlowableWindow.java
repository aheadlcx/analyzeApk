package io.reactivex.internal.operators.flowable;

import com.facebook.common.time.Clock;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.processors.UnicastProcessor;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.a.a;
import org.a.c;
import org.a.d;

public final class FlowableWindow<T> extends AbstractFlowableWithUpstream<T, Flowable<T>> {
    final int bufferSize;
    final long size;
    final long skip;

    static final class WindowExactSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Runnable, d {
        private static final long serialVersionUID = -2365647875069161133L;
        final c<? super Flowable<T>> actual;
        final int bufferSize;
        long index;
        final AtomicBoolean once = new AtomicBoolean();
        d s;
        final long size;
        UnicastProcessor<T> window;

        WindowExactSubscriber(c<? super Flowable<T>> cVar, long j, int i) {
            super(1);
            this.actual = cVar;
            this.size = j;
            this.bufferSize = i;
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.s, dVar)) {
                this.s = dVar;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            long j = this.index;
            UnicastProcessor unicastProcessor = this.window;
            if (j == 0) {
                getAndIncrement();
                unicastProcessor = UnicastProcessor.create(this.bufferSize, this);
                this.window = unicastProcessor;
                this.actual.onNext(unicastProcessor);
            }
            j++;
            unicastProcessor.onNext(t);
            if (j == this.size) {
                this.index = 0;
                this.window = null;
                unicastProcessor.onComplete();
                return;
            }
            this.index = j;
        }

        public void onError(Throwable th) {
            a aVar = this.window;
            if (aVar != null) {
                this.window = null;
                aVar.onError(th);
            }
            this.actual.onError(th);
        }

        public void onComplete() {
            a aVar = this.window;
            if (aVar != null) {
                this.window = null;
                aVar.onComplete();
            }
            this.actual.onComplete();
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                this.s.request(BackpressureHelper.multiplyCap(this.size, j));
            }
        }

        public void cancel() {
            if (this.once.compareAndSet(false, true)) {
                run();
            }
        }

        public void run() {
            if (decrementAndGet() == 0) {
                this.s.cancel();
            }
        }
    }

    static final class WindowOverlapSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Runnable, d {
        private static final long serialVersionUID = 2428527070996323976L;
        final c<? super Flowable<T>> actual;
        final int bufferSize;
        volatile boolean cancelled;
        volatile boolean done;
        Throwable error;
        final AtomicBoolean firstRequest = new AtomicBoolean();
        long index;
        final AtomicBoolean once = new AtomicBoolean();
        long produced;
        final SpscLinkedArrayQueue<UnicastProcessor<T>> queue;
        final AtomicLong requested = new AtomicLong();
        d s;
        final long size;
        final long skip;
        final ArrayDeque<UnicastProcessor<T>> windows = new ArrayDeque();
        final AtomicInteger wip = new AtomicInteger();

        WindowOverlapSubscriber(c<? super Flowable<T>> cVar, long j, long j2, int i) {
            super(1);
            this.actual = cVar;
            this.size = j;
            this.skip = j2;
            this.queue = new SpscLinkedArrayQueue(i);
            this.bufferSize = i;
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.s, dVar)) {
                this.s = dVar;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long j = this.index;
                if (j == 0 && !this.cancelled) {
                    getAndIncrement();
                    UnicastProcessor create = UnicastProcessor.create(this.bufferSize, this);
                    this.windows.offer(create);
                    this.queue.offer(create);
                    drain();
                }
                long j2 = j + 1;
                Iterator it = this.windows.iterator();
                while (it.hasNext()) {
                    ((a) it.next()).onNext(t);
                }
                j = this.produced + 1;
                if (j == this.size) {
                    this.produced = j - this.skip;
                    a aVar = (a) this.windows.poll();
                    if (aVar != null) {
                        aVar.onComplete();
                    }
                } else {
                    this.produced = j;
                }
                if (j2 == this.skip) {
                    this.index = 0;
                } else {
                    this.index = j2;
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            Iterator it = this.windows.iterator();
            while (it.hasNext()) {
                ((a) it.next()).onError(th);
            }
            this.windows.clear();
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            if (!this.done) {
                Iterator it = this.windows.iterator();
                while (it.hasNext()) {
                    ((a) it.next()).onComplete();
                }
                this.windows.clear();
                this.done = true;
                drain();
            }
        }

        void drain() {
            if (this.wip.getAndIncrement() == 0) {
                c cVar = this.actual;
                SpscLinkedArrayQueue spscLinkedArrayQueue = this.queue;
                int i = 1;
                while (true) {
                    long j = this.requested.get();
                    long j2 = 0;
                    while (j2 != j) {
                        boolean z = this.done;
                        UnicastProcessor unicastProcessor = (UnicastProcessor) spscLinkedArrayQueue.poll();
                        boolean z2 = unicastProcessor == null;
                        if (!checkTerminated(z, z2, cVar, spscLinkedArrayQueue)) {
                            if (z2) {
                                break;
                            }
                            cVar.onNext(unicastProcessor);
                            j2++;
                        } else {
                            return;
                        }
                    }
                    if (j2 != j || !checkTerminated(this.done, spscLinkedArrayQueue.isEmpty(), cVar, spscLinkedArrayQueue)) {
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
        }

        boolean checkTerminated(boolean z, boolean z2, c<?> cVar, SpscLinkedArrayQueue<?> spscLinkedArrayQueue) {
            if (this.cancelled) {
                spscLinkedArrayQueue.clear();
                return true;
            }
            if (z) {
                Throwable th = this.error;
                if (th != null) {
                    spscLinkedArrayQueue.clear();
                    cVar.onError(th);
                    return true;
                } else if (z2) {
                    cVar.onComplete();
                    return true;
                }
            }
            return false;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                if (this.firstRequest.get() || !this.firstRequest.compareAndSet(false, true)) {
                    this.s.request(BackpressureHelper.multiplyCap(this.skip, j));
                } else {
                    this.s.request(BackpressureHelper.addCap(this.size, BackpressureHelper.multiplyCap(this.skip, j - 1)));
                }
                drain();
            }
        }

        public void cancel() {
            this.cancelled = true;
            if (this.once.compareAndSet(false, true)) {
                run();
            }
        }

        public void run() {
            if (decrementAndGet() == 0) {
                this.s.cancel();
            }
        }
    }

    static final class WindowSkipSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Runnable, d {
        private static final long serialVersionUID = -8792836352386833856L;
        final c<? super Flowable<T>> actual;
        final int bufferSize;
        final AtomicBoolean firstRequest = new AtomicBoolean();
        long index;
        final AtomicBoolean once = new AtomicBoolean();
        d s;
        final long size;
        final long skip;
        UnicastProcessor<T> window;

        WindowSkipSubscriber(c<? super Flowable<T>> cVar, long j, long j2, int i) {
            super(1);
            this.actual = cVar;
            this.size = j;
            this.skip = j2;
            this.bufferSize = i;
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.s, dVar)) {
                this.s = dVar;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            long j = this.index;
            UnicastProcessor unicastProcessor = this.window;
            if (j == 0) {
                getAndIncrement();
                unicastProcessor = UnicastProcessor.create(this.bufferSize, this);
                this.window = unicastProcessor;
                this.actual.onNext(unicastProcessor);
            }
            j++;
            if (unicastProcessor != null) {
                unicastProcessor.onNext(t);
            }
            if (j == this.size) {
                this.window = null;
                unicastProcessor.onComplete();
            }
            if (j == this.skip) {
                this.index = 0;
            } else {
                this.index = j;
            }
        }

        public void onError(Throwable th) {
            a aVar = this.window;
            if (aVar != null) {
                this.window = null;
                aVar.onError(th);
            }
            this.actual.onError(th);
        }

        public void onComplete() {
            a aVar = this.window;
            if (aVar != null) {
                this.window = null;
                aVar.onComplete();
            }
            this.actual.onComplete();
        }

        public void request(long j) {
            if (!SubscriptionHelper.validate(j)) {
                return;
            }
            if (this.firstRequest.get() || !this.firstRequest.compareAndSet(false, true)) {
                this.s.request(BackpressureHelper.multiplyCap(this.skip, j));
                return;
            }
            this.s.request(BackpressureHelper.addCap(BackpressureHelper.multiplyCap(this.size, j), BackpressureHelper.multiplyCap(this.skip - this.size, j - 1)));
        }

        public void cancel() {
            if (this.once.compareAndSet(false, true)) {
                run();
            }
        }

        public void run() {
            if (decrementAndGet() == 0) {
                this.s.cancel();
            }
        }
    }

    public FlowableWindow(Flowable<T> flowable, long j, long j2, int i) {
        super(flowable);
        this.size = j;
        this.skip = j2;
        this.bufferSize = i;
    }

    public void subscribeActual(c<? super Flowable<T>> cVar) {
        if (this.skip == this.size) {
            this.source.subscribe(new WindowExactSubscriber(cVar, this.size, this.bufferSize));
        } else if (this.skip > this.size) {
            this.source.subscribe(new WindowSkipSubscriber(cVar, this.size, this.skip, this.bufferSize));
        } else {
            this.source.subscribe(new WindowOverlapSubscriber(cVar, this.size, this.skip, this.bufferSize));
        }
    }
}
