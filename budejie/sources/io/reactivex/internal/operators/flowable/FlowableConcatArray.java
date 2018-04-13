package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableConcatArray<T> extends Flowable<T> {
    final boolean delayError;
    final b<? extends T>[] sources;

    static final class ConcatArraySubscriber<T> extends SubscriptionArbiter implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -8158322871608889516L;
        final c<? super T> actual;
        final boolean delayError;
        List<Throwable> errors;
        int index;
        long produced;
        final b<? extends T>[] sources;
        final AtomicInteger wip = new AtomicInteger();

        ConcatArraySubscriber(b<? extends T>[] bVarArr, boolean z, c<? super T> cVar) {
            this.actual = cVar;
            this.sources = bVarArr;
            this.delayError = z;
        }

        public void onSubscribe(d dVar) {
            setSubscription(dVar);
        }

        public void onNext(T t) {
            this.produced++;
            this.actual.onNext(t);
        }

        public void onError(Throwable th) {
            if (this.delayError) {
                List list = this.errors;
                if (list == null) {
                    list = new ArrayList((this.sources.length - this.index) + 1);
                    this.errors = list;
                }
                list.add(th);
                onComplete();
                return;
            }
            this.actual.onError(th);
        }

        public void onComplete() {
            if (this.wip.getAndIncrement() == 0) {
                b[] bVarArr = this.sources;
                int length = bVarArr.length;
                int i = this.index;
                while (i != length) {
                    b bVar = bVarArr[i];
                    if (bVar == null) {
                        Throwable nullPointerException = new NullPointerException("A Publisher entry is null");
                        if (this.delayError) {
                            List list = this.errors;
                            if (list == null) {
                                list = new ArrayList((length - i) + 1);
                                this.errors = list;
                            }
                            list.add(nullPointerException);
                            i++;
                        } else {
                            this.actual.onError(nullPointerException);
                            return;
                        }
                    }
                    long j = this.produced;
                    if (j != 0) {
                        this.produced = 0;
                        produced(j);
                    }
                    bVar.subscribe(this);
                    int i2 = i + 1;
                    this.index = i2;
                    if (this.wip.decrementAndGet() != 0) {
                        i = i2;
                    } else {
                        return;
                    }
                }
                Iterable iterable = this.errors;
                if (iterable == null) {
                    this.actual.onComplete();
                } else if (iterable.size() == 1) {
                    this.actual.onError((Throwable) iterable.get(0));
                } else {
                    this.actual.onError(new CompositeException(iterable));
                }
            }
        }
    }

    public FlowableConcatArray(b<? extends T>[] bVarArr, boolean z) {
        this.sources = bVarArr;
        this.delayError = z;
    }

    protected void subscribeActual(c<? super T> cVar) {
        Object concatArraySubscriber = new ConcatArraySubscriber(this.sources, this.delayError, cVar);
        cVar.onSubscribe(concatArraySubscriber);
        concatArraySubscriber.onComplete();
    }
}
