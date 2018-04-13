package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.LinkedArrayList;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableCache<T> extends AbstractObservableWithUpstream<T, T> {
    final AtomicBoolean once = new AtomicBoolean();
    final CacheState<T> state;

    static final class CacheState<T> extends LinkedArrayList implements Observer<T> {
        static final ReplayDisposable[] EMPTY = new ReplayDisposable[0];
        static final ReplayDisposable[] TERMINATED = new ReplayDisposable[0];
        final SequentialDisposable connection = new SequentialDisposable();
        volatile boolean isConnected;
        final AtomicReference<ReplayDisposable<T>[]> observers = new AtomicReference(EMPTY);
        final Observable<? extends T> source;
        boolean sourceDone;

        CacheState(Observable<? extends T> observable, int i) {
            super(i);
            this.source = observable;
        }

        public boolean addChild(ReplayDisposable<T> replayDisposable) {
            ReplayDisposable[] replayDisposableArr;
            Object obj;
            do {
                replayDisposableArr = (ReplayDisposable[]) this.observers.get();
                if (replayDisposableArr == TERMINATED) {
                    return false;
                }
                int length = replayDisposableArr.length;
                obj = new ReplayDisposable[(length + 1)];
                System.arraycopy(replayDisposableArr, 0, obj, 0, length);
                obj[length] = replayDisposable;
            } while (!this.observers.compareAndSet(replayDisposableArr, obj));
            return true;
        }

        public void removeChild(ReplayDisposable<T> replayDisposable) {
            ReplayDisposable[] replayDisposableArr;
            Object obj;
            do {
                replayDisposableArr = (ReplayDisposable[]) this.observers.get();
                int length = replayDisposableArr.length;
                if (length != 0) {
                    int i = -1;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (replayDisposableArr[i2].equals(replayDisposable)) {
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
                        obj = new ReplayDisposable[(length - 1)];
                        System.arraycopy(replayDisposableArr, 0, obj, 0, i);
                        System.arraycopy(replayDisposableArr, i + 1, obj, i, (length - i) - 1);
                    }
                } else {
                    return;
                }
            } while (!this.observers.compareAndSet(replayDisposableArr, obj));
        }

        public void onSubscribe(Disposable disposable) {
            this.connection.update(disposable);
        }

        public void connect() {
            this.source.subscribe(this);
            this.isConnected = true;
        }

        public void onNext(T t) {
            if (!this.sourceDone) {
                add(NotificationLite.next(t));
                for (ReplayDisposable replay : (ReplayDisposable[]) this.observers.get()) {
                    replay.replay();
                }
            }
        }

        public void onError(Throwable th) {
            if (!this.sourceDone) {
                this.sourceDone = true;
                add(NotificationLite.error(th));
                this.connection.dispose();
                for (ReplayDisposable replay : (ReplayDisposable[]) this.observers.getAndSet(TERMINATED)) {
                    replay.replay();
                }
            }
        }

        public void onComplete() {
            if (!this.sourceDone) {
                this.sourceDone = true;
                add(NotificationLite.complete());
                this.connection.dispose();
                for (ReplayDisposable replay : (ReplayDisposable[]) this.observers.getAndSet(TERMINATED)) {
                    replay.replay();
                }
            }
        }
    }

    static final class ReplayDisposable<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 7058506693698832024L;
        volatile boolean cancelled;
        final Observer<? super T> child;
        Object[] currentBuffer;
        int currentIndexInBuffer;
        int index;
        final CacheState<T> state;

        ReplayDisposable(Observer<? super T> observer, CacheState<T> cacheState) {
            this.child = observer;
            this.state = cacheState;
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.state.removeChild(this);
            }
        }

        public void replay() {
            if (getAndIncrement() == 0) {
                Observer observer = this.child;
                int i = 1;
                while (!this.cancelled) {
                    int i2;
                    int size = this.state.size();
                    if (size != 0) {
                        Object[] objArr = this.currentBuffer;
                        if (objArr == null) {
                            objArr = this.state.head();
                            this.currentBuffer = objArr;
                        }
                        int length = objArr.length - 1;
                        int i3 = this.index;
                        Object[] objArr2 = objArr;
                        i2 = this.currentIndexInBuffer;
                        while (i3 < size) {
                            if (!this.cancelled) {
                                if (i2 == length) {
                                    objArr2 = (Object[]) objArr2[length];
                                    i2 = 0;
                                }
                                if (!NotificationLite.accept(objArr2[i2], observer)) {
                                    i2++;
                                    i3++;
                                } else {
                                    return;
                                }
                            }
                            return;
                        }
                        if (!this.cancelled) {
                            this.index = i3;
                            this.currentIndexInBuffer = i2;
                            this.currentBuffer = objArr2;
                        } else {
                            return;
                        }
                    }
                    i2 = addAndGet(-i);
                    if (i2 != 0) {
                        i = i2;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public static <T> Observable<T> from(Observable<T> observable) {
        return from(observable, 16);
    }

    public static <T> Observable<T> from(Observable<T> observable, int i) {
        ObjectHelper.verifyPositive(i, "capacityHint");
        return RxJavaPlugins.onAssembly(new ObservableCache(observable, new CacheState(observable, i)));
    }

    private ObservableCache(Observable<T> observable, CacheState<T> cacheState) {
        super(observable);
        this.state = cacheState;
    }

    protected void subscribeActual(Observer<? super T> observer) {
        ReplayDisposable replayDisposable = new ReplayDisposable(observer, this.state);
        observer.onSubscribe(replayDisposable);
        this.state.addChild(replayDisposable);
        if (!this.once.get() && this.once.compareAndSet(false, true)) {
            this.state.connect();
        }
        replayDisposable.replay();
    }

    boolean isConnected() {
        return this.state.isConnected;
    }

    boolean hasObservers() {
        return ((ReplayDisposable[]) this.state.observers.get()).length != 0;
    }

    int cachedEventCount() {
        return this.state.size();
    }
}
