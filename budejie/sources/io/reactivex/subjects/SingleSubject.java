package io.reactivex.subjects;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleSubject<T> extends Single<T> implements SingleObserver<T> {
    static final SingleSubject$SingleDisposable[] EMPTY = new SingleSubject$SingleDisposable[0];
    static final SingleSubject$SingleDisposable[] TERMINATED = new SingleSubject$SingleDisposable[0];
    Throwable error;
    final AtomicReference<SingleSubject$SingleDisposable<T>[]> observers = new AtomicReference(EMPTY);
    final AtomicBoolean once = new AtomicBoolean();
    T value;

    @CheckReturnValue
    @NonNull
    public static <T> SingleSubject<T> create() {
        return new SingleSubject();
    }

    SingleSubject() {
    }

    public void onSubscribe(@NonNull Disposable disposable) {
        if (this.observers.get() == TERMINATED) {
            disposable.dispose();
        }
    }

    public void onSuccess(@NonNull T t) {
        int i = 0;
        if (t == null) {
            onError(new NullPointerException("Null values are not allowed in 2.x"));
        } else if (this.once.compareAndSet(false, true)) {
            this.value = t;
            SingleSubject$SingleDisposable[] singleSubject$SingleDisposableArr = (SingleSubject$SingleDisposable[]) this.observers.getAndSet(TERMINATED);
            int length = singleSubject$SingleDisposableArr.length;
            while (i < length) {
                singleSubject$SingleDisposableArr[i].actual.onSuccess(t);
                i++;
            }
        }
    }

    public void onError(@NonNull Throwable th) {
        int i = 0;
        if (th == null) {
            th = new NullPointerException("Null errors are not allowed in 2.x");
        }
        if (this.once.compareAndSet(false, true)) {
            this.error = th;
            SingleSubject$SingleDisposable[] singleSubject$SingleDisposableArr = (SingleSubject$SingleDisposable[]) this.observers.getAndSet(TERMINATED);
            int length = singleSubject$SingleDisposableArr.length;
            while (i < length) {
                singleSubject$SingleDisposableArr[i].actual.onError(th);
                i++;
            }
            return;
        }
        RxJavaPlugins.onError(th);
    }

    protected void subscribeActual(@NonNull SingleObserver<? super T> singleObserver) {
        SingleSubject$SingleDisposable singleSubject$SingleDisposable = new SingleSubject$SingleDisposable(singleObserver, this);
        singleObserver.onSubscribe(singleSubject$SingleDisposable);
        if (!add(singleSubject$SingleDisposable)) {
            Throwable th = this.error;
            if (th != null) {
                singleObserver.onError(th);
            } else {
                singleObserver.onSuccess(this.value);
            }
        } else if (singleSubject$SingleDisposable.isDisposed()) {
            remove(singleSubject$SingleDisposable);
        }
    }

    boolean add(@NonNull SingleSubject$SingleDisposable<T> singleSubject$SingleDisposable) {
        SingleSubject$SingleDisposable[] singleSubject$SingleDisposableArr;
        Object obj;
        do {
            singleSubject$SingleDisposableArr = (SingleSubject$SingleDisposable[]) this.observers.get();
            if (singleSubject$SingleDisposableArr == TERMINATED) {
                return false;
            }
            int length = singleSubject$SingleDisposableArr.length;
            obj = new SingleSubject$SingleDisposable[(length + 1)];
            System.arraycopy(singleSubject$SingleDisposableArr, 0, obj, 0, length);
            obj[length] = singleSubject$SingleDisposable;
        } while (!this.observers.compareAndSet(singleSubject$SingleDisposableArr, obj));
        return true;
    }

    void remove(@NonNull SingleSubject$SingleDisposable<T> singleSubject$SingleDisposable) {
        SingleSubject$SingleDisposable[] singleSubject$SingleDisposableArr;
        Object obj;
        do {
            singleSubject$SingleDisposableArr = (SingleSubject$SingleDisposable[]) this.observers.get();
            int length = singleSubject$SingleDisposableArr.length;
            if (length != 0) {
                int i = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    if (singleSubject$SingleDisposableArr[i2] == singleSubject$SingleDisposable) {
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
                    obj = new SingleSubject$SingleDisposable[(length - 1)];
                    System.arraycopy(singleSubject$SingleDisposableArr, 0, obj, 0, i);
                    System.arraycopy(singleSubject$SingleDisposableArr, i + 1, obj, i, (length - i) - 1);
                }
            } else {
                return;
            }
        } while (!this.observers.compareAndSet(singleSubject$SingleDisposableArr, obj));
    }

    @Nullable
    public T getValue() {
        if (this.observers.get() == TERMINATED) {
            return this.value;
        }
        return null;
    }

    public boolean hasValue() {
        return this.observers.get() == TERMINATED && this.value != null;
    }

    @Nullable
    public Throwable getThrowable() {
        if (this.observers.get() == TERMINATED) {
            return this.error;
        }
        return null;
    }

    public boolean hasThrowable() {
        return this.observers.get() == TERMINATED && this.error != null;
    }

    public boolean hasObservers() {
        return ((SingleSubject$SingleDisposable[]) this.observers.get()).length != 0;
    }

    int observerCount() {
        return ((SingleSubject$SingleDisposable[]) this.observers.get()).length;
    }
}
