package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableAmb<T> extends Observable<T> {
    final ObservableSource<? extends T>[] sources;
    final Iterable<? extends ObservableSource<? extends T>> sourcesIterable;

    static final class AmbCoordinator<T> implements Disposable {
        final Observer<? super T> actual;
        final AmbInnerObserver<T>[] observers;
        final AtomicInteger winner = new AtomicInteger();

        AmbCoordinator(Observer<? super T> observer, int i) {
            this.actual = observer;
            this.observers = new AmbInnerObserver[i];
        }

        public void subscribe(ObservableSource<? extends T>[] observableSourceArr) {
            int i = 0;
            AmbInnerObserver[] ambInnerObserverArr = this.observers;
            int length = ambInnerObserverArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                ambInnerObserverArr[i2] = new AmbInnerObserver(this, i2 + 1, this.actual);
            }
            this.winner.lazySet(0);
            this.actual.onSubscribe(this);
            while (i < length && this.winner.get() == 0) {
                observableSourceArr[i].subscribe(ambInnerObserverArr[i]);
                i++;
            }
        }

        public boolean win(int i) {
            int i2 = 0;
            int i3 = this.winner.get();
            if (i3 == 0) {
                if (!this.winner.compareAndSet(0, i)) {
                    return false;
                }
                AmbInnerObserver[] ambInnerObserverArr = this.observers;
                int length = ambInnerObserverArr.length;
                while (i2 < length) {
                    if (i2 + 1 != i) {
                        ambInnerObserverArr[i2].dispose();
                    }
                    i2++;
                }
                return true;
            } else if (i3 != i) {
                return false;
            } else {
                return true;
            }
        }

        public void dispose() {
            if (this.winner.get() != -1) {
                this.winner.lazySet(-1);
                for (AmbInnerObserver dispose : this.observers) {
                    dispose.dispose();
                }
            }
        }

        public boolean isDisposed() {
            return this.winner.get() == -1;
        }
    }

    static final class AmbInnerObserver<T> extends AtomicReference<Disposable> implements Observer<T> {
        private static final long serialVersionUID = -1185974347409665484L;
        final Observer<? super T> actual;
        final int index;
        final AmbCoordinator<T> parent;
        boolean won;

        AmbInnerObserver(AmbCoordinator<T> ambCoordinator, int i, Observer<? super T> observer) {
            this.parent = ambCoordinator;
            this.index = i;
            this.actual = observer;
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        public void onNext(T t) {
            if (this.won) {
                this.actual.onNext(t);
            } else if (this.parent.win(this.index)) {
                this.won = true;
                this.actual.onNext(t);
            } else {
                ((Disposable) get()).dispose();
            }
        }

        public void onError(Throwable th) {
            if (this.won) {
                this.actual.onError(th);
            } else if (this.parent.win(this.index)) {
                this.won = true;
                this.actual.onError(th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        public void onComplete() {
            if (this.won) {
                this.actual.onComplete();
            } else if (this.parent.win(this.index)) {
                this.won = true;
                this.actual.onComplete();
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }

    public ObservableAmb(ObservableSource<? extends T>[] observableSourceArr, Iterable<? extends ObservableSource<? extends T>> iterable) {
        this.sources = observableSourceArr;
        this.sourcesIterable = iterable;
    }

    public void subscribeActual(Observer<? super T> observer) {
        ObservableSource[] observableSourceArr = this.sources;
        if (observableSourceArr == null) {
            Object obj = new Observable[8];
            try {
                int i = 0;
                for (ObservableSource observableSource : this.sourcesIterable) {
                    if (observableSource == null) {
                        EmptyDisposable.error(new NullPointerException("One of the sources is null"), observer);
                        return;
                    }
                    Object obj2;
                    if (i == obj.length) {
                        obj2 = new ObservableSource[((i >> 2) + i)];
                        System.arraycopy(obj, 0, obj2, 0, i);
                    } else {
                        obj2 = obj;
                    }
                    int i2 = i + 1;
                    obj2[i] = observableSource;
                    i = i2;
                    obj = obj2;
                }
                int i3 = i;
                observableSourceArr = obj;
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptyDisposable.error(th, observer);
                return;
            }
        }
        i3 = observableSourceArr.length;
        if (i3 == 0) {
            EmptyDisposable.complete(observer);
        } else if (i3 == 1) {
            observableSourceArr[0].subscribe(observer);
        } else {
            new AmbCoordinator(observer, i3).subscribe(observableSourceArr);
        }
    }
}
