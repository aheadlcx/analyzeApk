package io.reactivex.internal.operators.parallel;

import com.facebook.common.time.Clock;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class ParallelSortedJoin<T> extends Flowable<T> {
    final Comparator<? super T> comparator;
    final ParallelFlowable<List<T>> source;

    static final class SortedJoinInnerSubscriber<T> extends AtomicReference<d> implements FlowableSubscriber<List<T>> {
        private static final long serialVersionUID = 6751017204873808094L;
        final int index;
        final SortedJoinSubscription<T> parent;

        SortedJoinInnerSubscriber(SortedJoinSubscription<T> sortedJoinSubscription, int i) {
            this.parent = sortedJoinSubscription;
            this.index = i;
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.setOnce(this, dVar)) {
                dVar.request(Clock.MAX_TIME);
            }
        }

        public void onNext(List<T> list) {
            this.parent.innerNext(list, this.index);
        }

        public void onError(Throwable th) {
            this.parent.innerError(th);
        }

        public void onComplete() {
        }

        void cancel() {
            SubscriptionHelper.cancel(this);
        }
    }

    static final class SortedJoinSubscription<T> extends AtomicInteger implements d {
        private static final long serialVersionUID = 3481980673745556697L;
        final c<? super T> actual;
        volatile boolean cancelled;
        final Comparator<? super T> comparator;
        final AtomicReference<Throwable> error = new AtomicReference();
        final int[] indexes;
        final List<T>[] lists;
        final AtomicInteger remaining = new AtomicInteger();
        final AtomicLong requested = new AtomicLong();
        final SortedJoinInnerSubscriber<T>[] subscribers;

        SortedJoinSubscription(c<? super T> cVar, int i, Comparator<? super T> comparator) {
            this.actual = cVar;
            this.comparator = comparator;
            SortedJoinInnerSubscriber[] sortedJoinInnerSubscriberArr = new SortedJoinInnerSubscriber[i];
            for (int i2 = 0; i2 < i; i2++) {
                sortedJoinInnerSubscriberArr[i2] = new SortedJoinInnerSubscriber(this, i2);
            }
            this.subscribers = sortedJoinInnerSubscriberArr;
            this.lists = new List[i];
            this.indexes = new int[i];
            this.remaining.lazySet(i);
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                if (this.remaining.get() == 0) {
                    drain();
                }
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    Arrays.fill(this.lists, null);
                }
            }
        }

        void cancelAll() {
            for (SortedJoinInnerSubscriber cancel : this.subscribers) {
                cancel.cancel();
            }
        }

        void innerNext(List<T> list, int i) {
            this.lists[i] = list;
            if (this.remaining.decrementAndGet() == 0) {
                drain();
            }
        }

        void innerError(Throwable th) {
            if (this.error.compareAndSet(null, th)) {
                drain();
            } else if (th != this.error.get()) {
                RxJavaPlugins.onError(th);
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                c cVar = this.actual;
                List[] listArr = this.lists;
                int[] iArr = this.indexes;
                int length = iArr.length;
                int i = 1;
                while (true) {
                    Throwable th;
                    int i2;
                    int i3;
                    long j = this.requested.get();
                    long j2 = 0;
                    while (j2 != j) {
                        if (this.cancelled) {
                            Arrays.fill(listArr, null);
                            return;
                        }
                        th = (Throwable) this.error.get();
                        if (th != null) {
                            cancelAll();
                            Arrays.fill(listArr, null);
                            cVar.onError(th);
                            return;
                        }
                        Object obj = null;
                        i2 = -1;
                        i3 = 0;
                        while (i3 < length) {
                            Object obj2;
                            List list = listArr[i3];
                            int i4 = iArr[i3];
                            if (list.size() != i4) {
                                if (obj == null) {
                                    obj2 = list.get(i4);
                                    i2 = i3;
                                } else {
                                    obj2 = list.get(i4);
                                    try {
                                        Object obj3;
                                        if (this.comparator.compare(obj, obj2) > 0) {
                                            obj3 = 1;
                                        } else {
                                            obj3 = null;
                                        }
                                        if (obj3 != null) {
                                            i2 = i3;
                                        }
                                    } catch (Throwable th2) {
                                        Exceptions.throwIfFatal(th2);
                                        cancelAll();
                                        Arrays.fill(listArr, null);
                                        if (!this.error.compareAndSet(null, th2)) {
                                            RxJavaPlugins.onError(th2);
                                        }
                                        cVar.onError((Throwable) this.error.get());
                                        return;
                                    }
                                }
                                i3++;
                                obj = obj2;
                            }
                            obj2 = obj;
                            i3++;
                            obj = obj2;
                        }
                        if (obj == null) {
                            Arrays.fill(listArr, null);
                            cVar.onComplete();
                            return;
                        }
                        cVar.onNext(obj);
                        iArr[i2] = iArr[i2] + 1;
                        j2 = 1 + j2;
                    }
                    if (j2 == j) {
                        if (this.cancelled) {
                            Arrays.fill(listArr, null);
                            return;
                        }
                        th2 = (Throwable) this.error.get();
                        if (th2 != null) {
                            cancelAll();
                            Arrays.fill(listArr, null);
                            cVar.onError(th2);
                            return;
                        }
                        Object obj4 = 1;
                        for (i3 = 0; i3 < length; i3++) {
                            if (iArr[i3] != listArr[i3].size()) {
                                obj4 = null;
                                break;
                            }
                        }
                        if (obj4 != null) {
                            Arrays.fill(listArr, null);
                            cVar.onComplete();
                            return;
                        }
                    }
                    if (!(j2 == 0 || j == Clock.MAX_TIME)) {
                        this.requested.addAndGet(-j2);
                    }
                    i2 = get();
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
    }

    public ParallelSortedJoin(ParallelFlowable<List<T>> parallelFlowable, Comparator<? super T> comparator) {
        this.source = parallelFlowable;
        this.comparator = comparator;
    }

    protected void subscribeActual(c<? super T> cVar) {
        Object sortedJoinSubscription = new SortedJoinSubscription(cVar, this.source.parallelism(), this.comparator);
        cVar.onSubscribe(sortedJoinSubscription);
        this.source.subscribe(sortedJoinSubscription.subscribers);
    }
}
