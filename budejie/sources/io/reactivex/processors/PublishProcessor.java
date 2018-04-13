package io.reactivex.processors;

import com.facebook.common.time.Clock;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.Experimental;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class PublishProcessor<T> extends FlowableProcessor<T> {
    static final PublishProcessor$PublishSubscription[] EMPTY = new PublishProcessor$PublishSubscription[0];
    static final PublishProcessor$PublishSubscription[] TERMINATED = new PublishProcessor$PublishSubscription[0];
    Throwable error;
    final AtomicReference<PublishProcessor$PublishSubscription<T>[]> subscribers = new AtomicReference(EMPTY);

    @CheckReturnValue
    public static <T> PublishProcessor<T> create() {
        return new PublishProcessor();
    }

    PublishProcessor() {
    }

    public void subscribeActual(c<? super T> cVar) {
        PublishProcessor$PublishSubscription publishProcessor$PublishSubscription = new PublishProcessor$PublishSubscription(cVar, this);
        cVar.onSubscribe(publishProcessor$PublishSubscription);
        if (!add(publishProcessor$PublishSubscription)) {
            Throwable th = this.error;
            if (th != null) {
                cVar.onError(th);
            } else {
                cVar.onComplete();
            }
        } else if (publishProcessor$PublishSubscription.isCancelled()) {
            remove(publishProcessor$PublishSubscription);
        }
    }

    boolean add(PublishProcessor$PublishSubscription<T> publishProcessor$PublishSubscription) {
        PublishProcessor$PublishSubscription[] publishProcessor$PublishSubscriptionArr;
        Object obj;
        do {
            publishProcessor$PublishSubscriptionArr = (PublishProcessor$PublishSubscription[]) this.subscribers.get();
            if (publishProcessor$PublishSubscriptionArr == TERMINATED) {
                return false;
            }
            int length = publishProcessor$PublishSubscriptionArr.length;
            obj = new PublishProcessor$PublishSubscription[(length + 1)];
            System.arraycopy(publishProcessor$PublishSubscriptionArr, 0, obj, 0, length);
            obj[length] = publishProcessor$PublishSubscription;
        } while (!this.subscribers.compareAndSet(publishProcessor$PublishSubscriptionArr, obj));
        return true;
    }

    void remove(PublishProcessor$PublishSubscription<T> publishProcessor$PublishSubscription) {
        PublishProcessor$PublishSubscription[] publishProcessor$PublishSubscriptionArr;
        Object obj;
        do {
            publishProcessor$PublishSubscriptionArr = (PublishProcessor$PublishSubscription[]) this.subscribers.get();
            if (publishProcessor$PublishSubscriptionArr != TERMINATED && publishProcessor$PublishSubscriptionArr != EMPTY) {
                int length = publishProcessor$PublishSubscriptionArr.length;
                int i = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    if (publishProcessor$PublishSubscriptionArr[i2] == publishProcessor$PublishSubscription) {
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
                    obj = new PublishProcessor$PublishSubscription[(length - 1)];
                    System.arraycopy(publishProcessor$PublishSubscriptionArr, 0, obj, 0, i);
                    System.arraycopy(publishProcessor$PublishSubscriptionArr, i + 1, obj, i, (length - i) - 1);
                }
            } else {
                return;
            }
        } while (!this.subscribers.compareAndSet(publishProcessor$PublishSubscriptionArr, obj));
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
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                return;
            }
            for (PublishProcessor$PublishSubscription onNext : (PublishProcessor$PublishSubscription[]) this.subscribers.get()) {
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
        for (PublishProcessor$PublishSubscription onError : (PublishProcessor$PublishSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
            onError.onError(th);
        }
    }

    public void onComplete() {
        if (this.subscribers.get() != TERMINATED) {
            for (PublishProcessor$PublishSubscription onComplete : (PublishProcessor$PublishSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                onComplete.onComplete();
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
        PublishProcessor$PublishSubscription[] publishProcessor$PublishSubscriptionArr = (PublishProcessor$PublishSubscription[]) this.subscribers.get();
        for (PublishProcessor$PublishSubscription isFull : publishProcessor$PublishSubscriptionArr) {
            if (isFull.isFull()) {
                return false;
            }
        }
        int length = publishProcessor$PublishSubscriptionArr.length;
        while (i < length) {
            publishProcessor$PublishSubscriptionArr[i].onNext(t);
            i++;
        }
        return true;
    }

    public boolean hasSubscribers() {
        return ((PublishProcessor$PublishSubscription[]) this.subscribers.get()).length != 0;
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
