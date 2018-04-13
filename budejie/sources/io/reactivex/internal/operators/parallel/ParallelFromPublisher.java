package io.reactivex.internal.operators.parallel;

import com.facebook.common.time.Clock;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.parallel.ParallelFlowable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLongArray;
import org.a.b;
import org.a.c;
import org.a.d;

public final class ParallelFromPublisher<T> extends ParallelFlowable<T> {
    final int parallelism;
    final int prefetch;
    final b<? extends T> source;

    static final class ParallelDispatcher<T> extends AtomicInteger implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -4470634016609963609L;
        volatile boolean cancelled;
        volatile boolean done;
        final long[] emissions;
        Throwable error;
        int index;
        final int limit;
        final int prefetch;
        int produced;
        SimpleQueue<T> queue;
        final AtomicLongArray requests;
        d s;
        int sourceMode;
        final AtomicInteger subscriberCount = new AtomicInteger();
        final c<? super T>[] subscribers;

        final class RailSubscription implements d {
            final int j;
            final int m;

            RailSubscription(int i, int i2) {
                this.j = i;
                this.m = i2;
            }

            public void request(long j) {
                if (SubscriptionHelper.validate(j)) {
                    AtomicLongArray atomicLongArray = ParallelDispatcher.this.requests;
                    long j2;
                    do {
                        j2 = atomicLongArray.get(this.j);
                        if (j2 != Clock.MAX_TIME) {
                        } else {
                            return;
                        }
                    } while (!atomicLongArray.compareAndSet(this.j, j2, BackpressureHelper.addCap(j2, j)));
                    if (ParallelDispatcher.this.subscriberCount.get() == this.m) {
                        ParallelDispatcher.this.drain();
                    }
                }
            }

            public void cancel() {
                if (ParallelDispatcher.this.requests.compareAndSet(this.m + this.j, 0, 1)) {
                    ParallelDispatcher.this.cancel(this.m + this.m);
                }
            }
        }

        ParallelDispatcher(c<? super T>[] cVarArr, int i) {
            this.subscribers = cVarArr;
            this.prefetch = i;
            this.limit = i - (i >> 2);
            int length = cVarArr.length;
            this.requests = new AtomicLongArray((length + length) + 1);
            this.requests.lazySet(length + length, (long) length);
            this.emissions = new long[length];
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.s, dVar)) {
                this.s = dVar;
                if (dVar instanceof QueueSubscription) {
                    QueueSubscription queueSubscription = (QueueSubscription) dVar;
                    int requestFusion = queueSubscription.requestFusion(3);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        this.done = true;
                        setupSubscribers();
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        setupSubscribers();
                        dVar.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                setupSubscribers();
                dVar.request((long) this.prefetch);
            }
        }

        void setupSubscribers() {
            c[] cVarArr = this.subscribers;
            int length = cVarArr.length;
            for (int i = 0; i < length && !this.cancelled; i++) {
                this.subscriberCount.lazySet(i + 1);
                cVarArr[i].onSubscribe(new RailSubscription(i, length));
            }
        }

        public void onNext(T t) {
            if (this.sourceMode != 0 || this.queue.offer(t)) {
                drain();
                return;
            }
            this.s.cancel();
            onError(new MissingBackpressureException("Queue is full?"));
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        void cancel(int i) {
            if (this.requests.decrementAndGet(i) == 0) {
                this.cancelled = true;
                this.s.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void drainAsync() {
            /*
            r19 = this;
            r4 = 1;
            r0 = r19;
            r6 = r0.queue;
            r0 = r19;
            r7 = r0.subscribers;
            r0 = r19;
            r8 = r0.requests;
            r0 = r19;
            r9 = r0.emissions;
            r10 = r9.length;
            r0 = r19;
            r3 = r0.index;
            r0 = r19;
            r2 = r0.produced;
        L_0x001a:
            r5 = 0;
            r18 = r5;
            r5 = r3;
            r3 = r2;
            r2 = r18;
        L_0x0021:
            r0 = r19;
            r11 = r0.cancelled;
            if (r11 == 0) goto L_0x002b;
        L_0x0027:
            r6.clear();
        L_0x002a:
            return;
        L_0x002b:
            r0 = r19;
            r11 = r0.done;
            if (r11 == 0) goto L_0x0046;
        L_0x0031:
            r0 = r19;
            r12 = r0.error;
            if (r12 == 0) goto L_0x0046;
        L_0x0037:
            r6.clear();
            r3 = r7.length;
            r2 = 0;
        L_0x003c:
            if (r2 >= r3) goto L_0x002a;
        L_0x003e:
            r4 = r7[r2];
            r4.onError(r12);
            r2 = r2 + 1;
            goto L_0x003c;
        L_0x0046:
            r12 = r6.isEmpty();
            if (r11 == 0) goto L_0x005a;
        L_0x004c:
            if (r12 == 0) goto L_0x005a;
        L_0x004e:
            r3 = r7.length;
            r2 = 0;
        L_0x0050:
            if (r2 >= r3) goto L_0x002a;
        L_0x0052:
            r4 = r7[r2];
            r4.onComplete();
            r2 = r2 + 1;
            goto L_0x0050;
        L_0x005a:
            if (r12 == 0) goto L_0x0076;
        L_0x005c:
            r2 = r3;
            r3 = r5;
        L_0x005e:
            r5 = r19.get();
            if (r5 != r4) goto L_0x00da;
        L_0x0064:
            r0 = r19;
            r0.index = r3;
            r0 = r19;
            r0.produced = r2;
            r4 = -r4;
            r0 = r19;
            r4 = r0.addAndGet(r4);
            if (r4 != 0) goto L_0x001a;
        L_0x0075:
            goto L_0x002a;
        L_0x0076:
            r12 = r8.get(r5);
            r14 = r9[r5];
            r11 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1));
            if (r11 == 0) goto L_0x00d7;
        L_0x0080:
            r11 = r10 + r5;
            r12 = r8.get(r11);
            r16 = 0;
            r11 = (r12 > r16 ? 1 : (r12 == r16 ? 0 : -1));
            if (r11 != 0) goto L_0x00d7;
        L_0x008c:
            r2 = r6.poll();	 Catch:{ Throwable -> 0x0095 }
            if (r2 != 0) goto L_0x00ac;
        L_0x0092:
            r2 = r3;
            r3 = r5;
            goto L_0x005e;
        L_0x0095:
            r3 = move-exception;
            io.reactivex.exceptions.Exceptions.throwIfFatal(r3);
            r0 = r19;
            r2 = r0.s;
            r2.cancel();
            r4 = r7.length;
            r2 = 0;
        L_0x00a2:
            if (r2 >= r4) goto L_0x002a;
        L_0x00a4:
            r5 = r7[r2];
            r5.onError(r3);
            r2 = r2 + 1;
            goto L_0x00a2;
        L_0x00ac:
            r11 = r7[r5];
            r11.onNext(r2);
            r12 = 1;
            r12 = r12 + r14;
            r9[r5] = r12;
            r3 = r3 + 1;
            r0 = r19;
            r2 = r0.limit;
            if (r3 != r2) goto L_0x00dd;
        L_0x00be:
            r2 = 0;
            r0 = r19;
            r11 = r0.s;
            r12 = (long) r3;
            r11.request(r12);
        L_0x00c7:
            r3 = 0;
            r18 = r3;
            r3 = r2;
            r2 = r18;
        L_0x00cd:
            r5 = r5 + 1;
            if (r5 != r10) goto L_0x00d2;
        L_0x00d1:
            r5 = 0;
        L_0x00d2:
            if (r2 != r10) goto L_0x0021;
        L_0x00d4:
            r2 = r3;
            r3 = r5;
            goto L_0x005e;
        L_0x00d7:
            r2 = r2 + 1;
            goto L_0x00cd;
        L_0x00da:
            r4 = r5;
            goto L_0x001a;
        L_0x00dd:
            r2 = r3;
            goto L_0x00c7;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelFromPublisher.ParallelDispatcher.drainAsync():void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void drainSync() {
            /*
            r18 = this;
            r3 = 1;
            r0 = r18;
            r6 = r0.queue;
            r0 = r18;
            r7 = r0.subscribers;
            r0 = r18;
            r8 = r0.requests;
            r0 = r18;
            r9 = r0.emissions;
            r10 = r9.length;
            r0 = r18;
            r2 = r0.index;
        L_0x0016:
            r4 = 0;
            r5 = r2;
            r2 = r4;
        L_0x0019:
            r0 = r18;
            r4 = r0.cancelled;
            if (r4 == 0) goto L_0x0023;
        L_0x001f:
            r6.clear();
        L_0x0022:
            return;
        L_0x0023:
            r4 = r6.isEmpty();
            if (r4 == 0) goto L_0x0035;
        L_0x0029:
            r3 = r7.length;
            r2 = 0;
        L_0x002b:
            if (r2 >= r3) goto L_0x0022;
        L_0x002d:
            r4 = r7[r2];
            r4.onComplete();
            r2 = r2 + 1;
            goto L_0x002b;
        L_0x0035:
            r12 = r8.get(r5);
            r14 = r9[r5];
            r4 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1));
            if (r4 == 0) goto L_0x009a;
        L_0x003f:
            r4 = r10 + r5;
            r12 = r8.get(r4);
            r16 = 0;
            r4 = (r12 > r16 ? 1 : (r12 == r16 ? 0 : -1));
            if (r4 != 0) goto L_0x009a;
        L_0x004b:
            r2 = r6.poll();	 Catch:{ Throwable -> 0x005d }
            if (r2 != 0) goto L_0x0074;
        L_0x0051:
            r3 = r7.length;
            r2 = 0;
        L_0x0053:
            if (r2 >= r3) goto L_0x0022;
        L_0x0055:
            r4 = r7[r2];
            r4.onComplete();
            r2 = r2 + 1;
            goto L_0x0053;
        L_0x005d:
            r3 = move-exception;
            io.reactivex.exceptions.Exceptions.throwIfFatal(r3);
            r0 = r18;
            r2 = r0.s;
            r2.cancel();
            r4 = r7.length;
            r2 = 0;
        L_0x006a:
            if (r2 >= r4) goto L_0x0022;
        L_0x006c:
            r5 = r7[r2];
            r5.onError(r3);
            r2 = r2 + 1;
            goto L_0x006a;
        L_0x0074:
            r4 = r7[r5];
            r4.onNext(r2);
            r12 = 1;
            r12 = r12 + r14;
            r9[r5] = r12;
            r4 = 0;
        L_0x007f:
            r2 = r5 + 1;
            if (r2 != r10) goto L_0x0084;
        L_0x0083:
            r2 = 0;
        L_0x0084:
            if (r4 != r10) goto L_0x009d;
        L_0x0086:
            r4 = r18.get();
            if (r4 != r3) goto L_0x00a1;
        L_0x008c:
            r0 = r18;
            r0.index = r2;
            r3 = -r3;
            r0 = r18;
            r3 = r0.addAndGet(r3);
            if (r3 != 0) goto L_0x0016;
        L_0x0099:
            goto L_0x0022;
        L_0x009a:
            r4 = r2 + 1;
            goto L_0x007f;
        L_0x009d:
            r5 = r2;
            r2 = r4;
            goto L_0x0019;
        L_0x00a1:
            r3 = r4;
            goto L_0x0016;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelFromPublisher.ParallelDispatcher.drainSync():void");
        }

        void drain() {
            if (getAndIncrement() == 0) {
                if (this.sourceMode == 1) {
                    drainSync();
                } else {
                    drainAsync();
                }
            }
        }
    }

    public ParallelFromPublisher(b<? extends T> bVar, int i, int i2) {
        this.source = bVar;
        this.parallelism = i;
        this.prefetch = i2;
    }

    public int parallelism() {
        return this.parallelism;
    }

    public void subscribe(c<? super T>[] cVarArr) {
        if (validate(cVarArr)) {
            this.source.subscribe(new ParallelDispatcher(cVarArr, this.prefetch));
        }
    }
}
