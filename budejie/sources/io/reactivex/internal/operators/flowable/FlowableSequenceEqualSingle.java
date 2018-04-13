package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiPredicate;
import io.reactivex.internal.fuseable.FuseToFlowable;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import org.a.b;
import org.a.d;

public final class FlowableSequenceEqualSingle<T> extends Single<Boolean> implements FuseToFlowable<Boolean> {
    final BiPredicate<? super T, ? super T> comparer;
    final b<? extends T> first;
    final int prefetch;
    final b<? extends T> second;

    static final class EqualCoordinator<T> extends AtomicInteger implements Disposable, EqualCoordinatorHelper {
        private static final long serialVersionUID = -6178010334400373240L;
        final SingleObserver<? super Boolean> actual;
        final BiPredicate<? super T, ? super T> comparer;
        final AtomicThrowable error = new AtomicThrowable();
        final EqualSubscriber<T> first;
        final EqualSubscriber<T> second;
        T v1;
        T v2;

        EqualCoordinator(SingleObserver<? super Boolean> singleObserver, int i, BiPredicate<? super T, ? super T> biPredicate) {
            this.actual = singleObserver;
            this.comparer = biPredicate;
            this.first = new EqualSubscriber(this, i);
            this.second = new EqualSubscriber(this, i);
        }

        void subscribe(b<? extends T> bVar, b<? extends T> bVar2) {
            bVar.subscribe(this.first);
            bVar2.subscribe(this.second);
        }

        public void dispose() {
            this.first.cancel();
            this.second.cancel();
            if (getAndIncrement() == 0) {
                this.first.clear();
                this.second.clear();
            }
        }

        public boolean isDisposed() {
            return SubscriptionHelper.isCancelled((d) this.first.get());
        }

        void cancelAndClear() {
            this.first.cancel();
            this.first.clear();
            this.second.cancel();
            this.second.clear();
        }

        public void drain() {
            if (getAndIncrement() == 0) {
                int i = 1;
                while (true) {
                    SimpleQueue simpleQueue = this.first.queue;
                    SimpleQueue simpleQueue2 = this.second.queue;
                    if (simpleQueue != null && simpleQueue2 != null) {
                        while (!isDisposed()) {
                            if (((Throwable) this.error.get()) != null) {
                                cancelAndClear();
                                this.actual.onError(this.error.terminate());
                                return;
                            }
                            boolean z;
                            boolean z2 = this.first.done;
                            Object obj = this.v1;
                            if (obj == null) {
                                try {
                                    obj = simpleQueue.poll();
                                    this.v1 = obj;
                                } catch (Throwable th) {
                                    Exceptions.throwIfFatal(th);
                                    cancelAndClear();
                                    this.error.addThrowable(th);
                                    this.actual.onError(this.error.terminate());
                                    return;
                                }
                            }
                            Object obj2 = obj;
                            if (obj2 == null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            boolean z3 = this.second.done;
                            Object obj3 = this.v2;
                            if (obj3 == null) {
                                try {
                                    obj3 = simpleQueue2.poll();
                                    this.v2 = obj3;
                                } catch (Throwable th2) {
                                    Exceptions.throwIfFatal(th2);
                                    cancelAndClear();
                                    this.error.addThrowable(th2);
                                    this.actual.onError(this.error.terminate());
                                    return;
                                }
                            }
                            Object obj4 = obj3;
                            boolean z4;
                            if (obj4 == null) {
                                z4 = true;
                            } else {
                                z4 = false;
                            }
                            if (z2 && z3 && z && r4) {
                                this.actual.onSuccess(Boolean.valueOf(true));
                                return;
                            } else if (z2 && z3 && z != r4) {
                                cancelAndClear();
                                this.actual.onSuccess(Boolean.valueOf(false));
                                return;
                            } else if (!(z || r4)) {
                                try {
                                    if (this.comparer.test(obj2, obj4)) {
                                        this.v1 = null;
                                        this.v2 = null;
                                        this.first.request();
                                        this.second.request();
                                    } else {
                                        cancelAndClear();
                                        this.actual.onSuccess(Boolean.valueOf(false));
                                        return;
                                    }
                                } catch (Throwable th22) {
                                    Exceptions.throwIfFatal(th22);
                                    cancelAndClear();
                                    this.error.addThrowable(th22);
                                    this.actual.onError(this.error.terminate());
                                    return;
                                }
                            }
                        }
                        this.first.clear();
                        this.second.clear();
                        return;
                    } else if (isDisposed()) {
                        this.first.clear();
                        this.second.clear();
                        return;
                    } else if (((Throwable) this.error.get()) != null) {
                        cancelAndClear();
                        this.actual.onError(this.error.terminate());
                        return;
                    }
                    int addAndGet = addAndGet(-i);
                    if (addAndGet != 0) {
                        i = addAndGet;
                    } else {
                        return;
                    }
                }
            }
        }

        public void innerError(Throwable th) {
            if (this.error.addThrowable(th)) {
                drain();
            } else {
                RxJavaPlugins.onError(th);
            }
        }
    }

    public FlowableSequenceEqualSingle(b<? extends T> bVar, b<? extends T> bVar2, BiPredicate<? super T, ? super T> biPredicate, int i) {
        this.first = bVar;
        this.second = bVar2;
        this.comparer = biPredicate;
        this.prefetch = i;
    }

    public void subscribeActual(SingleObserver<? super Boolean> singleObserver) {
        Object equalCoordinator = new EqualCoordinator(singleObserver, this.prefetch, this.comparer);
        singleObserver.onSubscribe(equalCoordinator);
        equalCoordinator.subscribe(this.first, this.second);
    }

    public Flowable<Boolean> fuseToFlowable() {
        return RxJavaPlugins.onAssembly(new FlowableSequenceEqual(this.first, this.second, this.comparer, this.prefetch));
    }
}
