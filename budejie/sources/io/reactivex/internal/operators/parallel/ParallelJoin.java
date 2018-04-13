package io.reactivex.internal.operators.parallel;

import com.facebook.common.time.Clock;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class ParallelJoin<T> extends Flowable<T> {
    final boolean delayErrors;
    final int prefetch;
    final ParallelFlowable<? extends T> source;

    static final class JoinInnerSubscriber<T> extends AtomicReference<d> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = 8410034718427740355L;
        final int limit;
        final JoinSubscriptionBase<T> parent;
        final int prefetch;
        long produced;
        volatile SimplePlainQueue<T> queue;

        JoinInnerSubscriber(JoinSubscriptionBase<T> joinSubscriptionBase, int i) {
            this.parent = joinSubscriptionBase;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.setOnce(this, dVar)) {
                dVar.request((long) this.prefetch);
            }
        }

        public void onNext(T t) {
            this.parent.onNext(this, t);
        }

        public void onError(Throwable th) {
            this.parent.onError(th);
        }

        public void onComplete() {
            this.parent.onComplete();
        }

        public void requestOne() {
            long j = 1 + this.produced;
            if (j == ((long) this.limit)) {
                this.produced = 0;
                ((d) get()).request(j);
                return;
            }
            this.produced = j;
        }

        public void request(long j) {
            long j2 = this.produced + j;
            if (j2 >= ((long) this.limit)) {
                this.produced = 0;
                ((d) get()).request(j2);
                return;
            }
            this.produced = j2;
        }

        public boolean cancel() {
            return SubscriptionHelper.cancel(this);
        }

        SimplePlainQueue<T> getQueue() {
            SimplePlainQueue<T> simplePlainQueue = this.queue;
            if (simplePlainQueue != null) {
                return simplePlainQueue;
            }
            simplePlainQueue = new SpscArrayQueue(this.prefetch);
            this.queue = simplePlainQueue;
            return simplePlainQueue;
        }
    }

    static abstract class JoinSubscriptionBase<T> extends AtomicInteger implements d {
        private static final long serialVersionUID = 3100232009247827843L;
        final c<? super T> actual;
        volatile boolean cancelled;
        final AtomicInteger done = new AtomicInteger();
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicLong requested = new AtomicLong();
        final JoinInnerSubscriber<T>[] subscribers;

        abstract void drain();

        abstract void onComplete();

        abstract void onError(Throwable th);

        abstract void onNext(JoinInnerSubscriber<T> joinInnerSubscriber, T t);

        JoinSubscriptionBase(c<? super T> cVar, int i, int i2) {
            this.actual = cVar;
            JoinInnerSubscriber[] joinInnerSubscriberArr = new JoinInnerSubscriber[i];
            for (int i3 = 0; i3 < i; i3++) {
                joinInnerSubscriberArr[i3] = new JoinInnerSubscriber(this, i2);
            }
            this.subscribers = joinInnerSubscriberArr;
            this.done.lazySet(i);
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    cleanup();
                }
            }
        }

        void cancelAll() {
            for (JoinInnerSubscriber cancel : this.subscribers) {
                cancel.cancel();
            }
        }

        void cleanup() {
            for (JoinInnerSubscriber joinInnerSubscriber : this.subscribers) {
                joinInnerSubscriber.queue = null;
            }
        }
    }

    static final class JoinSubscription<T> extends JoinSubscriptionBase<T> {
        private static final long serialVersionUID = 6312374661811000451L;

        JoinSubscription(c<? super T> cVar, int i, int i2) {
            super(cVar, i, i2);
        }

        public void onNext(JoinInnerSubscriber<T> joinInnerSubscriber, T t) {
            if (get() == 0 && compareAndSet(0, 1)) {
                if (this.requested.get() != 0) {
                    this.actual.onNext(t);
                    if (this.requested.get() != Clock.MAX_TIME) {
                        this.requested.decrementAndGet();
                    }
                    joinInnerSubscriber.request(1);
                } else if (!joinInnerSubscriber.getQueue().offer(t)) {
                    cancelAll();
                    Throwable missingBackpressureException = new MissingBackpressureException("Queue full?!");
                    if (this.errors.compareAndSet(null, missingBackpressureException)) {
                        this.actual.onError(missingBackpressureException);
                        return;
                    } else {
                        RxJavaPlugins.onError(missingBackpressureException);
                        return;
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else if (!joinInnerSubscriber.getQueue().offer(t)) {
                cancelAll();
                onError(new MissingBackpressureException("Queue full?!"));
                return;
            } else if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        public void onError(Throwable th) {
            if (this.errors.compareAndSet(null, th)) {
                cancelAll();
                drain();
            } else if (th != this.errors.get()) {
                RxJavaPlugins.onError(th);
            }
        }

        public void onComplete() {
            this.done.decrementAndGet();
            drain();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void drainLoop() {
            JoinInnerSubscriber[] joinInnerSubscriberArr = this.subscribers;
            c cVar = this.actual;
            int i = 1;
            while (true) {
                long j = this.requested.get();
                long j2 = 0;
                while (j2 != j) {
                    if (this.cancelled) {
                        cleanup();
                        return;
                    }
                    Throwable th = (Throwable) this.errors.get();
                    if (th != null) {
                        cleanup();
                        cVar.onError(th);
                        return;
                    }
                    Object obj = this.done.get() == 0 ? 1 : null;
                    Object obj2 = 1;
                    for (JoinInnerSubscriber joinInnerSubscriber : joinInnerSubscriberArr) {
                        SimplePlainQueue simplePlainQueue = joinInnerSubscriber.queue;
                        if (simplePlainQueue != null) {
                            Object poll = simplePlainQueue.poll();
                            if (poll != null) {
                                obj2 = null;
                                cVar.onNext(poll);
                                joinInnerSubscriber.requestOne();
                                j2++;
                                if (j2 == j) {
                                    break;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                    if (obj == null || obj2 == null) {
                        if (obj2 != null) {
                            break;
                        }
                    } else {
                        cVar.onComplete();
                        return;
                    }
                }
                if (j2 == j) {
                    if (this.cancelled) {
                        cleanup();
                        return;
                    }
                    th = (Throwable) this.errors.get();
                    if (th != null) {
                        cleanup();
                        cVar.onError(th);
                        return;
                    }
                    obj = this.done.get() == 0 ? 1 : null;
                    Object obj3 = 1;
                    for (JoinInnerSubscriber joinInnerSubscriber2 : joinInnerSubscriberArr) {
                        SimpleQueue simpleQueue = joinInnerSubscriber2.queue;
                        if (simpleQueue != null && !simpleQueue.isEmpty()) {
                            obj3 = null;
                            break;
                        }
                    }
                    if (!(obj == null || r2 == null)) {
                        cVar.onComplete();
                        return;
                    }
                }
                if (!(j2 == 0 || j == Clock.MAX_TIME)) {
                    this.requested.addAndGet(-j2);
                }
                int i2 = get();
                if (i2 == i) {
                    i2 = addAndGet(-i);
                    if (i2 == 0) {
                        return;
                    }
                }
                i = i2;
            }
        }
    }

    static final class JoinSubscriptionDelayError<T> extends JoinSubscriptionBase<T> {
        private static final long serialVersionUID = -5737965195918321883L;

        JoinSubscriptionDelayError(c<? super T> cVar, int i, int i2) {
            super(cVar, i, i2);
        }

        void onNext(JoinInnerSubscriber<T> joinInnerSubscriber, T t) {
            if (get() == 0 && compareAndSet(0, 1)) {
                if (this.requested.get() != 0) {
                    this.actual.onNext(t);
                    if (this.requested.get() != Clock.MAX_TIME) {
                        this.requested.decrementAndGet();
                    }
                    joinInnerSubscriber.request(1);
                } else if (!joinInnerSubscriber.getQueue().offer(t)) {
                    joinInnerSubscriber.cancel();
                    this.errors.addThrowable(new MissingBackpressureException("Queue full?!"));
                    this.done.decrementAndGet();
                    drainLoop();
                    return;
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            if (!joinInnerSubscriber.getQueue().offer(t) && joinInnerSubscriber.cancel()) {
                this.errors.addThrowable(new MissingBackpressureException("Queue full?!"));
                this.done.decrementAndGet();
            }
            if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        void onError(Throwable th) {
            this.errors.addThrowable(th);
            this.done.decrementAndGet();
            drain();
        }

        void onComplete() {
            this.done.decrementAndGet();
            drain();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void drainLoop() {
            /*
            r15 = this;
            r0 = 1;
            r6 = r15.subscribers;
            r7 = r6.length;
            r8 = r15.actual;
        L_0x0006:
            r1 = r15.requested;
            r10 = r1.get();
            r4 = 0;
        L_0x000e:
            r1 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
            if (r1 == 0) goto L_0x0044;
        L_0x0012:
            r1 = r15.cancelled;
            if (r1 == 0) goto L_0x001a;
        L_0x0016:
            r15.cleanup();
        L_0x0019:
            return;
        L_0x001a:
            r1 = r15.done;
            r1 = r1.get();
            if (r1 != 0) goto L_0x0050;
        L_0x0022:
            r1 = 1;
        L_0x0023:
            r3 = 1;
            r2 = 0;
            r14 = r2;
            r2 = r3;
            r3 = r14;
        L_0x0028:
            if (r3 >= r7) goto L_0x0055;
        L_0x002a:
            r9 = r6[r3];
            r12 = r9.queue;
            if (r12 == 0) goto L_0x0052;
        L_0x0030:
            r12 = r12.poll();
            if (r12 == 0) goto L_0x0052;
        L_0x0036:
            r2 = 0;
            r8.onNext(r12);
            r9.requestOne();
            r12 = 1;
            r4 = r4 + r12;
            r9 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
            if (r9 != 0) goto L_0x0052;
        L_0x0044:
            r1 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
            if (r1 != 0) goto L_0x00b1;
        L_0x0048:
            r1 = r15.cancelled;
            if (r1 == 0) goto L_0x0074;
        L_0x004c:
            r15.cleanup();
            goto L_0x0019;
        L_0x0050:
            r1 = 0;
            goto L_0x0023;
        L_0x0052:
            r3 = r3 + 1;
            goto L_0x0028;
        L_0x0055:
            if (r1 == 0) goto L_0x0071;
        L_0x0057:
            if (r2 == 0) goto L_0x0071;
        L_0x0059:
            r0 = r15.errors;
            r0 = r0.get();
            r0 = (java.lang.Throwable) r0;
            if (r0 == 0) goto L_0x006d;
        L_0x0063:
            r0 = r15.errors;
            r0 = r0.terminate();
            r8.onError(r0);
            goto L_0x0019;
        L_0x006d:
            r8.onComplete();
            goto L_0x0019;
        L_0x0071:
            if (r2 == 0) goto L_0x000e;
        L_0x0073:
            goto L_0x0044;
        L_0x0074:
            r1 = r15.done;
            r1 = r1.get();
            if (r1 != 0) goto L_0x00a7;
        L_0x007c:
            r1 = 1;
        L_0x007d:
            r2 = 1;
            r3 = 0;
        L_0x007f:
            if (r3 >= r7) goto L_0x008e;
        L_0x0081:
            r9 = r6[r3];
            r9 = r9.queue;
            if (r9 == 0) goto L_0x00a9;
        L_0x0087:
            r9 = r9.isEmpty();
            if (r9 != 0) goto L_0x00a9;
        L_0x008d:
            r2 = 0;
        L_0x008e:
            if (r1 == 0) goto L_0x00b1;
        L_0x0090:
            if (r2 == 0) goto L_0x00b1;
        L_0x0092:
            r0 = r15.errors;
            r0 = r0.get();
            r0 = (java.lang.Throwable) r0;
            if (r0 == 0) goto L_0x00ac;
        L_0x009c:
            r0 = r15.errors;
            r0 = r0.terminate();
            r8.onError(r0);
            goto L_0x0019;
        L_0x00a7:
            r1 = 0;
            goto L_0x007d;
        L_0x00a9:
            r3 = r3 + 1;
            goto L_0x007f;
        L_0x00ac:
            r8.onComplete();
            goto L_0x0019;
        L_0x00b1:
            r2 = 0;
            r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
            if (r1 == 0) goto L_0x00c6;
        L_0x00b7:
            r2 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r1 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1));
            if (r1 == 0) goto L_0x00c6;
        L_0x00c0:
            r1 = r15.requested;
            r2 = -r4;
            r1.addAndGet(r2);
        L_0x00c6:
            r1 = r15.get();
            if (r1 != r0) goto L_0x00d5;
        L_0x00cc:
            r0 = -r0;
            r0 = r15.addAndGet(r0);
            if (r0 != 0) goto L_0x0006;
        L_0x00d3:
            goto L_0x0019;
        L_0x00d5:
            r0 = r1;
            goto L_0x0006;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelJoin.JoinSubscriptionDelayError.drainLoop():void");
        }
    }

    public ParallelJoin(ParallelFlowable<? extends T> parallelFlowable, int i, boolean z) {
        this.source = parallelFlowable;
        this.prefetch = i;
        this.delayErrors = z;
    }

    protected void subscribeActual(c<? super T> cVar) {
        Object joinSubscriptionDelayError;
        if (this.delayErrors) {
            joinSubscriptionDelayError = new JoinSubscriptionDelayError(cVar, this.source.parallelism(), this.prefetch);
        } else {
            joinSubscriptionDelayError = new JoinSubscription(cVar, this.source.parallelism(), this.prefetch);
        }
        cVar.onSubscribe(joinSubscriptionDelayError);
        this.source.subscribe(joinSubscriptionDelayError.subscribers);
    }
}
