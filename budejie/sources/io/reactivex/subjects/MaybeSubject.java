package io.reactivex.subjects;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeSubject<T> extends Maybe<T> implements MaybeObserver<T> {
    static final MaybeSubject$MaybeDisposable[] EMPTY = new MaybeSubject$MaybeDisposable[0];
    static final MaybeSubject$MaybeDisposable[] TERMINATED = new MaybeSubject$MaybeDisposable[0];
    Throwable error;
    final AtomicReference<MaybeSubject$MaybeDisposable<T>[]> observers = new AtomicReference(EMPTY);
    final AtomicBoolean once = new AtomicBoolean();
    T value;

    @CheckReturnValue
    public static <T> MaybeSubject<T> create() {
        return new MaybeSubject();
    }

    MaybeSubject() {
    }

    public void onSubscribe(Disposable disposable) {
        if (this.observers.get() == TERMINATED) {
            disposable.dispose();
        }
    }

    public void onSuccess(T t) {
        int i = 0;
        if (t == null) {
            onError(new NullPointerException("Null values are not allowed in 2.x"));
        } else if (this.once.compareAndSet(false, true)) {
            this.value = t;
            MaybeSubject$MaybeDisposable[] maybeSubject$MaybeDisposableArr = (MaybeSubject$MaybeDisposable[]) this.observers.getAndSet(TERMINATED);
            int length = maybeSubject$MaybeDisposableArr.length;
            while (i < length) {
                maybeSubject$MaybeDisposableArr[i].actual.onSuccess(t);
                i++;
            }
        }
    }

    public void onError(Throwable th) {
        int i = 0;
        if (th == null) {
            th = new NullPointerException("Null errors are not allowed in 2.x");
        }
        if (this.once.compareAndSet(false, true)) {
            this.error = th;
            MaybeSubject$MaybeDisposable[] maybeSubject$MaybeDisposableArr = (MaybeSubject$MaybeDisposable[]) this.observers.getAndSet(TERMINATED);
            int length = maybeSubject$MaybeDisposableArr.length;
            while (i < length) {
                maybeSubject$MaybeDisposableArr[i].actual.onError(th);
                i++;
            }
            return;
        }
        RxJavaPlugins.onError(th);
    }

    public void onComplete() {
        int i = 0;
        if (this.once.compareAndSet(false, true)) {
            MaybeSubject$MaybeDisposable[] maybeSubject$MaybeDisposableArr = (MaybeSubject$MaybeDisposable[]) this.observers.getAndSet(TERMINATED);
            int length = maybeSubject$MaybeDisposableArr.length;
            while (i < length) {
                maybeSubject$MaybeDisposableArr[i].actual.onComplete();
                i++;
            }
        }
    }

    protected void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        MaybeSubject$MaybeDisposable maybeSubject$MaybeDisposable = new MaybeSubject$MaybeDisposable(maybeObserver, this);
        maybeObserver.onSubscribe(maybeSubject$MaybeDisposable);
        if (!add(maybeSubject$MaybeDisposable)) {
            Throwable th = this.error;
            if (th != null) {
                maybeObserver.onError(th);
                return;
            }
            Object obj = this.value;
            if (obj == null) {
                maybeObserver.onComplete();
            } else {
                maybeObserver.onSuccess(obj);
            }
        } else if (maybeSubject$MaybeDisposable.isDisposed()) {
            remove(maybeSubject$MaybeDisposable);
        }
    }

    boolean add(MaybeSubject$MaybeDisposable<T> maybeSubject$MaybeDisposable) {
        MaybeSubject$MaybeDisposable[] maybeSubject$MaybeDisposableArr;
        Object obj;
        do {
            maybeSubject$MaybeDisposableArr = (MaybeSubject$MaybeDisposable[]) this.observers.get();
            if (maybeSubject$MaybeDisposableArr == TERMINATED) {
                return false;
            }
            int length = maybeSubject$MaybeDisposableArr.length;
            obj = new MaybeSubject$MaybeDisposable[(length + 1)];
            System.arraycopy(maybeSubject$MaybeDisposableArr, 0, obj, 0, length);
            obj[length] = maybeSubject$MaybeDisposable;
        } while (!this.observers.compareAndSet(maybeSubject$MaybeDisposableArr, obj));
        return true;
    }

    void remove(MaybeSubject$MaybeDisposable<T> maybeSubject$MaybeDisposable) {
        MaybeSubject$MaybeDisposable[] maybeSubject$MaybeDisposableArr;
        Object obj;
        do {
            maybeSubject$MaybeDisposableArr = (MaybeSubject$MaybeDisposable[]) this.observers.get();
            int length = maybeSubject$MaybeDisposableArr.length;
            if (length != 0) {
                int i = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    if (maybeSubject$MaybeDisposableArr[i2] == maybeSubject$MaybeDisposable) {
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
                    obj = new MaybeSubject$MaybeDisposable[(length - 1)];
                    System.arraycopy(maybeSubject$MaybeDisposableArr, 0, obj, 0, i);
                    System.arraycopy(maybeSubject$MaybeDisposableArr, i + 1, obj, i, (length - i) - 1);
                }
            } else {
                return;
            }
        } while (!this.observers.compareAndSet(maybeSubject$MaybeDisposableArr, obj));
    }

    public T getValue() {
        if (this.observers.get() == TERMINATED) {
            return this.value;
        }
        return null;
    }

    public boolean hasValue() {
        return this.observers.get() == TERMINATED && this.value != null;
    }

    public Throwable getThrowable() {
        if (this.observers.get() == TERMINATED) {
            return this.error;
        }
        return null;
    }

    public boolean hasThrowable() {
        return this.observers.get() == TERMINATED && this.error != null;
    }

    public boolean hasComplete() {
        return this.observers.get() == TERMINATED && this.value == null && this.error == null;
    }

    public boolean hasObservers() {
        return ((MaybeSubject$MaybeDisposable[]) this.observers.get()).length != 0;
    }

    int observerCount() {
        return ((MaybeSubject$MaybeDisposable[]) this.observers.get()).length;
    }
}
