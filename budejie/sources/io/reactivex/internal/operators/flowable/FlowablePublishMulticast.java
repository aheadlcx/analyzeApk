package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.QueueDrainHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowablePublishMulticast<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final boolean delayError;
    final int prefetch;
    final Function<? super Flowable<T>, ? extends b<? extends R>> selector;

    static final class MulticastProcessor<T> extends Flowable<T> implements FlowableSubscriber<T>, Disposable {
        static final MulticastSubscription[] EMPTY = new MulticastSubscription[0];
        static final MulticastSubscription[] TERMINATED = new MulticastSubscription[0];
        int consumed;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final int limit;
        final int prefetch;
        volatile SimpleQueue<T> queue;
        final AtomicReference<d> s = new AtomicReference();
        int sourceMode;
        final AtomicReference<MulticastSubscription<T>[]> subscribers = new AtomicReference(EMPTY);
        final AtomicInteger wip = new AtomicInteger();

        MulticastProcessor(int i, boolean z) {
            this.prefetch = i;
            this.limit = i - (i >> 2);
            this.delayError = z;
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.setOnce(this.s, dVar)) {
                if (dVar instanceof QueueSubscription) {
                    QueueSubscription queueSubscription = (QueueSubscription) dVar;
                    int requestFusion = queueSubscription.requestFusion(3);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        this.done = true;
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        QueueDrainHelper.request(dVar, this.prefetch);
                        return;
                    }
                }
                this.queue = QueueDrainHelper.createQueue(this.prefetch);
                QueueDrainHelper.request(dVar, this.prefetch);
            }
        }

        public void dispose() {
            SubscriptionHelper.cancel(this.s);
            if (this.wip.getAndIncrement() == 0) {
                SimpleQueue simpleQueue = this.queue;
                if (simpleQueue != null) {
                    simpleQueue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return SubscriptionHelper.isCancelled((d) this.s.get());
        }

        public void onNext(T t) {
            if (!this.done) {
                if (this.sourceMode != 0 || this.queue.offer(t)) {
                    drain();
                    return;
                }
                ((d) this.s.get()).cancel();
                onError(new MissingBackpressureException());
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        boolean add(MulticastSubscription<T> multicastSubscription) {
            MulticastSubscription[] multicastSubscriptionArr;
            Object obj;
            do {
                multicastSubscriptionArr = (MulticastSubscription[]) this.subscribers.get();
                if (multicastSubscriptionArr == TERMINATED) {
                    return false;
                }
                int length = multicastSubscriptionArr.length;
                obj = new MulticastSubscription[(length + 1)];
                System.arraycopy(multicastSubscriptionArr, 0, obj, 0, length);
                obj[length] = multicastSubscription;
            } while (!this.subscribers.compareAndSet(multicastSubscriptionArr, obj));
            return true;
        }

        void remove(MulticastSubscription<T> multicastSubscription) {
            MulticastSubscription[] multicastSubscriptionArr;
            Object obj;
            do {
                multicastSubscriptionArr = (MulticastSubscription[]) this.subscribers.get();
                if (multicastSubscriptionArr != TERMINATED && multicastSubscriptionArr != EMPTY) {
                    int length = multicastSubscriptionArr.length;
                    int i = -1;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (multicastSubscriptionArr[i2] == multicastSubscription) {
                            i = i2;
                            break;
                        }
                    }
                    if (i < 0) {
                        return;
                    }
                    if (length == 1) {
                        obj = EMPTY;
                    } else {
                        obj = new MulticastSubscription[(length - 1)];
                        System.arraycopy(multicastSubscriptionArr, 0, obj, 0, i);
                        System.arraycopy(multicastSubscriptionArr, i + 1, obj, i, (length - i) - 1);
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(multicastSubscriptionArr, obj));
        }

        protected void subscribeActual(c<? super T> cVar) {
            MulticastSubscription multicastSubscription = new MulticastSubscription(cVar, this);
            cVar.onSubscribe(multicastSubscription);
            if (!add(multicastSubscription)) {
                Throwable th = this.error;
                if (th != null) {
                    cVar.onError(th);
                } else {
                    cVar.onComplete();
                }
            } else if (multicastSubscription.isCancelled()) {
                remove(multicastSubscription);
            } else {
                drain();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void drain() {
            /*
            r20 = this;
            r0 = r20;
            r2 = r0.wip;
            r2 = r2.getAndIncrement();
            if (r2 == 0) goto L_0x000b;
        L_0x000a:
            return;
        L_0x000b:
            r6 = 1;
            r0 = r20;
            r5 = r0.queue;
            r0 = r20;
            r3 = r0.consumed;
            r0 = r20;
            r12 = r0.limit;
            r0 = r20;
            r2 = r0.sourceMode;
            r4 = 1;
            if (r2 == r4) goto L_0x004d;
        L_0x001f:
            r2 = 1;
            r4 = r2;
        L_0x0021:
            r0 = r20;
            r2 = r0.subscribers;
            r2 = r2.get();
            r2 = (io.reactivex.internal.operators.flowable.FlowablePublishMulticast.MulticastSubscription[]) r2;
            r7 = r2.length;
            if (r5 == 0) goto L_0x012c;
        L_0x002e:
            if (r7 == 0) goto L_0x012c;
        L_0x0030:
            r10 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r13 = r2.length;
            r7 = 0;
        L_0x0037:
            if (r7 >= r13) goto L_0x0050;
        L_0x0039:
            r8 = r2[r7];
            r8 = r8.get();
            r14 = -9223372036854775808;
            r14 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1));
            if (r14 == 0) goto L_0x0148;
        L_0x0045:
            r14 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1));
            if (r14 <= 0) goto L_0x0148;
        L_0x0049:
            r7 = r7 + 1;
            r10 = r8;
            goto L_0x0037;
        L_0x004d:
            r2 = 0;
            r4 = r2;
            goto L_0x0021;
        L_0x0050:
            r8 = 0;
        L_0x0052:
            r7 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
            if (r7 == 0) goto L_0x00ab;
        L_0x0056:
            r7 = r20.isDisposed();
            if (r7 == 0) goto L_0x0060;
        L_0x005c:
            r5.clear();
            goto L_0x000a;
        L_0x0060:
            r0 = r20;
            r13 = r0.done;
            if (r13 == 0) goto L_0x0078;
        L_0x0066:
            r0 = r20;
            r7 = r0.delayError;
            if (r7 != 0) goto L_0x0078;
        L_0x006c:
            r0 = r20;
            r7 = r0.error;
            if (r7 == 0) goto L_0x0078;
        L_0x0072:
            r0 = r20;
            r0.errorAll(r7);
            goto L_0x000a;
        L_0x0078:
            r14 = r5.poll();	 Catch:{ Throwable -> 0x0090 }
            if (r14 != 0) goto L_0x00a2;
        L_0x007e:
            r7 = 1;
        L_0x007f:
            if (r13 == 0) goto L_0x00a9;
        L_0x0081:
            if (r7 == 0) goto L_0x00a9;
        L_0x0083:
            r0 = r20;
            r2 = r0.error;
            if (r2 == 0) goto L_0x00a4;
        L_0x0089:
            r0 = r20;
            r0.errorAll(r2);
            goto L_0x000a;
        L_0x0090:
            r2 = move-exception;
            io.reactivex.exceptions.Exceptions.throwIfFatal(r2);
            r0 = r20;
            r3 = r0.s;
            io.reactivex.internal.subscriptions.SubscriptionHelper.cancel(r3);
            r0 = r20;
            r0.errorAll(r2);
            goto L_0x000a;
        L_0x00a2:
            r7 = 0;
            goto L_0x007f;
        L_0x00a4:
            r20.completeAll();
            goto L_0x000a;
        L_0x00a9:
            if (r7 == 0) goto L_0x00ba;
        L_0x00ab:
            r7 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
            if (r7 != 0) goto L_0x0120;
        L_0x00af:
            r7 = r20.isDisposed();
            if (r7 == 0) goto L_0x00ed;
        L_0x00b5:
            r5.clear();
            goto L_0x000a;
        L_0x00ba:
            r13 = r2.length;
            r7 = 0;
        L_0x00bc:
            if (r7 >= r13) goto L_0x00d2;
        L_0x00be:
            r15 = r2[r7];
            r16 = r15.get();
            r18 = -9223372036854775808;
            r16 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1));
            if (r16 == 0) goto L_0x00cf;
        L_0x00ca:
            r15 = r15.actual;
            r15.onNext(r14);
        L_0x00cf:
            r7 = r7 + 1;
            goto L_0x00bc;
        L_0x00d2:
            r14 = 1;
            r8 = r8 + r14;
            if (r4 == 0) goto L_0x0052;
        L_0x00d7:
            r3 = r3 + 1;
            if (r3 != r12) goto L_0x0052;
        L_0x00db:
            r7 = 0;
            r0 = r20;
            r3 = r0.s;
            r3 = r3.get();
            r3 = (org.a.d) r3;
            r14 = (long) r12;
            r3.request(r14);
            r3 = r7;
            goto L_0x0052;
        L_0x00ed:
            r0 = r20;
            r7 = r0.done;
            if (r7 == 0) goto L_0x0106;
        L_0x00f3:
            r0 = r20;
            r10 = r0.delayError;
            if (r10 != 0) goto L_0x0106;
        L_0x00f9:
            r0 = r20;
            r10 = r0.error;
            if (r10 == 0) goto L_0x0106;
        L_0x00ff:
            r0 = r20;
            r0.errorAll(r10);
            goto L_0x000a;
        L_0x0106:
            if (r7 == 0) goto L_0x0120;
        L_0x0108:
            r7 = r5.isEmpty();
            if (r7 == 0) goto L_0x0120;
        L_0x010e:
            r0 = r20;
            r2 = r0.error;
            if (r2 == 0) goto L_0x011b;
        L_0x0114:
            r0 = r20;
            r0.errorAll(r2);
            goto L_0x000a;
        L_0x011b:
            r20.completeAll();
            goto L_0x000a;
        L_0x0120:
            r10 = r2.length;
            r7 = 0;
        L_0x0122:
            if (r7 >= r10) goto L_0x012c;
        L_0x0124:
            r11 = r2[r7];
            io.reactivex.internal.util.BackpressureHelper.produced(r11, r8);
            r7 = r7 + 1;
            goto L_0x0122;
        L_0x012c:
            r2 = r3;
            r0 = r20;
            r0.consumed = r2;
            r0 = r20;
            r3 = r0.wip;
            r6 = -r6;
            r6 = r3.addAndGet(r6);
            if (r6 == 0) goto L_0x000a;
        L_0x013c:
            if (r5 != 0) goto L_0x0146;
        L_0x013e:
            r0 = r20;
            r3 = r0.queue;
        L_0x0142:
            r5 = r3;
            r3 = r2;
            goto L_0x0021;
        L_0x0146:
            r3 = r5;
            goto L_0x0142;
        L_0x0148:
            r8 = r10;
            goto L_0x0049;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowablePublishMulticast.MulticastProcessor.drain():void");
        }

        void errorAll(Throwable th) {
            for (MulticastSubscription multicastSubscription : (MulticastSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                if (multicastSubscription.get() != Long.MIN_VALUE) {
                    multicastSubscription.actual.onError(th);
                }
            }
        }

        void completeAll() {
            for (MulticastSubscription multicastSubscription : (MulticastSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                if (multicastSubscription.get() != Long.MIN_VALUE) {
                    multicastSubscription.actual.onComplete();
                }
            }
        }
    }

    static final class MulticastSubscription<T> extends AtomicLong implements d {
        private static final long serialVersionUID = 8664815189257569791L;
        final c<? super T> actual;
        final MulticastProcessor<T> parent;

        MulticastSubscription(c<? super T> cVar, MulticastProcessor<T> multicastProcessor) {
            this.actual = cVar;
            this.parent = multicastProcessor;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.addCancel(this, j);
                this.parent.drain();
            }
        }

        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
                this.parent.drain();
            }
        }

        public boolean isCancelled() {
            return get() == Long.MIN_VALUE;
        }
    }

    static final class OutputCanceller<R> implements FlowableSubscriber<R>, d {
        final c<? super R> actual;
        final MulticastProcessor<?> processor;
        d s;

        OutputCanceller(c<? super R> cVar, MulticastProcessor<?> multicastProcessor) {
            this.actual = cVar;
            this.processor = multicastProcessor;
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.s, dVar)) {
                this.s = dVar;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(R r) {
            this.actual.onNext(r);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
            this.processor.dispose();
        }

        public void onComplete() {
            this.actual.onComplete();
            this.processor.dispose();
        }

        public void request(long j) {
            this.s.request(j);
        }

        public void cancel() {
            this.s.cancel();
            this.processor.dispose();
        }
    }

    public FlowablePublishMulticast(Flowable<T> flowable, Function<? super Flowable<T>, ? extends b<? extends R>> function, int i, boolean z) {
        super(flowable);
        this.selector = function;
        this.prefetch = i;
        this.delayError = z;
    }

    protected void subscribeActual(c<? super R> cVar) {
        FlowableSubscriber multicastProcessor = new MulticastProcessor(this.prefetch, this.delayError);
        try {
            ((b) ObjectHelper.requireNonNull(this.selector.apply(multicastProcessor), "selector returned a null Publisher")).subscribe(new OutputCanceller(cVar, multicastProcessor));
            this.source.subscribe(multicastProcessor);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptySubscription.error(th, cVar);
        }
    }
}
