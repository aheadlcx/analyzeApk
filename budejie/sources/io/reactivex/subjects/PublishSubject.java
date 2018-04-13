package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

public final class PublishSubject<T> extends Subject<T> {
    static final PublishSubject$PublishDisposable[] EMPTY = new PublishSubject$PublishDisposable[0];
    static final PublishSubject$PublishDisposable[] TERMINATED = new PublishSubject$PublishDisposable[0];
    Throwable error;
    final AtomicReference<PublishSubject$PublishDisposable<T>[]> subscribers = new AtomicReference(EMPTY);

    @CheckReturnValue
    public static <T> PublishSubject<T> create() {
        return new PublishSubject();
    }

    PublishSubject() {
    }

    public void subscribeActual(Observer<? super T> observer) {
        PublishSubject$PublishDisposable publishSubject$PublishDisposable = new PublishSubject$PublishDisposable(observer, this);
        observer.onSubscribe(publishSubject$PublishDisposable);
        if (!add(publishSubject$PublishDisposable)) {
            Throwable th = this.error;
            if (th != null) {
                observer.onError(th);
            } else {
                observer.onComplete();
            }
        } else if (publishSubject$PublishDisposable.isDisposed()) {
            remove(publishSubject$PublishDisposable);
        }
    }

    boolean add(PublishSubject$PublishDisposable<T> publishSubject$PublishDisposable) {
        PublishSubject$PublishDisposable[] publishSubject$PublishDisposableArr;
        Object obj;
        do {
            publishSubject$PublishDisposableArr = (PublishSubject$PublishDisposable[]) this.subscribers.get();
            if (publishSubject$PublishDisposableArr == TERMINATED) {
                return false;
            }
            int length = publishSubject$PublishDisposableArr.length;
            obj = new PublishSubject$PublishDisposable[(length + 1)];
            System.arraycopy(publishSubject$PublishDisposableArr, 0, obj, 0, length);
            obj[length] = publishSubject$PublishDisposable;
        } while (!this.subscribers.compareAndSet(publishSubject$PublishDisposableArr, obj));
        return true;
    }

    void remove(PublishSubject$PublishDisposable<T> publishSubject$PublishDisposable) {
        PublishSubject$PublishDisposable[] publishSubject$PublishDisposableArr;
        Object obj;
        do {
            publishSubject$PublishDisposableArr = (PublishSubject$PublishDisposable[]) this.subscribers.get();
            if (publishSubject$PublishDisposableArr != TERMINATED && publishSubject$PublishDisposableArr != EMPTY) {
                int length = publishSubject$PublishDisposableArr.length;
                int i = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    if (publishSubject$PublishDisposableArr[i2] == publishSubject$PublishDisposable) {
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
                    obj = new PublishSubject$PublishDisposable[(length - 1)];
                    System.arraycopy(publishSubject$PublishDisposableArr, 0, obj, 0, i);
                    System.arraycopy(publishSubject$PublishDisposableArr, i + 1, obj, i, (length - i) - 1);
                }
            } else {
                return;
            }
        } while (!this.subscribers.compareAndSet(publishSubject$PublishDisposableArr, obj));
    }

    public void onSubscribe(Disposable disposable) {
        if (this.subscribers.get() == TERMINATED) {
            disposable.dispose();
        }
    }

    public void onNext(T t) {
        if (this.subscribers.get() != TERMINATED) {
            if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                return;
            }
            for (PublishSubject$PublishDisposable onNext : (PublishSubject$PublishDisposable[]) this.subscribers.get()) {
                onNext.onNext(t);
            }
        }
    }

    public void onError(Throwable th) {
        if (this.subscribers.get() == TERMINATED) {
            RxJavaPlugins.onError(th);
            return;
        }
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        this.error = th;
        for (PublishSubject$PublishDisposable onError : (PublishSubject$PublishDisposable[]) this.subscribers.getAndSet(TERMINATED)) {
            onError.onError(th);
        }
    }

    public void onComplete() {
        if (this.subscribers.get() != TERMINATED) {
            for (PublishSubject$PublishDisposable onComplete : (PublishSubject$PublishDisposable[]) this.subscribers.getAndSet(TERMINATED)) {
                onComplete.onComplete();
            }
        }
    }

    public boolean hasObservers() {
        return ((PublishSubject$PublishDisposable[]) this.subscribers.get()).length != 0;
    }

    public Throwable getThrowable() {
        if (this.subscribers.get() == TERMINATED) {
            return this.error;
        }
        return null;
    }

    public boolean hasThrowable() {
        return this.subscribers.get() == TERMINATED && this.error != null;
    }

    public boolean hasComplete() {
        return this.subscribers.get() == TERMINATED && this.error == null;
    }
}
