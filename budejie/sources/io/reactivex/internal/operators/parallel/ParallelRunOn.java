package io.reactivex.internal.operators.parallel;

import com.facebook.common.time.Clock;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.Scheduler$Worker;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.a.c;
import org.a.d;

public final class ParallelRunOn<T> extends ParallelFlowable<T> {
    final int prefetch;
    final Scheduler scheduler;
    final ParallelFlowable<? extends T> source;

    static abstract class BaseRunOnSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Runnable, d {
        private static final long serialVersionUID = 9222303586456402150L;
        volatile boolean cancelled;
        int consumed;
        volatile boolean done;
        Throwable error;
        final int limit;
        final int prefetch;
        final SpscArrayQueue<T> queue;
        final AtomicLong requested = new AtomicLong();
        d s;
        final Scheduler$Worker worker;

        BaseRunOnSubscriber(int i, SpscArrayQueue<T> spscArrayQueue, Scheduler$Worker scheduler$Worker) {
            this.prefetch = i;
            this.queue = spscArrayQueue;
            this.limit = i - (i >> 2);
            this.worker = scheduler$Worker;
        }

        public final void onNext(T t) {
            if (!this.done) {
                if (this.queue.offer(t)) {
                    schedule();
                    return;
                }
                this.s.cancel();
                onError(new MissingBackpressureException("Queue is full?!"));
            }
        }

        public final void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.error = th;
            this.done = true;
            schedule();
        }

        public final void onComplete() {
            if (!this.done) {
                this.done = true;
                schedule();
            }
        }

        public final void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                schedule();
            }
        }

        public final void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.s.cancel();
                this.worker.dispose();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        final void schedule() {
            if (getAndIncrement() == 0) {
                this.worker.schedule(this);
            }
        }
    }

    static final class RunOnConditionalSubscriber<T> extends BaseRunOnSubscriber<T> {
        private static final long serialVersionUID = 1075119423897941642L;
        final ConditionalSubscriber<? super T> actual;

        RunOnConditionalSubscriber(ConditionalSubscriber<? super T> conditionalSubscriber, int i, SpscArrayQueue<T> spscArrayQueue, Scheduler$Worker scheduler$Worker) {
            super(i, spscArrayQueue, scheduler$Worker);
            this.actual = conditionalSubscriber;
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.s, dVar)) {
                this.s = dVar;
                this.actual.onSubscribe(this);
                dVar.request((long) this.prefetch);
            }
        }

        public void run() {
            int i = 1;
            int i2 = this.consumed;
            SpscArrayQueue spscArrayQueue = this.queue;
            ConditionalSubscriber conditionalSubscriber = this.actual;
            int i3 = this.limit;
            while (true) {
                int i4;
                long j = this.requested.get();
                long j2 = 0;
                while (j2 != j) {
                    if (this.cancelled) {
                        spscArrayQueue.clear();
                        return;
                    }
                    Throwable th;
                    boolean z = this.done;
                    if (z) {
                        th = this.error;
                        if (th != null) {
                            spscArrayQueue.clear();
                            conditionalSubscriber.onError(th);
                            this.worker.dispose();
                            return;
                        }
                    }
                    Object poll = spscArrayQueue.poll();
                    Object obj = poll == null ? 1 : null;
                    if (z && obj != null) {
                        conditionalSubscriber.onComplete();
                        this.worker.dispose();
                        return;
                    } else if (obj != null) {
                        break;
                    } else {
                        long j3;
                        if (conditionalSubscriber.tryOnNext(poll)) {
                            j3 = j2 + 1;
                        } else {
                            j3 = j2;
                        }
                        i4 = i2 + 1;
                        if (i4 == i3) {
                            i2 = 0;
                            this.s.request((long) i4);
                        } else {
                            i2 = i4;
                        }
                        j2 = j3;
                    }
                }
                if (j2 == j) {
                    if (this.cancelled) {
                        spscArrayQueue.clear();
                        return;
                    } else if (this.done) {
                        th = this.error;
                        if (th != null) {
                            spscArrayQueue.clear();
                            conditionalSubscriber.onError(th);
                            this.worker.dispose();
                            return;
                        } else if (spscArrayQueue.isEmpty()) {
                            conditionalSubscriber.onComplete();
                            this.worker.dispose();
                            return;
                        }
                    }
                }
                if (!(j2 == 0 || j == Clock.MAX_TIME)) {
                    this.requested.addAndGet(-j2);
                }
                i4 = get();
                if (i4 == i) {
                    this.consumed = i2;
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    i = i4;
                }
            }
        }
    }

    static final class RunOnSubscriber<T> extends BaseRunOnSubscriber<T> {
        private static final long serialVersionUID = 1075119423897941642L;
        final c<? super T> actual;

        RunOnSubscriber(c<? super T> cVar, int i, SpscArrayQueue<T> spscArrayQueue, Scheduler$Worker scheduler$Worker) {
            super(i, spscArrayQueue, scheduler$Worker);
            this.actual = cVar;
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.s, dVar)) {
                this.s = dVar;
                this.actual.onSubscribe(this);
                dVar.request((long) this.prefetch);
            }
        }

        public void run() {
            int i = 1;
            int i2 = this.consumed;
            SpscArrayQueue spscArrayQueue = this.queue;
            c cVar = this.actual;
            int i3 = this.limit;
            while (true) {
                int i4;
                long j = this.requested.get();
                long j2 = 0;
                while (j2 != j) {
                    if (this.cancelled) {
                        spscArrayQueue.clear();
                        return;
                    }
                    Throwable th;
                    boolean z = this.done;
                    if (z) {
                        th = this.error;
                        if (th != null) {
                            spscArrayQueue.clear();
                            cVar.onError(th);
                            this.worker.dispose();
                            return;
                        }
                    }
                    Object poll = spscArrayQueue.poll();
                    Object obj = poll == null ? 1 : null;
                    if (z && obj != null) {
                        cVar.onComplete();
                        this.worker.dispose();
                        return;
                    } else if (obj != null) {
                        break;
                    } else {
                        cVar.onNext(poll);
                        j2++;
                        i4 = i2 + 1;
                        if (i4 == i3) {
                            i2 = 0;
                            this.s.request((long) i4);
                        } else {
                            i2 = i4;
                        }
                    }
                }
                if (j2 == j) {
                    if (this.cancelled) {
                        spscArrayQueue.clear();
                        return;
                    } else if (this.done) {
                        th = this.error;
                        if (th != null) {
                            spscArrayQueue.clear();
                            cVar.onError(th);
                            this.worker.dispose();
                            return;
                        } else if (spscArrayQueue.isEmpty()) {
                            cVar.onComplete();
                            this.worker.dispose();
                            return;
                        }
                    }
                }
                if (!(j2 == 0 || j == Clock.MAX_TIME)) {
                    this.requested.addAndGet(-j2);
                }
                i4 = get();
                if (i4 == i) {
                    this.consumed = i2;
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    i = i4;
                }
            }
        }
    }

    public ParallelRunOn(ParallelFlowable<? extends T> parallelFlowable, Scheduler scheduler, int i) {
        this.source = parallelFlowable;
        this.scheduler = scheduler;
        this.prefetch = i;
    }

    public void subscribe(c<? super T>[] cVarArr) {
        if (validate(cVarArr)) {
            int length = cVarArr.length;
            c[] cVarArr2 = new c[length];
            int i = this.prefetch;
            for (int i2 = 0; i2 < length; i2++) {
                c cVar = cVarArr[i2];
                Scheduler$Worker createWorker = this.scheduler.createWorker();
                SpscArrayQueue spscArrayQueue = new SpscArrayQueue(i);
                if (cVar instanceof ConditionalSubscriber) {
                    cVarArr2[i2] = new RunOnConditionalSubscriber((ConditionalSubscriber) cVar, i, spscArrayQueue, createWorker);
                } else {
                    cVarArr2[i2] = new RunOnSubscriber(cVar, i, spscArrayQueue, createWorker);
                }
            }
            this.source.subscribe(cVarArr2);
        }
    }

    public int parallelism() {
        return this.source.parallelism();
    }
}
