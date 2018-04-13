package io.reactivex.processors;

import com.facebook.common.time.Clock;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.Experimental;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.a.c;
import org.a.d;

public final class BehaviorProcessor<T> extends FlowableProcessor<T> {
    static final BehaviorProcessor$BehaviorSubscription[] EMPTY = new BehaviorProcessor$BehaviorSubscription[0];
    static final Object[] EMPTY_ARRAY = new Object[0];
    static final BehaviorProcessor$BehaviorSubscription[] TERMINATED = new BehaviorProcessor$BehaviorSubscription[0];
    long index;
    final ReadWriteLock lock;
    final Lock readLock;
    final AtomicReference<BehaviorProcessor$BehaviorSubscription<T>[]> subscribers;
    final AtomicReference<Throwable> terminalEvent;
    final AtomicReference<Object> value;
    final Lock writeLock;

    @CheckReturnValue
    public static <T> BehaviorProcessor<T> create() {
        return new BehaviorProcessor();
    }

    @CheckReturnValue
    public static <T> BehaviorProcessor<T> createDefault(T t) {
        ObjectHelper.requireNonNull(t, "defaultValue is null");
        return new BehaviorProcessor(t);
    }

    BehaviorProcessor() {
        this.value = new AtomicReference();
        this.lock = new ReentrantReadWriteLock();
        this.readLock = this.lock.readLock();
        this.writeLock = this.lock.writeLock();
        this.subscribers = new AtomicReference(EMPTY);
        this.terminalEvent = new AtomicReference();
    }

    BehaviorProcessor(T t) {
        this();
        this.value.lazySet(ObjectHelper.requireNonNull(t, "defaultValue is null"));
    }

    protected void subscribeActual(c<? super T> cVar) {
        BehaviorProcessor$BehaviorSubscription behaviorProcessor$BehaviorSubscription = new BehaviorProcessor$BehaviorSubscription(cVar, this);
        cVar.onSubscribe(behaviorProcessor$BehaviorSubscription);
        if (!add(behaviorProcessor$BehaviorSubscription)) {
            Throwable th = (Throwable) this.terminalEvent.get();
            if (th == ExceptionHelper.TERMINATED) {
                cVar.onComplete();
            } else {
                cVar.onError(th);
            }
        } else if (behaviorProcessor$BehaviorSubscription.cancelled) {
            remove(behaviorProcessor$BehaviorSubscription);
        } else {
            behaviorProcessor$BehaviorSubscription.emitFirst();
        }
    }

    public void onSubscribe(d dVar) {
        if (this.terminalEvent.get() != null) {
            dVar.cancel();
        } else {
            dVar.request(Clock.MAX_TIME);
        }
    }

    public void onNext(T t) {
        if (t == null) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
        } else if (this.terminalEvent.get() == null) {
            Object next = NotificationLite.next(t);
            setCurrent(next);
            for (BehaviorProcessor$BehaviorSubscription emitNext : (BehaviorProcessor$BehaviorSubscription[]) this.subscribers.get()) {
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
            for (BehaviorProcessor$BehaviorSubscription emitNext : terminate(error)) {
                emitNext.emitNext(error, this.index);
            }
            return;
        }
        RxJavaPlugins.onError(th);
    }

    public void onComplete() {
        if (this.terminalEvent.compareAndSet(null, ExceptionHelper.TERMINATED)) {
            Object complete = NotificationLite.complete();
            for (BehaviorProcessor$BehaviorSubscription emitNext : terminate(complete)) {
                emitNext.emitNext(complete, this.index);
            }
        }
    }

    @Experimental
    public boolean offer(T t) {
        int i = 0;
        if (t == null) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            return true;
        }
        BehaviorProcessor$BehaviorSubscription[] behaviorProcessor$BehaviorSubscriptionArr = (BehaviorProcessor$BehaviorSubscription[]) this.subscribers.get();
        for (BehaviorProcessor$BehaviorSubscription isFull : behaviorProcessor$BehaviorSubscriptionArr) {
            if (isFull.isFull()) {
                return false;
            }
        }
        Object next = NotificationLite.next(t);
        setCurrent(next);
        int length = behaviorProcessor$BehaviorSubscriptionArr.length;
        while (i < length) {
            behaviorProcessor$BehaviorSubscriptionArr[i].emitNext(next, this.index);
            i++;
        }
        return true;
    }

    public boolean hasSubscribers() {
        return ((BehaviorProcessor$BehaviorSubscription[]) this.subscribers.get()).length != 0;
    }

    int subscriberCount() {
        return ((BehaviorProcessor$BehaviorSubscription[]) this.subscribers.get()).length;
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

    boolean add(BehaviorProcessor$BehaviorSubscription<T> behaviorProcessor$BehaviorSubscription) {
        BehaviorProcessor$BehaviorSubscription[] behaviorProcessor$BehaviorSubscriptionArr;
        Object obj;
        do {
            behaviorProcessor$BehaviorSubscriptionArr = (BehaviorProcessor$BehaviorSubscription[]) this.subscribers.get();
            if (behaviorProcessor$BehaviorSubscriptionArr == TERMINATED) {
                return false;
            }
            int length = behaviorProcessor$BehaviorSubscriptionArr.length;
            obj = new BehaviorProcessor$BehaviorSubscription[(length + 1)];
            System.arraycopy(behaviorProcessor$BehaviorSubscriptionArr, 0, obj, 0, length);
            obj[length] = behaviorProcessor$BehaviorSubscription;
        } while (!this.subscribers.compareAndSet(behaviorProcessor$BehaviorSubscriptionArr, obj));
        return true;
    }

    void remove(BehaviorProcessor$BehaviorSubscription<T> behaviorProcessor$BehaviorSubscription) {
        BehaviorProcessor$BehaviorSubscription[] behaviorProcessor$BehaviorSubscriptionArr;
        Object obj;
        do {
            behaviorProcessor$BehaviorSubscriptionArr = (BehaviorProcessor$BehaviorSubscription[]) this.subscribers.get();
            if (behaviorProcessor$BehaviorSubscriptionArr != TERMINATED && behaviorProcessor$BehaviorSubscriptionArr != EMPTY) {
                int length = behaviorProcessor$BehaviorSubscriptionArr.length;
                int i = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    if (behaviorProcessor$BehaviorSubscriptionArr[i2] == behaviorProcessor$BehaviorSubscription) {
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
                    obj = new BehaviorProcessor$BehaviorSubscription[(length - 1)];
                    System.arraycopy(behaviorProcessor$BehaviorSubscriptionArr, 0, obj, 0, i);
                    System.arraycopy(behaviorProcessor$BehaviorSubscriptionArr, i + 1, obj, i, (length - i) - 1);
                }
            } else {
                return;
            }
        } while (!this.subscribers.compareAndSet(behaviorProcessor$BehaviorSubscriptionArr, obj));
    }

    BehaviorProcessor$BehaviorSubscription<T>[] terminate(Object obj) {
        BehaviorProcessor$BehaviorSubscription<T>[] behaviorProcessor$BehaviorSubscriptionArr = (BehaviorProcessor$BehaviorSubscription[]) this.subscribers.get();
        if (behaviorProcessor$BehaviorSubscriptionArr != TERMINATED) {
            behaviorProcessor$BehaviorSubscriptionArr = (BehaviorProcessor$BehaviorSubscription[]) this.subscribers.getAndSet(TERMINATED);
            if (behaviorProcessor$BehaviorSubscriptionArr != TERMINATED) {
                setCurrent(obj);
            }
        }
        return behaviorProcessor$BehaviorSubscriptionArr;
    }

    void setCurrent(Object obj) {
        Lock lock = this.writeLock;
        lock.lock();
        this.index++;
        this.value.lazySet(obj);
        lock.unlock();
    }
}
