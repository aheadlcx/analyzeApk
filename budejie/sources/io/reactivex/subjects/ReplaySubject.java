package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class ReplaySubject<T> extends Subject<T> {
    static final ReplaySubject$ReplayDisposable[] EMPTY = new ReplaySubject$ReplayDisposable[0];
    private static final Object[] EMPTY_ARRAY = new Object[0];
    static final ReplaySubject$ReplayDisposable[] TERMINATED = new ReplaySubject$ReplayDisposable[0];
    final ReplaySubject$ReplayBuffer<T> buffer;
    boolean done;
    final AtomicReference<ReplaySubject$ReplayDisposable<T>[]> observers = new AtomicReference(EMPTY);

    @CheckReturnValue
    public static <T> ReplaySubject<T> create() {
        return new ReplaySubject(new ReplaySubject$UnboundedReplayBuffer(16));
    }

    @CheckReturnValue
    public static <T> ReplaySubject<T> create(int i) {
        return new ReplaySubject(new ReplaySubject$UnboundedReplayBuffer(i));
    }

    @CheckReturnValue
    public static <T> ReplaySubject<T> createWithSize(int i) {
        return new ReplaySubject(new ReplaySubject$SizeBoundReplayBuffer(i));
    }

    static <T> ReplaySubject<T> createUnbounded() {
        return new ReplaySubject(new ReplaySubject$SizeBoundReplayBuffer(Integer.MAX_VALUE));
    }

    @CheckReturnValue
    public static <T> ReplaySubject<T> createWithTime(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return new ReplaySubject(new ReplaySubject$SizeAndTimeBoundReplayBuffer(Integer.MAX_VALUE, j, timeUnit, scheduler));
    }

    @CheckReturnValue
    public static <T> ReplaySubject<T> createWithTimeAndSize(long j, TimeUnit timeUnit, Scheduler scheduler, int i) {
        return new ReplaySubject(new ReplaySubject$SizeAndTimeBoundReplayBuffer(i, j, timeUnit, scheduler));
    }

    ReplaySubject(ReplaySubject$ReplayBuffer<T> replaySubject$ReplayBuffer) {
        this.buffer = replaySubject$ReplayBuffer;
    }

    protected void subscribeActual(Observer<? super T> observer) {
        ReplaySubject$ReplayDisposable replaySubject$ReplayDisposable = new ReplaySubject$ReplayDisposable(observer, this);
        observer.onSubscribe(replaySubject$ReplayDisposable);
        if (!replaySubject$ReplayDisposable.cancelled) {
            if (add(replaySubject$ReplayDisposable) && replaySubject$ReplayDisposable.cancelled) {
                remove(replaySubject$ReplayDisposable);
            } else {
                this.buffer.replay(replaySubject$ReplayDisposable);
            }
        }
    }

    public void onSubscribe(Disposable disposable) {
        if (this.done) {
            disposable.dispose();
        }
    }

    public void onNext(T t) {
        if (t == null) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
        } else if (!this.done) {
            ReplaySubject$ReplayBuffer replaySubject$ReplayBuffer = this.buffer;
            replaySubject$ReplayBuffer.add(t);
            for (ReplaySubject$ReplayDisposable replay : (ReplaySubject$ReplayDisposable[]) this.observers.get()) {
                replaySubject$ReplayBuffer.replay(replay);
            }
        }
    }

    public void onError(Throwable th) {
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        if (this.done) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.done = true;
        Object error = NotificationLite.error(th);
        ReplaySubject$ReplayBuffer replaySubject$ReplayBuffer = this.buffer;
        replaySubject$ReplayBuffer.addFinal(error);
        for (ReplaySubject$ReplayDisposable replay : terminate(error)) {
            replaySubject$ReplayBuffer.replay(replay);
        }
    }

    public void onComplete() {
        if (!this.done) {
            this.done = true;
            Object complete = NotificationLite.complete();
            ReplaySubject$ReplayBuffer replaySubject$ReplayBuffer = this.buffer;
            replaySubject$ReplayBuffer.addFinal(complete);
            for (ReplaySubject$ReplayDisposable replay : terminate(complete)) {
                replaySubject$ReplayBuffer.replay(replay);
            }
        }
    }

    public boolean hasObservers() {
        return ((ReplaySubject$ReplayDisposable[]) this.observers.get()).length != 0;
    }

    int observerCount() {
        return ((ReplaySubject$ReplayDisposable[]) this.observers.get()).length;
    }

    public Throwable getThrowable() {
        Object obj = this.buffer.get();
        if (NotificationLite.isError(obj)) {
            return NotificationLite.getError(obj);
        }
        return null;
    }

    public T getValue() {
        return this.buffer.getValue();
    }

    public Object[] getValues() {
        Object[] values = getValues(EMPTY_ARRAY);
        if (values == EMPTY_ARRAY) {
            return new Object[0];
        }
        return values;
    }

    public T[] getValues(T[] tArr) {
        return this.buffer.getValues(tArr);
    }

    public boolean hasComplete() {
        return NotificationLite.isComplete(this.buffer.get());
    }

    public boolean hasThrowable() {
        return NotificationLite.isError(this.buffer.get());
    }

    public boolean hasValue() {
        return this.buffer.size() != 0;
    }

    int size() {
        return this.buffer.size();
    }

    boolean add(ReplaySubject$ReplayDisposable<T> replaySubject$ReplayDisposable) {
        ReplaySubject$ReplayDisposable[] replaySubject$ReplayDisposableArr;
        Object obj;
        do {
            replaySubject$ReplayDisposableArr = (ReplaySubject$ReplayDisposable[]) this.observers.get();
            if (replaySubject$ReplayDisposableArr == TERMINATED) {
                return false;
            }
            int length = replaySubject$ReplayDisposableArr.length;
            obj = new ReplaySubject$ReplayDisposable[(length + 1)];
            System.arraycopy(replaySubject$ReplayDisposableArr, 0, obj, 0, length);
            obj[length] = replaySubject$ReplayDisposable;
        } while (!this.observers.compareAndSet(replaySubject$ReplayDisposableArr, obj));
        return true;
    }

    void remove(ReplaySubject$ReplayDisposable<T> replaySubject$ReplayDisposable) {
        ReplaySubject$ReplayDisposable[] replaySubject$ReplayDisposableArr;
        Object obj;
        do {
            replaySubject$ReplayDisposableArr = (ReplaySubject$ReplayDisposable[]) this.observers.get();
            if (replaySubject$ReplayDisposableArr != TERMINATED && replaySubject$ReplayDisposableArr != EMPTY) {
                int length = replaySubject$ReplayDisposableArr.length;
                int i = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    if (replaySubject$ReplayDisposableArr[i2] == replaySubject$ReplayDisposable) {
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
                    obj = new ReplaySubject$ReplayDisposable[(length - 1)];
                    System.arraycopy(replaySubject$ReplayDisposableArr, 0, obj, 0, i);
                    System.arraycopy(replaySubject$ReplayDisposableArr, i + 1, obj, i, (length - i) - 1);
                }
            } else {
                return;
            }
        } while (!this.observers.compareAndSet(replaySubject$ReplayDisposableArr, obj));
    }

    ReplaySubject$ReplayDisposable<T>[] terminate(Object obj) {
        if (this.buffer.compareAndSet(null, obj)) {
            return (ReplaySubject$ReplayDisposable[]) this.observers.getAndSet(TERMINATED);
        }
        return TERMINATED;
    }
}
