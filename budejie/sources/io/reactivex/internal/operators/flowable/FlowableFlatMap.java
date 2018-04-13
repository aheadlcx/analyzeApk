package io.reactivex.internal.operators.flowable;

import com.facebook.common.time.Clock;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableFlatMap<T, U> extends AbstractFlowableWithUpstream<T, U> {
    final int bufferSize;
    final boolean delayErrors;
    final Function<? super T, ? extends b<? extends U>> mapper;
    final int maxConcurrency;

    static final class InnerSubscriber<T, U> extends AtomicReference<d> implements FlowableSubscriber<U>, Disposable {
        private static final long serialVersionUID = -4606175640614850599L;
        final int bufferSize;
        volatile boolean done;
        int fusionMode;
        final long id;
        final int limit = (this.bufferSize >> 2);
        final MergeSubscriber<T, U> parent;
        long produced;
        volatile SimpleQueue<U> queue;

        InnerSubscriber(MergeSubscriber<T, U> mergeSubscriber, long j) {
            this.id = j;
            this.parent = mergeSubscriber;
            this.bufferSize = mergeSubscriber.bufferSize;
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.setOnce(this, dVar)) {
                if (dVar instanceof QueueSubscription) {
                    QueueSubscription queueSubscription = (QueueSubscription) dVar;
                    int requestFusion = queueSubscription.requestFusion(7);
                    if (requestFusion == 1) {
                        this.fusionMode = requestFusion;
                        this.queue = queueSubscription;
                        this.done = true;
                        this.parent.drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.fusionMode = requestFusion;
                        this.queue = queueSubscription;
                    }
                }
                dVar.request((long) this.bufferSize);
            }
        }

        public void onNext(U u) {
            if (this.fusionMode != 2) {
                this.parent.tryEmit(u, this);
            } else {
                this.parent.drain();
            }
        }

        public void onError(Throwable th) {
            lazySet(SubscriptionHelper.CANCELLED);
            this.parent.innerError(this, th);
        }

        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        void requestMore(long j) {
            if (this.fusionMode != 1) {
                long j2 = this.produced + j;
                if (j2 >= ((long) this.limit)) {
                    this.produced = 0;
                    ((d) get()).request(j2);
                    return;
                }
                this.produced = j2;
            }
        }

        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        public boolean isDisposed() {
            return get() == SubscriptionHelper.CANCELLED;
        }
    }

    static final class MergeSubscriber<T, U> extends AtomicInteger implements FlowableSubscriber<T>, d {
        static final InnerSubscriber<?, ?>[] CANCELLED = new InnerSubscriber[0];
        static final InnerSubscriber<?, ?>[] EMPTY = new InnerSubscriber[0];
        private static final long serialVersionUID = -2117620485640801370L;
        final c<? super U> actual;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final AtomicThrowable errs = new AtomicThrowable();
        long lastId;
        int lastIndex;
        final Function<? super T, ? extends b<? extends U>> mapper;
        final int maxConcurrency;
        volatile SimplePlainQueue<U> queue;
        final AtomicLong requested = new AtomicLong();
        d s;
        int scalarEmitted;
        final int scalarLimit;
        final AtomicReference<InnerSubscriber<?, ?>[]> subscribers = new AtomicReference();
        long uniqueId;

        MergeSubscriber(c<? super U> cVar, Function<? super T, ? extends b<? extends U>> function, boolean z, int i, int i2) {
            this.actual = cVar;
            this.mapper = function;
            this.delayErrors = z;
            this.maxConcurrency = i;
            this.bufferSize = i2;
            this.scalarLimit = Math.max(1, i >> 1);
            this.subscribers.lazySet(EMPTY);
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.s, dVar)) {
                this.s = dVar;
                this.actual.onSubscribe(this);
                if (!this.cancelled) {
                    if (this.maxConcurrency == Integer.MAX_VALUE) {
                        dVar.request(Clock.MAX_TIME);
                    } else {
                        dVar.request((long) this.maxConcurrency);
                    }
                }
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    b bVar = (b) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null Publisher");
                    if (bVar instanceof Callable) {
                        try {
                            Object call = ((Callable) bVar).call();
                            if (call != null) {
                                tryEmitScalar(call);
                                return;
                            } else if (this.maxConcurrency != Integer.MAX_VALUE && !this.cancelled) {
                                int i = this.scalarEmitted + 1;
                                this.scalarEmitted = i;
                                if (i == this.scalarLimit) {
                                    this.scalarEmitted = 0;
                                    this.s.request((long) this.scalarLimit);
                                    return;
                                }
                                return;
                            } else {
                                return;
                            }
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            this.errs.addThrowable(th);
                            drain();
                            return;
                        }
                    }
                    long j = this.uniqueId;
                    this.uniqueId = 1 + j;
                    c innerSubscriber = new InnerSubscriber(this, j);
                    if (addInner(innerSubscriber)) {
                        bVar.subscribe(innerSubscriber);
                    }
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    this.s.cancel();
                    onError(th2);
                }
            }
        }

        boolean addInner(InnerSubscriber<T, U> innerSubscriber) {
            InnerSubscriber[] innerSubscriberArr;
            Object obj;
            do {
                innerSubscriberArr = (InnerSubscriber[]) this.subscribers.get();
                if (innerSubscriberArr == CANCELLED) {
                    innerSubscriber.dispose();
                    return false;
                }
                int length = innerSubscriberArr.length;
                obj = new InnerSubscriber[(length + 1)];
                System.arraycopy(innerSubscriberArr, 0, obj, 0, length);
                obj[length] = innerSubscriber;
            } while (!this.subscribers.compareAndSet(innerSubscriberArr, obj));
            return true;
        }

        void removeInner(InnerSubscriber<T, U> innerSubscriber) {
            InnerSubscriber[] innerSubscriberArr;
            Object obj;
            do {
                innerSubscriberArr = (InnerSubscriber[]) this.subscribers.get();
                if (innerSubscriberArr != CANCELLED && innerSubscriberArr != EMPTY) {
                    int length = innerSubscriberArr.length;
                    int i = -1;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (innerSubscriberArr[i2] == innerSubscriber) {
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
                        obj = new InnerSubscriber[(length - 1)];
                        System.arraycopy(innerSubscriberArr, 0, obj, 0, i);
                        System.arraycopy(innerSubscriberArr, i + 1, obj, i, (length - i) - 1);
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(innerSubscriberArr, obj));
        }

        SimpleQueue<U> getMainQueue() {
            SimpleQueue<U> simpleQueue = this.queue;
            if (simpleQueue == null) {
                if (this.maxConcurrency == Integer.MAX_VALUE) {
                    simpleQueue = new SpscLinkedArrayQueue(this.bufferSize);
                } else {
                    simpleQueue = new SpscArrayQueue(this.maxConcurrency);
                }
                this.queue = simpleQueue;
            }
            return simpleQueue;
        }

        void tryEmitScalar(U u) {
            if (get() == 0 && compareAndSet(0, 1)) {
                long j = this.requested.get();
                SimpleQueue simpleQueue = this.queue;
                if (j == 0 || !(simpleQueue == null || simpleQueue.isEmpty())) {
                    if (simpleQueue == null) {
                        simpleQueue = getMainQueue();
                    }
                    if (!simpleQueue.offer(u)) {
                        onError(new IllegalStateException("Scalar queue full?!"));
                        return;
                    }
                }
                this.actual.onNext(u);
                if (j != Clock.MAX_TIME) {
                    this.requested.decrementAndGet();
                }
                if (!(this.maxConcurrency == Integer.MAX_VALUE || this.cancelled)) {
                    int i = this.scalarEmitted + 1;
                    this.scalarEmitted = i;
                    if (i == this.scalarLimit) {
                        this.scalarEmitted = 0;
                        this.s.request((long) this.scalarLimit);
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else if (!getMainQueue().offer(u)) {
                onError(new IllegalStateException("Scalar queue full?!"));
                return;
            } else if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        SimpleQueue<U> getInnerQueue(InnerSubscriber<T, U> innerSubscriber) {
            SimpleQueue<U> simpleQueue = innerSubscriber.queue;
            if (simpleQueue != null) {
                return simpleQueue;
            }
            simpleQueue = new SpscArrayQueue(this.bufferSize);
            innerSubscriber.queue = simpleQueue;
            return simpleQueue;
        }

        void tryEmit(U u, InnerSubscriber<T, U> innerSubscriber) {
            SimpleQueue simpleQueue;
            if (get() == 0 && compareAndSet(0, 1)) {
                long j = this.requested.get();
                simpleQueue = innerSubscriber.queue;
                if (j == 0 || !(simpleQueue == null || simpleQueue.isEmpty())) {
                    if (simpleQueue == null) {
                        simpleQueue = getInnerQueue(innerSubscriber);
                    }
                    if (!simpleQueue.offer(u)) {
                        onError(new MissingBackpressureException("Inner queue full?!"));
                        return;
                    }
                }
                this.actual.onNext(u);
                if (j != Clock.MAX_TIME) {
                    this.requested.decrementAndGet();
                }
                innerSubscriber.requestMore(1);
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            simpleQueue = innerSubscriber.queue;
            if (simpleQueue == null) {
                simpleQueue = new SpscArrayQueue(this.bufferSize);
                innerSubscriber.queue = simpleQueue;
            }
            if (!simpleQueue.offer(u)) {
                onError(new MissingBackpressureException("Inner queue full?!"));
                return;
            } else if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
            } else if (this.errs.addThrowable(th)) {
                this.done = true;
                drain();
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
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
                this.s.cancel();
                disposeAll();
                if (getAndIncrement() == 0) {
                    SimpleQueue simpleQueue = this.queue;
                    if (simpleQueue != null) {
                        simpleQueue.clear();
                    }
                }
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void drainLoop() {
            c cVar = this.actual;
            int i = 1;
            while (!checkTerminate()) {
                Object obj;
                long j;
                SimplePlainQueue simplePlainQueue = this.queue;
                long j2 = this.requested.get();
                if (j2 == Clock.MAX_TIME) {
                    obj = 1;
                } else {
                    obj = null;
                }
                long j3 = 0;
                if (simplePlainQueue != null) {
                    Object obj2;
                    do {
                        j = 0;
                        obj2 = null;
                        while (j2 != 0) {
                            obj2 = simplePlainQueue.poll();
                            if (!checkTerminate()) {
                                if (obj2 == null) {
                                    break;
                                }
                                cVar.onNext(obj2);
                                j2--;
                                j = 1 + j;
                                j3 = 1 + j3;
                            } else {
                                return;
                            }
                        }
                        if (j != 0) {
                            if (obj != null) {
                                j2 = Clock.MAX_TIME;
                            } else {
                                j2 = this.requested.addAndGet(-j);
                            }
                        }
                        if (j2 == 0) {
                            break;
                        }
                    } while (obj2 != null);
                }
                boolean z = this.done;
                SimplePlainQueue simplePlainQueue2 = this.queue;
                InnerSubscriber[] innerSubscriberArr = (InnerSubscriber[]) this.subscribers.get();
                int length = innerSubscriberArr.length;
                if (z && ((simplePlainQueue2 == null || simplePlainQueue2.isEmpty()) && length == 0)) {
                    Throwable terminate = this.errs.terminate();
                    if (terminate == ExceptionHelper.TERMINATED) {
                        return;
                    }
                    if (terminate == null) {
                        cVar.onComplete();
                        return;
                    } else {
                        cVar.onError(terminate);
                        return;
                    }
                }
                long j4;
                Object obj3;
                if (length != 0) {
                    long j5 = this.lastId;
                    int i2 = this.lastIndex;
                    if (length <= i2 || innerSubscriberArr[i2].id != j5) {
                        if (length <= i2) {
                            i2 = 0;
                        }
                        for (int i3 = 0; i3 < length && innerSubscriberArr[i2].id != j5; i3++) {
                            i2++;
                            if (i2 == length) {
                                i2 = 0;
                            }
                        }
                        this.lastIndex = i2;
                        this.lastId = innerSubscriberArr[i2].id;
                    }
                    Object obj4 = null;
                    int i4 = i2;
                    i2 = 0;
                    j4 = j3;
                    while (i2 < length) {
                        if (!checkTerminate()) {
                            InnerSubscriber innerSubscriber = innerSubscriberArr[i4];
                            Object obj5 = null;
                            while (!checkTerminate()) {
                                Object obj6;
                                int i5;
                                int i6;
                                SimpleQueue simpleQueue = innerSubscriber.queue;
                                if (simpleQueue == null) {
                                    j3 = j2;
                                } else {
                                    long j6 = 0;
                                    j5 = j2;
                                    obj6 = obj5;
                                    while (j5 != 0) {
                                        try {
                                            obj6 = simpleQueue.poll();
                                            if (obj6 == null) {
                                                break;
                                            }
                                            cVar.onNext(obj6);
                                            if (!checkTerminate()) {
                                                j5--;
                                                j6 = 1 + j6;
                                            } else {
                                                return;
                                            }
                                        } catch (Throwable th) {
                                            Exceptions.throwIfFatal(th);
                                            innerSubscriber.dispose();
                                            this.errs.addThrowable(th);
                                            if (!checkTerminate()) {
                                                removeInner(innerSubscriber);
                                                i5 = i2 + 1;
                                                obj6 = 1;
                                                i6 = i4;
                                                j = j4;
                                                j4 = j5;
                                            } else {
                                                return;
                                            }
                                        }
                                    }
                                    if (j6 != 0) {
                                        if (obj == null) {
                                            j3 = this.requested.addAndGet(-j6);
                                        } else {
                                            j3 = Clock.MAX_TIME;
                                        }
                                        innerSubscriber.requestMore(j6);
                                    } else {
                                        j3 = j5;
                                    }
                                    if (!(j3 == 0 || obj6 == null)) {
                                        Object obj7 = obj6;
                                        j2 = j3;
                                        obj5 = obj7;
                                    }
                                }
                                boolean z2 = innerSubscriber.done;
                                SimpleQueue simpleQueue2 = innerSubscriber.queue;
                                if (z2 && (simpleQueue2 == null || simpleQueue2.isEmpty())) {
                                    removeInner(innerSubscriber);
                                    if (!checkTerminate()) {
                                        j4++;
                                        obj3 = 1;
                                    } else {
                                        return;
                                    }
                                }
                                obj3 = obj4;
                                if (j3 == 0) {
                                    break;
                                }
                                int i7 = i4 + 1;
                                int i8;
                                if (i7 == length) {
                                    i8 = i2;
                                    j = j4;
                                    j4 = j3;
                                    i5 = i8;
                                    i6 = 0;
                                    obj6 = obj3;
                                } else {
                                    i8 = i2;
                                    j = j4;
                                    j4 = j3;
                                    i5 = i8;
                                    i6 = i7;
                                    obj6 = obj3;
                                }
                                obj4 = obj6;
                                j2 = j4;
                                j4 = j;
                                i2 = i5 + 1;
                                i4 = i6;
                            }
                            return;
                        }
                        return;
                    }
                    obj3 = obj4;
                    this.lastIndex = i4;
                    this.lastId = innerSubscriberArr[i4].id;
                } else {
                    obj3 = null;
                    j4 = j3;
                }
                if (!(j4 == 0 || this.cancelled)) {
                    this.s.request(j4);
                }
                if (obj3 == null) {
                    int addAndGet = addAndGet(-i);
                    if (addAndGet != 0) {
                        i = addAndGet;
                    } else {
                        return;
                    }
                }
            }
        }

        boolean checkTerminate() {
            if (this.cancelled) {
                clearScalarQueue();
                return true;
            } else if (this.delayErrors || this.errs.get() == null) {
                return false;
            } else {
                clearScalarQueue();
                Throwable terminate = this.errs.terminate();
                if (terminate == ExceptionHelper.TERMINATED) {
                    return true;
                }
                this.actual.onError(terminate);
                return true;
            }
        }

        void clearScalarQueue() {
            SimpleQueue simpleQueue = this.queue;
            if (simpleQueue != null) {
                simpleQueue.clear();
            }
        }

        void disposeAll() {
            if (((InnerSubscriber[]) this.subscribers.get()) != CANCELLED) {
                InnerSubscriber[] innerSubscriberArr = (InnerSubscriber[]) this.subscribers.getAndSet(CANCELLED);
                if (innerSubscriberArr != CANCELLED) {
                    for (InnerSubscriber dispose : innerSubscriberArr) {
                        dispose.dispose();
                    }
                    Throwable terminate = this.errs.terminate();
                    if (terminate != null && terminate != ExceptionHelper.TERMINATED) {
                        RxJavaPlugins.onError(terminate);
                    }
                }
            }
        }

        void innerError(InnerSubscriber<T, U> innerSubscriber, Throwable th) {
            if (this.errs.addThrowable(th)) {
                innerSubscriber.done = true;
                if (!this.delayErrors) {
                    this.s.cancel();
                    for (InnerSubscriber dispose : (InnerSubscriber[]) this.subscribers.getAndSet(CANCELLED)) {
                        dispose.dispose();
                    }
                }
                drain();
                return;
            }
            RxJavaPlugins.onError(th);
        }
    }

    public FlowableFlatMap(Flowable<T> flowable, Function<? super T, ? extends b<? extends U>> function, boolean z, int i, int i2) {
        super(flowable);
        this.mapper = function;
        this.delayErrors = z;
        this.maxConcurrency = i;
        this.bufferSize = i2;
    }

    protected void subscribeActual(c<? super U> cVar) {
        if (!FlowableScalarXMap.tryScalarXMapSubscribe(this.source, cVar, this.mapper)) {
            this.source.subscribe(subscribe(cVar, this.mapper, this.delayErrors, this.maxConcurrency, this.bufferSize));
        }
    }

    public static <T, U> FlowableSubscriber<T> subscribe(c<? super U> cVar, Function<? super T, ? extends b<? extends U>> function, boolean z, int i, int i2) {
        return new MergeSubscriber(cVar, function, z, i, i2);
    }
}
