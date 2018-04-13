package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableCombineLatest<T, R> extends Observable<R> {
    final int bufferSize;
    final Function<? super Object[], ? extends R> combiner;
    final boolean delayError;
    final ObservableSource<? extends T>[] sources;
    final Iterable<? extends ObservableSource<? extends T>> sourcesIterable;

    static final class CombinerObserver<T, R> implements Observer<T> {
        final int index;
        final LatestCoordinator<T, R> parent;
        final AtomicReference<Disposable> s = new AtomicReference();

        CombinerObserver(LatestCoordinator<T, R> latestCoordinator, int i) {
            this.parent = latestCoordinator;
            this.index = i;
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this.s, disposable);
        }

        public void onNext(T t) {
            this.parent.combine(t, this.index);
        }

        public void onError(Throwable th) {
            this.parent.onError(th);
            this.parent.combine(null, this.index);
        }

        public void onComplete() {
            this.parent.combine(null, this.index);
        }

        public void dispose() {
            DisposableHelper.dispose(this.s);
        }
    }

    static final class LatestCoordinator<T, R> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 8567835998786448817L;
        int active;
        final Observer<? super R> actual;
        volatile boolean cancelled;
        final Function<? super Object[], ? extends R> combiner;
        int complete;
        final boolean delayError;
        volatile boolean done;
        final AtomicThrowable errors = new AtomicThrowable();
        final T[] latest;
        final CombinerObserver<T, R>[] observers;
        final SpscLinkedArrayQueue<Object> queue;

        LatestCoordinator(Observer<? super R> observer, Function<? super Object[], ? extends R> function, int i, int i2, boolean z) {
            this.actual = observer;
            this.combiner = function;
            this.delayError = z;
            this.latest = new Object[i];
            this.observers = new CombinerObserver[i];
            this.queue = new SpscLinkedArrayQueue(i2);
        }

        public void subscribe(ObservableSource<? extends T>[] observableSourceArr) {
            int i = 0;
            CombinerObserver[] combinerObserverArr = this.observers;
            int length = combinerObserverArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                combinerObserverArr[i2] = new CombinerObserver(this, i2);
            }
            lazySet(0);
            this.actual.onSubscribe(this);
            while (i < length && !this.done && !this.cancelled) {
                observableSourceArr[i].subscribe(combinerObserverArr[i]);
                i++;
            }
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelSources();
                if (getAndIncrement() == 0) {
                    clear(this.queue);
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        void cancel(SpscLinkedArrayQueue<?> spscLinkedArrayQueue) {
            clear(spscLinkedArrayQueue);
            cancelSources();
        }

        void cancelSources() {
            for (CombinerObserver dispose : this.observers) {
                dispose.dispose();
            }
        }

        void clear(SpscLinkedArrayQueue<?> spscLinkedArrayQueue) {
            synchronized (this) {
                Arrays.fill(this.latest, null);
            }
            spscLinkedArrayQueue.clear();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void combine(T r9, int r10) {
            /*
            r8 = this;
            r0 = 0;
            r1 = 1;
            r2 = r8.observers;
            r5 = r2[r10];
            monitor-enter(r8);
            r2 = r8.cancelled;	 Catch:{ all -> 0x0060 }
            if (r2 == 0) goto L_0x000d;
        L_0x000b:
            monitor-exit(r8);	 Catch:{ all -> 0x0060 }
        L_0x000c:
            return;
        L_0x000d:
            r2 = r8.latest;	 Catch:{ all -> 0x0060 }
            r6 = r2.length;	 Catch:{ all -> 0x0060 }
            r2 = r8.latest;	 Catch:{ all -> 0x0060 }
            r7 = r2[r10];	 Catch:{ all -> 0x0060 }
            r2 = r8.active;	 Catch:{ all -> 0x0060 }
            if (r7 != 0) goto L_0x001c;
        L_0x0018:
            r2 = r2 + 1;
            r8.active = r2;	 Catch:{ all -> 0x0060 }
        L_0x001c:
            r4 = r2;
            r2 = r8.complete;	 Catch:{ all -> 0x0060 }
            if (r9 != 0) goto L_0x004a;
        L_0x0021:
            r2 = r2 + 1;
            r8.complete = r2;	 Catch:{ all -> 0x0060 }
            r3 = r2;
        L_0x0026:
            if (r4 != r6) goto L_0x0050;
        L_0x0028:
            r2 = r1;
        L_0x0029:
            if (r3 == r6) goto L_0x002f;
        L_0x002b:
            if (r9 != 0) goto L_0x0030;
        L_0x002d:
            if (r7 != 0) goto L_0x0030;
        L_0x002f:
            r0 = r1;
        L_0x0030:
            if (r0 != 0) goto L_0x0063;
        L_0x0032:
            if (r9 == 0) goto L_0x0052;
        L_0x0034:
            if (r2 == 0) goto L_0x0052;
        L_0x0036:
            r0 = r8.queue;	 Catch:{ all -> 0x0060 }
            r1 = r8.latest;	 Catch:{ all -> 0x0060 }
            r1 = r1.clone();	 Catch:{ all -> 0x0060 }
            r0.offer(r5, r1);	 Catch:{ all -> 0x0060 }
        L_0x0041:
            monitor-exit(r8);	 Catch:{ all -> 0x0060 }
            if (r2 != 0) goto L_0x0046;
        L_0x0044:
            if (r9 != 0) goto L_0x000c;
        L_0x0046:
            r8.drain();
            goto L_0x000c;
        L_0x004a:
            r3 = r8.latest;	 Catch:{ all -> 0x0060 }
            r3[r10] = r9;	 Catch:{ all -> 0x0060 }
            r3 = r2;
            goto L_0x0026;
        L_0x0050:
            r2 = r0;
            goto L_0x0029;
        L_0x0052:
            if (r9 != 0) goto L_0x0041;
        L_0x0054:
            r0 = r8.errors;	 Catch:{ all -> 0x0060 }
            r0 = r0.get();	 Catch:{ all -> 0x0060 }
            if (r0 == 0) goto L_0x0041;
        L_0x005c:
            r0 = 1;
            r8.done = r0;	 Catch:{ all -> 0x0060 }
            goto L_0x0041;
        L_0x0060:
            r0 = move-exception;
            monitor-exit(r8);	 Catch:{ all -> 0x0060 }
            throw r0;
        L_0x0063:
            r0 = 1;
            r8.done = r0;	 Catch:{ all -> 0x0060 }
            goto L_0x0041;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableCombineLatest.LatestCoordinator.combine(java.lang.Object, int):void");
        }

        void drain() {
            if (getAndIncrement() == 0) {
                SpscLinkedArrayQueue spscLinkedArrayQueue = this.queue;
                Observer observer = this.actual;
                boolean z = this.delayError;
                int i = 1;
                while (!checkTerminated(this.done, spscLinkedArrayQueue.isEmpty(), observer, spscLinkedArrayQueue, z)) {
                    while (true) {
                        boolean z2 = this.done;
                        boolean z3 = ((CombinerObserver) spscLinkedArrayQueue.poll()) == null;
                        if (!checkTerminated(z2, z3, observer, spscLinkedArrayQueue, z)) {
                            if (z3) {
                                break;
                            }
                            try {
                                observer.onNext(ObjectHelper.requireNonNull(this.combiner.apply((Object[]) spscLinkedArrayQueue.poll()), "The combiner returned a null"));
                            } catch (Throwable th) {
                                Exceptions.throwIfFatal(th);
                                this.cancelled = true;
                                cancel(spscLinkedArrayQueue);
                                observer.onError(th);
                                return;
                            }
                        }
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

        boolean checkTerminated(boolean z, boolean z2, Observer<?> observer, SpscLinkedArrayQueue<?> spscLinkedArrayQueue, boolean z3) {
            if (this.cancelled) {
                cancel(spscLinkedArrayQueue);
                return true;
            }
            if (z) {
                if (z3) {
                    if (z2) {
                        cancel(spscLinkedArrayQueue);
                        Throwable terminate = this.errors.terminate();
                        if (terminate != null) {
                            observer.onError(terminate);
                        } else {
                            observer.onComplete();
                        }
                        return true;
                    }
                } else if (((Throwable) this.errors.get()) != null) {
                    cancel(spscLinkedArrayQueue);
                    observer.onError(this.errors.terminate());
                    return true;
                } else if (z2) {
                    clear(this.queue);
                    observer.onComplete();
                    return true;
                }
            }
            return false;
        }

        void onError(Throwable th) {
            if (!this.errors.addThrowable(th)) {
                RxJavaPlugins.onError(th);
            }
        }
    }

    public ObservableCombineLatest(ObservableSource<? extends T>[] observableSourceArr, Iterable<? extends ObservableSource<? extends T>> iterable, Function<? super Object[], ? extends R> function, int i, boolean z) {
        this.sources = observableSourceArr;
        this.sourcesIterable = iterable;
        this.combiner = function;
        this.bufferSize = i;
        this.delayError = z;
    }

    public void subscribeActual(Observer<? super R> observer) {
        int i;
        ObservableSource[] observableSourceArr;
        ObservableSource[] observableSourceArr2 = this.sources;
        if (observableSourceArr2 == null) {
            Object obj = new Observable[8];
            i = 0;
            for (ObservableSource observableSource : this.sourcesIterable) {
                Object obj2;
                if (i == obj.length) {
                    obj2 = new ObservableSource[((i >> 2) + i)];
                    System.arraycopy(obj, 0, obj2, 0, i);
                } else {
                    obj2 = obj;
                }
                int i2 = i + 1;
                obj2[i] = observableSource;
                i = i2;
                obj = obj2;
            }
            observableSourceArr = obj;
        } else {
            i = observableSourceArr2.length;
            observableSourceArr = observableSourceArr2;
        }
        if (i == 0) {
            EmptyDisposable.complete(observer);
            return;
        }
        new LatestCoordinator(observer, this.combiner, i, this.bufferSize, this.delayError).subscribe(observableSourceArr);
    }
}
