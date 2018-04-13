package io.reactivex.internal.operators.flowable;

import com.facebook.common.time.Clock;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableCombineLatest<T, R> extends Flowable<R> {
    @Nullable
    final b<? extends T>[] array;
    final int bufferSize;
    final Function<? super Object[], ? extends R> combiner;
    final boolean delayErrors;
    @Nullable
    final Iterable<? extends b<? extends T>> iterable;

    static final class CombineLatestCoordinator<T, R> extends BasicIntQueueSubscription<R> {
        private static final long serialVersionUID = -5082275438355852221L;
        final c<? super R> actual;
        volatile boolean cancelled;
        final Function<? super Object[], ? extends R> combiner;
        int completedSources;
        final boolean delayErrors;
        volatile boolean done;
        final AtomicReference<Throwable> error;
        final Object[] latest;
        int nonEmptySources;
        boolean outputFused;
        final SpscLinkedArrayQueue<Object> queue;
        final AtomicLong requested;
        final CombineLatestInnerSubscriber<T>[] subscribers;

        CombineLatestCoordinator(c<? super R> cVar, Function<? super Object[], ? extends R> function, int i, int i2, boolean z) {
            this.actual = cVar;
            this.combiner = function;
            CombineLatestInnerSubscriber[] combineLatestInnerSubscriberArr = new CombineLatestInnerSubscriber[i];
            for (int i3 = 0; i3 < i; i3++) {
                combineLatestInnerSubscriberArr[i3] = new CombineLatestInnerSubscriber(this, i3, i2);
            }
            this.subscribers = combineLatestInnerSubscriberArr;
            this.latest = new Object[i];
            this.queue = new SpscLinkedArrayQueue(i2);
            this.requested = new AtomicLong();
            this.error = new AtomicReference();
            this.delayErrors = z;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            this.cancelled = true;
            cancelAll();
        }

        void subscribe(b<? extends T>[] bVarArr, int i) {
            CombineLatestInnerSubscriber[] combineLatestInnerSubscriberArr = this.subscribers;
            for (int i2 = 0; i2 < i && !this.done && !this.cancelled; i2++) {
                bVarArr[i2].subscribe(combineLatestInnerSubscriberArr[i2]);
            }
        }

        void innerValue(int i, T t) {
            Object obj;
            synchronized (this) {
                Object obj2 = this.latest;
                int i2 = this.nonEmptySources;
                if (obj2[i] == null) {
                    i2++;
                    this.nonEmptySources = i2;
                }
                obj2[i] = t;
                if (obj2.length == i2) {
                    this.queue.offer(this.subscribers[i], obj2.clone());
                    obj = null;
                } else {
                    obj = 1;
                }
            }
            if (obj != null) {
                this.subscribers[i].requestOne();
            } else {
                drain();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void innerComplete(int r3) {
            /*
            r2 = this;
            monitor-enter(r2);
            r0 = r2.latest;	 Catch:{ all -> 0x001a }
            r1 = r0[r3];	 Catch:{ all -> 0x001a }
            if (r1 == 0) goto L_0x001d;
        L_0x0007:
            r1 = r2.completedSources;	 Catch:{ all -> 0x001a }
            r1 = r1 + 1;
            r0 = r0.length;	 Catch:{ all -> 0x001a }
            if (r1 != r0) goto L_0x0016;
        L_0x000e:
            r0 = 1;
            r2.done = r0;	 Catch:{ all -> 0x001a }
        L_0x0011:
            monitor-exit(r2);	 Catch:{ all -> 0x001a }
            r2.drain();
        L_0x0015:
            return;
        L_0x0016:
            r2.completedSources = r1;	 Catch:{ all -> 0x001a }
            monitor-exit(r2);	 Catch:{ all -> 0x001a }
            goto L_0x0015;
        L_0x001a:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x001a }
            throw r0;
        L_0x001d:
            r0 = 1;
            r2.done = r0;	 Catch:{ all -> 0x001a }
            goto L_0x0011;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableCombineLatest.CombineLatestCoordinator.innerComplete(int):void");
        }

        void innerError(int i, Throwable th) {
            if (!ExceptionHelper.addThrowable(this.error, th)) {
                RxJavaPlugins.onError(th);
            } else if (this.delayErrors) {
                innerComplete(i);
            } else {
                cancelAll();
                this.done = true;
                drain();
            }
        }

        void drainOutput() {
            c cVar = this.actual;
            SpscLinkedArrayQueue spscLinkedArrayQueue = this.queue;
            int i = 1;
            while (!this.cancelled) {
                Throwable th = (Throwable) this.error.get();
                if (th != null) {
                    spscLinkedArrayQueue.clear();
                    cVar.onError(th);
                    return;
                }
                boolean z = this.done;
                boolean isEmpty = spscLinkedArrayQueue.isEmpty();
                if (!isEmpty) {
                    cVar.onNext(null);
                }
                if (z && isEmpty) {
                    cVar.onComplete();
                    return;
                }
                int addAndGet = addAndGet(-i);
                if (addAndGet != 0) {
                    i = addAndGet;
                } else {
                    return;
                }
            }
            spscLinkedArrayQueue.clear();
        }

        void drainAsync() {
            c cVar = this.actual;
            SpscLinkedArrayQueue spscLinkedArrayQueue = this.queue;
            int i = 1;
            while (true) {
                long j = this.requested.get();
                long j2 = 0;
                while (j2 != j) {
                    boolean z = this.done;
                    Object poll = spscLinkedArrayQueue.poll();
                    boolean z2 = poll == null;
                    if (!checkTerminated(z, z2, cVar, spscLinkedArrayQueue)) {
                        if (z2) {
                            break;
                        }
                        try {
                            cVar.onNext(ObjectHelper.requireNonNull(this.combiner.apply((Object[]) spscLinkedArrayQueue.poll()), "The combiner returned a null value"));
                            ((CombineLatestInnerSubscriber) poll).requestOne();
                            j2 = 1 + j2;
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            cancelAll();
                            ExceptionHelper.addThrowable(this.error, th);
                            cVar.onError(ExceptionHelper.terminate(this.error));
                            return;
                        }
                    }
                    return;
                }
                if (j2 != j || !checkTerminated(this.done, spscLinkedArrayQueue.isEmpty(), cVar, spscLinkedArrayQueue)) {
                    if (!(j2 == 0 || j == Clock.MAX_TIME)) {
                        this.requested.addAndGet(-j2);
                    }
                    int addAndGet = addAndGet(-i);
                    if (addAndGet != 0) {
                        i = addAndGet;
                    } else {
                        return;
                    }
                }
                return;
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                if (this.outputFused) {
                    drainOutput();
                } else {
                    drainAsync();
                }
            }
        }

        boolean checkTerminated(boolean z, boolean z2, c<?> cVar, SpscLinkedArrayQueue<?> spscLinkedArrayQueue) {
            if (this.cancelled) {
                cancelAll();
                spscLinkedArrayQueue.clear();
                return true;
            }
            if (z) {
                Throwable terminate;
                if (!this.delayErrors) {
                    terminate = ExceptionHelper.terminate(this.error);
                    if (terminate != null && terminate != ExceptionHelper.TERMINATED) {
                        cancelAll();
                        spscLinkedArrayQueue.clear();
                        cVar.onError(terminate);
                        return true;
                    } else if (z2) {
                        cancelAll();
                        cVar.onComplete();
                        return true;
                    }
                } else if (z2) {
                    cancelAll();
                    terminate = ExceptionHelper.terminate(this.error);
                    if (terminate == null || terminate == ExceptionHelper.TERMINATED) {
                        cVar.onComplete();
                        return true;
                    }
                    cVar.onError(terminate);
                    return true;
                }
            }
            return false;
        }

        void cancelAll() {
            for (CombineLatestInnerSubscriber cancel : this.subscribers) {
                cancel.cancel();
            }
        }

        public int requestFusion(int i) {
            boolean z = false;
            if ((i & 4) != 0) {
                return 0;
            }
            int i2 = i & 2;
            if (i2 != 0) {
                z = true;
            }
            this.outputFused = z;
            return i2;
        }

        @Nullable
        public R poll() throws Exception {
            Object poll = this.queue.poll();
            if (poll == null) {
                return null;
            }
            R apply = this.combiner.apply((Object[]) this.queue.poll());
            ((CombineLatestInnerSubscriber) poll).requestOne();
            return apply;
        }

        public void clear() {
            this.queue.clear();
        }

        public boolean isEmpty() {
            return this.queue.isEmpty();
        }
    }

    static final class CombineLatestInnerSubscriber<T> extends AtomicReference<d> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -8730235182291002949L;
        final int index;
        final int limit;
        final CombineLatestCoordinator<T, ?> parent;
        final int prefetch;
        int produced;

        CombineLatestInnerSubscriber(CombineLatestCoordinator<T, ?> combineLatestCoordinator, int i, int i2) {
            this.parent = combineLatestCoordinator;
            this.index = i;
            this.prefetch = i2;
            this.limit = i2 - (i2 >> 2);
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.setOnce(this, dVar)) {
                dVar.request((long) this.prefetch);
            }
        }

        public void onNext(T t) {
            this.parent.innerValue(this.index, t);
        }

        public void onError(Throwable th) {
            this.parent.innerError(this.index, th);
        }

        public void onComplete() {
            this.parent.innerComplete(this.index);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        public void requestOne() {
            int i = this.produced + 1;
            if (i == this.limit) {
                this.produced = 0;
                ((d) get()).request((long) i);
                return;
            }
            this.produced = i;
        }
    }

    final class SingletonArrayFunc implements Function<T, R> {
        SingletonArrayFunc() {
        }

        public R apply(T t) throws Exception {
            return FlowableCombineLatest.this.combiner.apply(new Object[]{t});
        }
    }

    public FlowableCombineLatest(@NonNull b<? extends T>[] bVarArr, @NonNull Function<? super Object[], ? extends R> function, int i, boolean z) {
        this.array = bVarArr;
        this.iterable = null;
        this.combiner = function;
        this.bufferSize = i;
        this.delayErrors = z;
    }

    public FlowableCombineLatest(@NonNull Iterable<? extends b<? extends T>> iterable, @NonNull Function<? super Object[], ? extends R> function, int i, boolean z) {
        this.array = null;
        this.iterable = iterable;
        this.combiner = function;
        this.bufferSize = i;
        this.delayErrors = z;
    }

    public void subscribeActual(c<? super R> cVar) {
        int i;
        b[] bVarArr = this.array;
        if (bVarArr == null) {
            Object obj = new b[8];
            try {
                Iterator it = (Iterator) ObjectHelper.requireNonNull(this.iterable.iterator(), "The iterator returned is null");
                int i2 = 0;
                while (it.hasNext()) {
                    try {
                        try {
                            Object obj2;
                            b bVar = (b) ObjectHelper.requireNonNull(it.next(), "The publisher returned by the iterator is null");
                            if (i2 == obj.length) {
                                obj2 = new b[((i2 >> 2) + i2)];
                                System.arraycopy(obj, 0, obj2, 0, i2);
                            } else {
                                obj2 = obj;
                            }
                            i = i2 + 1;
                            obj2[i2] = bVar;
                            i2 = i;
                            obj = obj2;
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            EmptySubscription.error(th, cVar);
                            return;
                        }
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        EmptySubscription.error(th2, cVar);
                        return;
                    }
                }
                b[] bVarArr2 = obj;
                i = i2;
            } catch (Throwable th22) {
                Exceptions.throwIfFatal(th22);
                EmptySubscription.error(th22, cVar);
                return;
            }
        }
        i = bVarArr.length;
        bVarArr2 = bVarArr;
        if (i == 0) {
            EmptySubscription.complete(cVar);
        } else if (i == 1) {
            bVarArr2[0].subscribe(new MapSubscriber(cVar, new SingletonArrayFunc()));
        } else {
            Object combineLatestCoordinator = new CombineLatestCoordinator(cVar, this.combiner, i, this.bufferSize, this.delayErrors);
            cVar.onSubscribe(combineLatestCoordinator);
            combineLatestCoordinator.subscribe(bVarArr2, i);
        }
    }
}
