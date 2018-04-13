package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public final class AsyncSubject<T> extends Subject<T> {
    static final AsyncSubject$AsyncDisposable[] EMPTY = new AsyncSubject$AsyncDisposable[0];
    static final AsyncSubject$AsyncDisposable[] TERMINATED = new AsyncSubject$AsyncDisposable[0];
    Throwable error;
    final AtomicReference<AsyncSubject$AsyncDisposable<T>[]> subscribers = new AtomicReference(EMPTY);
    T value;

    @CheckReturnValue
    public static <T> AsyncSubject<T> create() {
        return new AsyncSubject();
    }

    AsyncSubject() {
    }

    public void onSubscribe(Disposable disposable) {
        if (this.subscribers.get() == TERMINATED) {
            disposable.dispose();
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
        for (AsyncSubject$AsyncDisposable onError : (AsyncSubject$AsyncDisposable[]) this.subscribers.getAndSet(TERMINATED)) {
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
        for (AsyncSubject$AsyncDisposable onError : (AsyncSubject$AsyncDisposable[]) this.subscribers.getAndSet(TERMINATED)) {
            onError.onError(th);
        }
    }

    public void onComplete() {
        int i = 0;
        if (this.subscribers.get() != TERMINATED) {
            Object obj = this.value;
            AsyncSubject$AsyncDisposable[] asyncSubject$AsyncDisposableArr = (AsyncSubject$AsyncDisposable[]) this.subscribers.getAndSet(TERMINATED);
            if (obj == null) {
                int length = asyncSubject$AsyncDisposableArr.length;
                while (i < length) {
                    asyncSubject$AsyncDisposableArr[i].onComplete();
                    i++;
                }
                return;
            }
            int length2 = asyncSubject$AsyncDisposableArr.length;
            while (i < length2) {
                asyncSubject$AsyncDisposableArr[i].complete(obj);
                i++;
            }
        }
    }

    public boolean hasObservers() {
        return ((AsyncSubject$AsyncDisposable[]) this.subscribers.get()).length != 0;
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

    protected void subscribeActual(Observer<? super T> observer) {
        AsyncSubject$AsyncDisposable asyncSubject$AsyncDisposable = new AsyncSubject$AsyncDisposable(observer, this);
        observer.onSubscribe(asyncSubject$AsyncDisposable);
        if (!add(asyncSubject$AsyncDisposable)) {
            Throwable th = this.error;
            if (th != null) {
                observer.onError(th);
                return;
            }
            Object obj = this.value;
            if (obj != null) {
                asyncSubject$AsyncDisposable.complete(obj);
            } else {
                asyncSubject$AsyncDisposable.onComplete();
            }
        } else if (asyncSubject$AsyncDisposable.isDisposed()) {
            remove(asyncSubject$AsyncDisposable);
        }
    }

    boolean add(AsyncSubject$AsyncDisposable<T> asyncSubject$AsyncDisposable) {
        AsyncSubject$AsyncDisposable[] asyncSubject$AsyncDisposableArr;
        Object obj;
        do {
            asyncSubject$AsyncDisposableArr = (AsyncSubject$AsyncDisposable[]) this.subscribers.get();
            if (asyncSubject$AsyncDisposableArr == TERMINATED) {
                return false;
            }
            int length = asyncSubject$AsyncDisposableArr.length;
            obj = new AsyncSubject$AsyncDisposable[(length + 1)];
            System.arraycopy(asyncSubject$AsyncDisposableArr, 0, obj, 0, length);
            obj[length] = asyncSubject$AsyncDisposable;
        } while (!this.subscribers.compareAndSet(asyncSubject$AsyncDisposableArr, obj));
        return true;
    }

    void remove(AsyncSubject$AsyncDisposable<T> asyncSubject$AsyncDisposable) {
        AsyncSubject$AsyncDisposable[] asyncSubject$AsyncDisposableArr;
        Object obj;
        do {
            asyncSubject$AsyncDisposableArr = (AsyncSubject$AsyncDisposable[]) this.subscribers.get();
            int length = asyncSubject$AsyncDisposableArr.length;
            if (length != 0) {
                int i = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    if (asyncSubject$AsyncDisposableArr[i2] == asyncSubject$AsyncDisposable) {
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
                    obj = new AsyncSubject$AsyncDisposable[(length - 1)];
                    System.arraycopy(asyncSubject$AsyncDisposableArr, 0, obj, 0, i);
                    System.arraycopy(asyncSubject$AsyncDisposableArr, i + 1, obj, i, (length - i) - 1);
                }
            } else {
                return;
            }
        } while (!this.subscribers.compareAndSet(asyncSubject$AsyncDisposableArr, obj));
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
