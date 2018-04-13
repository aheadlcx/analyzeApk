package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class BehaviorSubject<T> extends Subject<T> {
    static final BehaviorSubject$BehaviorDisposable[] EMPTY = new BehaviorSubject$BehaviorDisposable[0];
    private static final Object[] EMPTY_ARRAY = new Object[0];
    static final BehaviorSubject$BehaviorDisposable[] TERMINATED = new BehaviorSubject$BehaviorDisposable[0];
    long index;
    final ReadWriteLock lock;
    final Lock readLock;
    final AtomicReference<BehaviorSubject$BehaviorDisposable<T>[]> subscribers;
    final AtomicReference<Throwable> terminalEvent;
    final AtomicReference<Object> value;
    final Lock writeLock;

    @CheckReturnValue
    public static <T> BehaviorSubject<T> create() {
        return new BehaviorSubject();
    }

    @CheckReturnValue
    public static <T> BehaviorSubject<T> createDefault(T t) {
        return new BehaviorSubject(t);
    }

    BehaviorSubject() {
        this.lock = new ReentrantReadWriteLock();
        this.readLock = this.lock.readLock();
        this.writeLock = this.lock.writeLock();
        this.subscribers = new AtomicReference(EMPTY);
        this.value = new AtomicReference();
        this.terminalEvent = new AtomicReference();
    }

    BehaviorSubject(T t) {
        this();
        this.value.lazySet(ObjectHelper.requireNonNull(t, "defaultValue is null"));
    }

    protected void subscribeActual(Observer<? super T> observer) {
        BehaviorSubject$BehaviorDisposable behaviorSubject$BehaviorDisposable = new BehaviorSubject$BehaviorDisposable(observer, this);
        observer.onSubscribe(behaviorSubject$BehaviorDisposable);
        if (!add(behaviorSubject$BehaviorDisposable)) {
            Throwable th = (Throwable) this.terminalEvent.get();
            if (th == ExceptionHelper.TERMINATED) {
                observer.onComplete();
            } else {
                observer.onError(th);
            }
        } else if (behaviorSubject$BehaviorDisposable.cancelled) {
            remove(behaviorSubject$BehaviorDisposable);
        } else {
            behaviorSubject$BehaviorDisposable.emitFirst();
        }
    }

    public void onSubscribe(Disposable disposable) {
        if (this.terminalEvent.get() != null) {
            disposable.dispose();
        }
    }

    public void onNext(T t) {
        if (t == null) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
        } else if (this.terminalEvent.get() == null) {
            Object next = NotificationLite.next(t);
            setCurrent(next);
            for (BehaviorSubject$BehaviorDisposable emitNext : (BehaviorSubject$BehaviorDisposable[]) this.subscribers.get()) {
                emitNext.emitNext(next, this.index);
            }
        }
    }

    public void onError(Throwable th) {
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        if (this.terminalEvent.compareAndSet(null, th)) {
            Object error = NotificationLite.error(th);
            for (BehaviorSubject$BehaviorDisposable emitNext : terminate(error)) {
                emitNext.emitNext(error, this.index);
            }
            return;
        }
        RxJavaPlugins.onError(th);
    }

    public void onComplete() {
        if (this.terminalEvent.compareAndSet(null, ExceptionHelper.TERMINATED)) {
            Object complete = NotificationLite.complete();
            for (BehaviorSubject$BehaviorDisposable emitNext : terminate(complete)) {
                emitNext.emitNext(complete, this.index);
            }
        }
    }

    public boolean hasObservers() {
        return ((BehaviorSubject$BehaviorDisposable[]) this.subscribers.get()).length != 0;
    }

    int subscriberCount() {
        return ((BehaviorSubject$BehaviorDisposable[]) this.subscribers.get()).length;
    }

    public Throwable getThrowable() {
        Object obj = this.value.get();
        if (NotificationLite.isError(obj)) {
            return NotificationLite.getError(obj);
        }
        return null;
    }

    public T getValue() {
        Object obj = this.value.get();
        if (NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) {
            return null;
        }
        return NotificationLite.getValue(obj);
    }

    public Object[] getValues() {
        Object[] values = getValues(EMPTY_ARRAY);
        if (values == EMPTY_ARRAY) {
            return new Object[0];
        }
        return values;
    }

    public T[] getValues(T[] tArr) {
        Object obj = this.value.get();
        if (obj != null && !NotificationLite.isComplete(obj) && !NotificationLite.isError(obj)) {
            Object value = NotificationLite.getValue(obj);
            if (tArr.length != 0) {
                tArr[0] = value;
                if (tArr.length == 1) {
                    return tArr;
                }
                tArr[1] = null;
                return tArr;
            }
            T[] tArr2 = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), 1);
            tArr2[0] = value;
            return tArr2;
        } else if (tArr.length == 0) {
            return tArr;
        } else {
            tArr[0] = null;
            return tArr;
        }
    }

    public boolean hasComplete() {
        return NotificationLite.isComplete(this.value.get());
    }

    public boolean hasThrowable() {
        return NotificationLite.isError(this.value.get());
    }

    public boolean hasValue() {
        Object obj = this.value.get();
        return (obj == null || NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) ? false : true;
    }

    boolean add(BehaviorSubject$BehaviorDisposable<T> behaviorSubject$BehaviorDisposable) {
        BehaviorSubject$BehaviorDisposable[] behaviorSubject$BehaviorDisposableArr;
        Object obj;
        do {
            behaviorSubject$BehaviorDisposableArr = (BehaviorSubject$BehaviorDisposable[]) this.subscribers.get();
            if (behaviorSubject$BehaviorDisposableArr == TERMINATED) {
                return false;
            }
            int length = behaviorSubject$BehaviorDisposableArr.length;
            obj = new BehaviorSubject$BehaviorDisposable[(length + 1)];
            System.arraycopy(behaviorSubject$BehaviorDisposableArr, 0, obj, 0, length);
            obj[length] = behaviorSubject$BehaviorDisposable;
        } while (!this.subscribers.compareAndSet(behaviorSubject$BehaviorDisposableArr, obj));
        return true;
    }

    void remove(BehaviorSubject$BehaviorDisposable<T> behaviorSubject$BehaviorDisposable) {
        BehaviorSubject$BehaviorDisposable[] behaviorSubject$BehaviorDisposableArr;
        Object obj;
        do {
            behaviorSubject$BehaviorDisposableArr = (BehaviorSubject$BehaviorDisposable[]) this.subscribers.get();
            if (behaviorSubject$BehaviorDisposableArr != TERMINATED && behaviorSubject$BehaviorDisposableArr != EMPTY) {
                int length = behaviorSubject$BehaviorDisposableArr.length;
                int i = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    if (behaviorSubject$BehaviorDisposableArr[i2] == behaviorSubject$BehaviorDisposable) {
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
                    obj = new BehaviorSubject$BehaviorDisposable[(length - 1)];
                    System.arraycopy(behaviorSubject$BehaviorDisposableArr, 0, obj, 0, i);
                    System.arraycopy(behaviorSubject$BehaviorDisposableArr, i + 1, obj, i, (length - i) - 1);
                }
            } else {
                return;
            }
        } while (!this.subscribers.compareAndSet(behaviorSubject$BehaviorDisposableArr, obj));
    }

    BehaviorSubject$BehaviorDisposable<T>[] terminate(Object obj) {
        BehaviorSubject$BehaviorDisposable<T>[] behaviorSubject$BehaviorDisposableArr = (BehaviorSubject$BehaviorDisposable[]) this.subscribers.get();
        if (behaviorSubject$BehaviorDisposableArr != TERMINATED) {
            behaviorSubject$BehaviorDisposableArr = (BehaviorSubject$BehaviorDisposable[]) this.subscribers.getAndSet(TERMINATED);
            if (behaviorSubject$BehaviorDisposableArr != TERMINATED) {
                setCurrent(obj);
            }
        }
        return behaviorSubject$BehaviorDisposableArr;
    }

    void setCurrent(Object obj) {
        this.writeLock.lock();
        try {
            this.index++;
            this.value.lazySet(obj);
        } finally {
            this.writeLock.unlock();
        }
    }
}
