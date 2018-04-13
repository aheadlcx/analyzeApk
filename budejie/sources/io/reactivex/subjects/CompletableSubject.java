package io.reactivex.subjects;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableSubject extends Completable implements CompletableObserver {
    static final CompletableSubject$CompletableDisposable[] EMPTY = new CompletableSubject$CompletableDisposable[0];
    static final CompletableSubject$CompletableDisposable[] TERMINATED = new CompletableSubject$CompletableDisposable[0];
    Throwable error;
    final AtomicReference<CompletableSubject$CompletableDisposable[]> observers = new AtomicReference(EMPTY);
    final AtomicBoolean once = new AtomicBoolean();

    @CheckReturnValue
    public static CompletableSubject create() {
        return new CompletableSubject();
    }

    CompletableSubject() {
    }

    public void onSubscribe(Disposable disposable) {
        if (this.observers.get() == TERMINATED) {
            disposable.dispose();
        }
    }

    public void onError(Throwable th) {
        int i = 0;
        if (th == null) {
            th = new NullPointerException("Null errors are not allowed in 2.x");
        }
        if (this.once.compareAndSet(false, true)) {
            this.error = th;
            CompletableSubject$CompletableDisposable[] completableSubject$CompletableDisposableArr = (CompletableSubject$CompletableDisposable[]) this.observers.getAndSet(TERMINATED);
            int length = completableSubject$CompletableDisposableArr.length;
            while (i < length) {
                completableSubject$CompletableDisposableArr[i].actual.onError(th);
                i++;
            }
            return;
        }
        RxJavaPlugins.onError(th);
    }

    public void onComplete() {
        int i = 0;
        if (this.once.compareAndSet(false, true)) {
            CompletableSubject$CompletableDisposable[] completableSubject$CompletableDisposableArr = (CompletableSubject$CompletableDisposable[]) this.observers.getAndSet(TERMINATED);
            int length = completableSubject$CompletableDisposableArr.length;
            while (i < length) {
                completableSubject$CompletableDisposableArr[i].actual.onComplete();
                i++;
            }
        }
    }

    protected void subscribeActual(CompletableObserver completableObserver) {
        CompletableSubject$CompletableDisposable completableSubject$CompletableDisposable = new CompletableSubject$CompletableDisposable(completableObserver, this);
        completableObserver.onSubscribe(completableSubject$CompletableDisposable);
        if (!add(completableSubject$CompletableDisposable)) {
            Throwable th = this.error;
            if (th != null) {
                completableObserver.onError(th);
            } else {
                completableObserver.onComplete();
            }
        } else if (completableSubject$CompletableDisposable.isDisposed()) {
            remove(completableSubject$CompletableDisposable);
        }
    }

    boolean add(CompletableSubject$CompletableDisposable completableSubject$CompletableDisposable) {
        CompletableSubject$CompletableDisposable[] completableSubject$CompletableDisposableArr;
        Object obj;
        do {
            completableSubject$CompletableDisposableArr = (CompletableSubject$CompletableDisposable[]) this.observers.get();
            if (completableSubject$CompletableDisposableArr == TERMINATED) {
                return false;
            }
            int length = completableSubject$CompletableDisposableArr.length;
            obj = new CompletableSubject$CompletableDisposable[(length + 1)];
            System.arraycopy(completableSubject$CompletableDisposableArr, 0, obj, 0, length);
            obj[length] = completableSubject$CompletableDisposable;
        } while (!this.observers.compareAndSet(completableSubject$CompletableDisposableArr, obj));
        return true;
    }

    void remove(CompletableSubject$CompletableDisposable completableSubject$CompletableDisposable) {
        CompletableSubject$CompletableDisposable[] completableSubject$CompletableDisposableArr;
        Object obj;
        do {
            completableSubject$CompletableDisposableArr = (CompletableSubject$CompletableDisposable[]) this.observers.get();
            int length = completableSubject$CompletableDisposableArr.length;
            if (length != 0) {
                int i = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    if (completableSubject$CompletableDisposableArr[i2] == completableSubject$CompletableDisposable) {
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
                    obj = new CompletableSubject$CompletableDisposable[(length - 1)];
                    System.arraycopy(completableSubject$CompletableDisposableArr, 0, obj, 0, i);
                    System.arraycopy(completableSubject$CompletableDisposableArr, i + 1, obj, i, (length - i) - 1);
                }
            } else {
                return;
            }
        } while (!this.observers.compareAndSet(completableSubject$CompletableDisposableArr, obj));
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
        return this.observers.get() == TERMINATED && this.error == null;
    }

    public boolean hasObservers() {
        return ((CompletableSubject$CompletableDisposable[]) this.observers.get()).length != 0;
    }

    int observerCount() {
        return ((CompletableSubject$CompletableDisposable[]) this.observers.get()).length;
    }
}
