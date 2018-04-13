package io.reactivex.processors;

import com.facebook.common.time.Clock;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class AsyncProcessor<T> extends FlowableProcessor<T> {
    static final AsyncProcessor$AsyncSubscription[] EMPTY = new AsyncProcessor$AsyncSubscription[0];
    static final AsyncProcessor$AsyncSubscription[] TERMINATED = new AsyncProcessor$AsyncSubscription[0];
    Throwable error;
    final AtomicReference<AsyncProcessor$AsyncSubscription<T>[]> subscribers = new AtomicReference(EMPTY);
    T value;

    @CheckReturnValue
    @NonNull
    public static <T> AsyncProcessor<T> create() {
        return new AsyncProcessor();
    }

    AsyncProcessor() {
    }

    public void onSubscribe(d dVar) {
        if (this.subscribers.get() == TERMINATED) {
            dVar.cancel();
        } else {
            dVar.request(Clock.MAX_TIME);
        }
    }

    public void onNext(T t) {
        if (this.subscribers.get() != TERMINATED) {
            if (t == null) {
                nullOnNext();
            } else {
                this.value = t;
            }
        }
    }

    void nullOnNext() {
        this.value = null;
        Throwable nullPointerException = new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        this.error = nullPointerException;
        for (AsyncProcessor$AsyncSubscription onError : (AsyncProcessor$AsyncSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
            onError.onError(nullPointerException);
        }
    }

    public void onError(Throwable th) {
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        if (this.subscribers.get() == TERMINATED) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.value = null;
        this.error = th;
        for (AsyncProcessor$AsyncSubscription onError : (AsyncProcessor$AsyncSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
            onError.onError(th);
        }
    }

    public void onComplete() {
        int i = 0;
        if (this.subscribers.get() != TERMINATED) {
            Object obj = this.value;
            AsyncProcessor$AsyncSubscription[] asyncProcessor$AsyncSubscriptionArr = (AsyncProcessor$AsyncSubscription[]) this.subscribers.getAndSet(TERMINATED);
            if (obj == null) {
                int length = asyncProcessor$AsyncSubscriptionArr.length;
                while (i < length) {
                    asyncProcessor$AsyncSubscriptionArr[i].onComplete();
                    i++;
                }
                return;
            }
            int length2 = asyncProcessor$AsyncSubscriptionArr.length;
            while (i < length2) {
                asyncProcessor$AsyncSubscriptionArr[i].complete(obj);
                i++;
            }
        }
    }

    public boolean hasSubscribers() {
        return ((AsyncProcessor$AsyncSubscription[]) this.subscribers.get()).length != 0;
    }

    public boolean hasThrowable() {
        return this.subscribers.get() == TERMINATED && this.error != null;
    }

    public boolean hasComplete() {
        return this.subscribers.get() == TERMINATED && this.error == null;
    }

    public Throwable getThrowable() {
        return this.subscribers.get() == TERMINATED ? this.error : null;
    }

    protected void subscribeActual(c<? super T> cVar) {
        AsyncProcessor$AsyncSubscription asyncProcessor$AsyncSubscription = new AsyncProcessor$AsyncSubscription(cVar, this);
        cVar.onSubscribe(asyncProcessor$AsyncSubscription);
        if (!add(asyncProcessor$AsyncSubscription)) {
            Throwable th = this.error;
            if (th != null) {
                cVar.onError(th);
                return;
            }
            Object obj = this.value;
            if (obj != null) {
                asyncProcessor$AsyncSubscription.complete(obj);
            } else {
                asyncProcessor$AsyncSubscription.onComplete();
            }
        } else if (asyncProcessor$AsyncSubscription.isCancelled()) {
            remove(asyncProcessor$AsyncSubscription);
        }
    }

    boolean add(AsyncProcessor$AsyncSubscription<T> asyncProcessor$AsyncSubscription) {
        AsyncProcessor$AsyncSubscription[] asyncProcessor$AsyncSubscriptionArr;
        Object obj;
        do {
            asyncProcessor$AsyncSubscriptionArr = (AsyncProcessor$AsyncSubscription[]) this.subscribers.get();
            if (asyncProcessor$AsyncSubscriptionArr == TERMINATED) {
                return false;
            }
            int length = asyncProcessor$AsyncSubscriptionArr.length;
            obj = new AsyncProcessor$AsyncSubscription[(length + 1)];
            System.arraycopy(asyncProcessor$AsyncSubscriptionArr, 0, obj, 0, length);
            obj[length] = asyncProcessor$AsyncSubscription;
        } while (!this.subscribers.compareAndSet(asyncProcessor$AsyncSubscriptionArr, obj));
        return true;
    }

    void remove(AsyncProcessor$AsyncSubscription<T> asyncProcessor$AsyncSubscription) {
        AsyncProcessor$AsyncSubscription[] asyncProcessor$AsyncSubscriptionArr;
        Object obj;
        do {
            asyncProcessor$AsyncSubscriptionArr = (AsyncProcessor$AsyncSubscription[]) this.subscribers.get();
            int length = asyncProcessor$AsyncSubscriptionArr.length;
            if (length != 0) {
                int i = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    if (asyncProcessor$AsyncSubscriptionArr[i2] == asyncProcessor$AsyncSubscription) {
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
                    obj = new AsyncProcessor$AsyncSubscription[(length - 1)];
                    System.arraycopy(asyncProcessor$AsyncSubscriptionArr, 0, obj, 0, i);
                    System.arraycopy(asyncProcessor$AsyncSubscriptionArr, i + 1, obj, i, (length - i) - 1);
                }
            } else {
                return;
            }
        } while (!this.subscribers.compareAndSet(asyncProcessor$AsyncSubscriptionArr, obj));
    }

    public boolean hasValue() {
        return this.subscribers.get() == TERMINATED && this.value != null;
    }

    public T getValue() {
        return this.subscribers.get() == TERMINATED ? this.value : null;
    }

    public Object[] getValues() {
        if (getValue() == null) {
            return new Object[0];
        }
        return new Object[]{getValue()};
    }

    public T[] getValues(T[] tArr) {
        Object value = getValue();
        if (value != null) {
            if (tArr.length == 0) {
                tArr = Arrays.copyOf(tArr, 1);
            }
            tArr[0] = value;
            if (tArr.length != 1) {
                tArr[1] = null;
            }
        } else if (tArr.length != 0) {
            tArr[0] = null;
        }
        return tArr;
    }
}
