package io.reactivex.processors;

import com.facebook.common.time.Clock;
import io.reactivex.Scheduler;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class ReplayProcessor<T> extends FlowableProcessor<T> {
    static final ReplayProcessor$ReplaySubscription[] EMPTY = new ReplayProcessor$ReplaySubscription[0];
    private static final Object[] EMPTY_ARRAY = new Object[0];
    static final ReplayProcessor$ReplaySubscription[] TERMINATED = new ReplayProcessor$ReplaySubscription[0];
    final ReplayProcessor$ReplayBuffer<T> buffer;
    boolean done;
    final AtomicReference<ReplayProcessor$ReplaySubscription<T>[]> subscribers = new AtomicReference(EMPTY);

    @CheckReturnValue
    public static <T> ReplayProcessor<T> create() {
        return new ReplayProcessor(new ReplayProcessor$UnboundedReplayBuffer(16));
    }

    @CheckReturnValue
    public static <T> ReplayProcessor<T> create(int i) {
        return new ReplayProcessor(new ReplayProcessor$UnboundedReplayBuffer(i));
    }

    @CheckReturnValue
    public static <T> ReplayProcessor<T> createWithSize(int i) {
        return new ReplayProcessor(new ReplayProcessor$SizeBoundReplayBuffer(i));
    }

    static <T> ReplayProcessor<T> createUnbounded() {
        return new ReplayProcessor(new ReplayProcessor$SizeBoundReplayBuffer(Integer.MAX_VALUE));
    }

    @CheckReturnValue
    public static <T> ReplayProcessor<T> createWithTime(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return new ReplayProcessor(new ReplayProcessor$SizeAndTimeBoundReplayBuffer(Integer.MAX_VALUE, j, timeUnit, scheduler));
    }

    @CheckReturnValue
    public static <T> ReplayProcessor<T> createWithTimeAndSize(long j, TimeUnit timeUnit, Scheduler scheduler, int i) {
        return new ReplayProcessor(new ReplayProcessor$SizeAndTimeBoundReplayBuffer(i, j, timeUnit, scheduler));
    }

    ReplayProcessor(ReplayProcessor$ReplayBuffer<T> replayProcessor$ReplayBuffer) {
        this.buffer = replayProcessor$ReplayBuffer;
    }

    protected void subscribeActual(c<? super T> cVar) {
        ReplayProcessor$ReplaySubscription replayProcessor$ReplaySubscription = new ReplayProcessor$ReplaySubscription(cVar, this);
        cVar.onSubscribe(replayProcessor$ReplaySubscription);
        if (add(replayProcessor$ReplaySubscription) && replayProcessor$ReplaySubscription.cancelled) {
            remove(replayProcessor$ReplaySubscription);
        } else {
            this.buffer.replay(replayProcessor$ReplaySubscription);
        }
    }

    public void onSubscribe(d dVar) {
        if (this.done) {
            dVar.cancel();
        } else {
            dVar.request(Clock.MAX_TIME);
        }
    }

    public void onNext(T t) {
        if (t == null) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
        } else if (!this.done) {
            ReplayProcessor$ReplayBuffer replayProcessor$ReplayBuffer = this.buffer;
            replayProcessor$ReplayBuffer.add(t);
            for (ReplayProcessor$ReplaySubscription replay : (ReplayProcessor$ReplaySubscription[]) this.subscribers.get()) {
                replayProcessor$ReplayBuffer.replay(replay);
            }
        }
    }

    public void onError(Throwable th) {
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        if (this.done) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.done = true;
        Object error = NotificationLite.error(th);
        ReplayProcessor$ReplayBuffer replayProcessor$ReplayBuffer = this.buffer;
        replayProcessor$ReplayBuffer.addFinal(error);
        for (ReplayProcessor$ReplaySubscription replay : (ReplayProcessor$ReplaySubscription[]) this.subscribers.getAndSet(TERMINATED)) {
            replayProcessor$ReplayBuffer.replay(replay);
        }
    }

    public void onComplete() {
        if (!this.done) {
            this.done = true;
            Object complete = NotificationLite.complete();
            ReplayProcessor$ReplayBuffer replayProcessor$ReplayBuffer = this.buffer;
            replayProcessor$ReplayBuffer.addFinal(complete);
            for (ReplayProcessor$ReplaySubscription replay : (ReplayProcessor$ReplaySubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                replayProcessor$ReplayBuffer.replay(replay);
            }
        }
    }

    public boolean hasSubscribers() {
        return ((ReplayProcessor$ReplaySubscription[]) this.subscribers.get()).length != 0;
    }

    int subscriberCount() {
        return ((ReplayProcessor$ReplaySubscription[]) this.subscribers.get()).length;
    }

    public Throwable getThrowable() {
        Object obj = this.buffer.get();
        if (NotificationLite.isError(obj)) {
            return NotificationLite.getError(obj);
        }
        return null;
    }

    public T getValue() {
        return this.buffer.getValue();
    }

    public Object[] getValues() {
        Object[] values = getValues(EMPTY_ARRAY);
        if (values == EMPTY_ARRAY) {
            return new Object[0];
        }
        return values;
    }

    public T[] getValues(T[] tArr) {
        return this.buffer.getValues(tArr);
    }

    public boolean hasComplete() {
        return NotificationLite.isComplete(this.buffer.get());
    }

    public boolean hasThrowable() {
        return NotificationLite.isError(this.buffer.get());
    }

    public boolean hasValue() {
        return this.buffer.size() != 0;
    }

    int size() {
        return this.buffer.size();
    }

    boolean add(ReplayProcessor$ReplaySubscription<T> replayProcessor$ReplaySubscription) {
        ReplayProcessor$ReplaySubscription[] replayProcessor$ReplaySubscriptionArr;
        Object obj;
        do {
            replayProcessor$ReplaySubscriptionArr = (ReplayProcessor$ReplaySubscription[]) this.subscribers.get();
            if (replayProcessor$ReplaySubscriptionArr == TERMINATED) {
                return false;
            }
            int length = replayProcessor$ReplaySubscriptionArr.length;
            obj = new ReplayProcessor$ReplaySubscription[(length + 1)];
            System.arraycopy(replayProcessor$ReplaySubscriptionArr, 0, obj, 0, length);
            obj[length] = replayProcessor$ReplaySubscription;
        } while (!this.subscribers.compareAndSet(replayProcessor$ReplaySubscriptionArr, obj));
        return true;
    }

    void remove(ReplayProcessor$ReplaySubscription<T> replayProcessor$ReplaySubscription) {
        ReplayProcessor$ReplaySubscription[] replayProcessor$ReplaySubscriptionArr;
        Object obj;
        do {
            replayProcessor$ReplaySubscriptionArr = (ReplayProcessor$ReplaySubscription[]) this.subscribers.get();
            if (replayProcessor$ReplaySubscriptionArr != TERMINATED && replayProcessor$ReplaySubscriptionArr != EMPTY) {
                int length = replayProcessor$ReplaySubscriptionArr.length;
                int i = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    if (replayProcessor$ReplaySubscriptionArr[i2] == replayProcessor$ReplaySubscription) {
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
                    obj = new ReplayProcessor$ReplaySubscription[(length - 1)];
                    System.arraycopy(replayProcessor$ReplaySubscriptionArr, 0, obj, 0, i);
                    System.arraycopy(replayProcessor$ReplaySubscriptionArr, i + 1, obj, i, (length - i) - 1);
                }
            } else {
                return;
            }
        } while (!this.subscribers.compareAndSet(replayProcessor$ReplaySubscriptionArr, obj));
    }
}
