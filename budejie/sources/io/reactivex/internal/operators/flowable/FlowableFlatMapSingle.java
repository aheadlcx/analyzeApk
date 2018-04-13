package io.reactivex.internal.operators.flowable;

import com.facebook.common.time.Clock;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class FlowableFlatMapSingle<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final boolean delayErrors;
    final Function<? super T, ? extends SingleSource<? extends R>> mapper;
    final int maxConcurrency;

    static final class FlatMapSingleSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, d {
        private static final long serialVersionUID = 8600231336733376951L;
        final AtomicInteger active = new AtomicInteger(1);
        final c<? super R> actual;
        volatile boolean cancelled;
        final boolean delayErrors;
        final AtomicThrowable errors = new AtomicThrowable();
        final Function<? super T, ? extends SingleSource<? extends R>> mapper;
        final int maxConcurrency;
        final AtomicReference<SpscLinkedArrayQueue<R>> queue = new AtomicReference();
        final AtomicLong requested = new AtomicLong();
        d s;
        final CompositeDisposable set = new CompositeDisposable();

        final class InnerObserver extends AtomicReference<Disposable> implements SingleObserver<R>, Disposable {
            private static final long serialVersionUID = -502562646270949838L;

            InnerObserver() {
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            public void onSuccess(R r) {
                FlatMapSingleSubscriber.this.innerSuccess(this, r);
            }

            public void onError(Throwable th) {
                FlatMapSingleSubscriber.this.innerError(this, th);
            }

            public boolean isDisposed() {
                return DisposableHelper.isDisposed((Disposable) get());
            }

            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }

        FlatMapSingleSubscriber(c<? super R> cVar, Function<? super T, ? extends SingleSource<? extends R>> function, boolean z, int i) {
            this.actual = cVar;
            this.mapper = function;
            this.delayErrors = z;
            this.maxConcurrency = i;
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.s, dVar)) {
                this.s = dVar;
                this.actual.onSubscribe(this);
                if (this.maxConcurrency == Integer.MAX_VALUE) {
                    dVar.request(Clock.MAX_TIME);
                } else {
                    dVar.request((long) this.maxConcurrency);
                }
            }
        }

        public void onNext(T t) {
            try {
                SingleSource singleSource = (SingleSource) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null SingleSource");
                this.active.getAndIncrement();
                Object innerObserver = new InnerObserver();
                if (this.set.add(innerObserver)) {
                    singleSource.subscribe(innerObserver);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.s.cancel();
                onError(th);
            }
        }

        public void onError(Throwable th) {
            this.active.decrementAndGet();
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.set.dispose();
                }
                drain();
                return;
            }
            RxJavaPlugins.onError(th);
        }

        public void onComplete() {
            this.active.decrementAndGet();
            drain();
        }

        public void cancel() {
            this.cancelled = true;
            this.s.cancel();
            this.set.dispose();
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                drain();
            }
        }

        void innerSuccess(InnerObserver innerObserver, R r) {
            int i = 0;
            this.set.delete(innerObserver);
            SpscLinkedArrayQueue orCreateQueue;
            if (get() == 0 && compareAndSet(0, 1)) {
                if (this.active.decrementAndGet() == 0) {
                    i = 1;
                }
                if (this.requested.get() != 0) {
                    this.actual.onNext(r);
                    SpscLinkedArrayQueue spscLinkedArrayQueue = (SpscLinkedArrayQueue) this.queue.get();
                    if (i == 0 || !(spscLinkedArrayQueue == null || spscLinkedArrayQueue.isEmpty())) {
                        BackpressureHelper.produced(this.requested, 1);
                        if (this.maxConcurrency != Integer.MAX_VALUE) {
                            this.s.request(1);
                        }
                    } else {
                        Throwable terminate = this.errors.terminate();
                        if (terminate != null) {
                            this.actual.onError(terminate);
                            return;
                        } else {
                            this.actual.onComplete();
                            return;
                        }
                    }
                }
                orCreateQueue = getOrCreateQueue();
                synchronized (orCreateQueue) {
                    orCreateQueue.offer(r);
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            orCreateQueue = getOrCreateQueue();
            synchronized (orCreateQueue) {
                orCreateQueue.offer(r);
            }
            this.active.decrementAndGet();
            if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        SpscLinkedArrayQueue<R> getOrCreateQueue() {
            SpscLinkedArrayQueue<R> spscLinkedArrayQueue;
            do {
                spscLinkedArrayQueue = (SpscLinkedArrayQueue) this.queue.get();
                if (spscLinkedArrayQueue != null) {
                    break;
                }
                spscLinkedArrayQueue = new SpscLinkedArrayQueue(Flowable.bufferSize());
            } while (!this.queue.compareAndSet(null, spscLinkedArrayQueue));
            return spscLinkedArrayQueue;
        }

        void innerError(InnerObserver innerObserver, Throwable th) {
            this.set.delete(innerObserver);
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.s.cancel();
                    this.set.dispose();
                } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                    this.s.request(1);
                }
                this.active.decrementAndGet();
                drain();
                return;
            }
            RxJavaPlugins.onError(th);
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void clear() {
            SpscLinkedArrayQueue spscLinkedArrayQueue = (SpscLinkedArrayQueue) this.queue.get();
            if (spscLinkedArrayQueue != null) {
                spscLinkedArrayQueue.clear();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void drainLoop() {
            /*
            r12 = this;
            r0 = 1;
            r6 = r12.actual;
            r7 = r12.active;
            r8 = r12.queue;
            r1 = r0;
        L_0x0008:
            r0 = r12.requested;
            r10 = r0.get();
            r2 = 0;
            r4 = r2;
        L_0x0011:
            r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
            if (r0 == 0) goto L_0x006e;
        L_0x0015:
            r0 = r12.cancelled;
            if (r0 == 0) goto L_0x001d;
        L_0x0019:
            r12.clear();
        L_0x001c:
            return;
        L_0x001d:
            r0 = r12.delayErrors;
            if (r0 != 0) goto L_0x0038;
        L_0x0021:
            r0 = r12.errors;
            r0 = r0.get();
            r0 = (java.lang.Throwable) r0;
            if (r0 == 0) goto L_0x0038;
        L_0x002b:
            r0 = r12.errors;
            r0 = r0.terminate();
            r12.clear();
            r6.onError(r0);
            goto L_0x001c;
        L_0x0038:
            r0 = r7.get();
            if (r0 != 0) goto L_0x0060;
        L_0x003e:
            r0 = 1;
            r2 = r0;
        L_0x0040:
            r0 = r8.get();
            r0 = (io.reactivex.internal.queue.SpscLinkedArrayQueue) r0;
            if (r0 == 0) goto L_0x0063;
        L_0x0048:
            r0 = r0.poll();
            r3 = r0;
        L_0x004d:
            if (r3 != 0) goto L_0x0066;
        L_0x004f:
            r0 = 1;
        L_0x0050:
            if (r2 == 0) goto L_0x006c;
        L_0x0052:
            if (r0 == 0) goto L_0x006c;
        L_0x0054:
            r0 = r12.errors;
            r0 = r0.terminate();
            if (r0 == 0) goto L_0x0068;
        L_0x005c:
            r6.onError(r0);
            goto L_0x001c;
        L_0x0060:
            r0 = 0;
            r2 = r0;
            goto L_0x0040;
        L_0x0063:
            r0 = 0;
            r3 = r0;
            goto L_0x004d;
        L_0x0066:
            r0 = 0;
            goto L_0x0050;
        L_0x0068:
            r6.onComplete();
            goto L_0x001c;
        L_0x006c:
            if (r0 == 0) goto L_0x007a;
        L_0x006e:
            r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
            if (r0 != 0) goto L_0x00cf;
        L_0x0072:
            r0 = r12.cancelled;
            if (r0 == 0) goto L_0x0082;
        L_0x0076:
            r12.clear();
            goto L_0x001c;
        L_0x007a:
            r6.onNext(r3);
            r2 = 1;
            r2 = r2 + r4;
            r4 = r2;
            goto L_0x0011;
        L_0x0082:
            r0 = r12.delayErrors;
            if (r0 != 0) goto L_0x009d;
        L_0x0086:
            r0 = r12.errors;
            r0 = r0.get();
            r0 = (java.lang.Throwable) r0;
            if (r0 == 0) goto L_0x009d;
        L_0x0090:
            r0 = r12.errors;
            r0 = r0.terminate();
            r12.clear();
            r6.onError(r0);
            goto L_0x001c;
        L_0x009d:
            r0 = r7.get();
            if (r0 != 0) goto L_0x00c5;
        L_0x00a3:
            r0 = 1;
            r2 = r0;
        L_0x00a5:
            r0 = r8.get();
            r0 = (io.reactivex.internal.queue.SpscLinkedArrayQueue) r0;
            if (r0 == 0) goto L_0x00b3;
        L_0x00ad:
            r0 = r0.isEmpty();
            if (r0 == 0) goto L_0x00c8;
        L_0x00b3:
            r0 = 1;
        L_0x00b4:
            if (r2 == 0) goto L_0x00cf;
        L_0x00b6:
            if (r0 == 0) goto L_0x00cf;
        L_0x00b8:
            r0 = r12.errors;
            r0 = r0.terminate();
            if (r0 == 0) goto L_0x00ca;
        L_0x00c0:
            r6.onError(r0);
            goto L_0x001c;
        L_0x00c5:
            r0 = 0;
            r2 = r0;
            goto L_0x00a5;
        L_0x00c8:
            r0 = 0;
            goto L_0x00b4;
        L_0x00ca:
            r6.onComplete();
            goto L_0x001c;
        L_0x00cf:
            r2 = 0;
            r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
            if (r0 == 0) goto L_0x00e6;
        L_0x00d5:
            r0 = r12.requested;
            io.reactivex.internal.util.BackpressureHelper.produced(r0, r4);
            r0 = r12.maxConcurrency;
            r2 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
            if (r0 == r2) goto L_0x00e6;
        L_0x00e1:
            r0 = r12.s;
            r0.request(r4);
        L_0x00e6:
            r0 = -r1;
            r0 = r12.addAndGet(r0);
            if (r0 == 0) goto L_0x001c;
        L_0x00ed:
            r1 = r0;
            goto L_0x0008;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableFlatMapSingle.FlatMapSingleSubscriber.drainLoop():void");
        }
    }

    public FlowableFlatMapSingle(Flowable<T> flowable, Function<? super T, ? extends SingleSource<? extends R>> function, boolean z, int i) {
        super(flowable);
        this.mapper = function;
        this.delayErrors = z;
        this.maxConcurrency = i;
    }

    protected void subscribeActual(c<? super R> cVar) {
        this.source.subscribe(new FlatMapSingleSubscriber(cVar, this.mapper, this.delayErrors, this.maxConcurrency));
    }
}
