package io.reactivex.processors;

import com.facebook.common.time.Clock;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.NotificationLite;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;

final class ReplayProcessor$UnboundedReplayBuffer<T> extends AtomicReference<Object> implements ReplayProcessor$ReplayBuffer<T> {
    private static final long serialVersionUID = -4457200895834877300L;
    final List<Object> buffer;
    volatile boolean done;
    volatile int size;

    ReplayProcessor$UnboundedReplayBuffer(int i) {
        this.buffer = new ArrayList(ObjectHelper.verifyPositive(i, "capacityHint"));
    }

    public void add(T t) {
        this.buffer.add(t);
        this.size++;
    }

    public void addFinal(Object obj) {
        lazySet(obj);
        this.buffer.add(obj);
        this.size++;
        this.done = true;
    }

    public T getValue() {
        int i = this.size;
        if (i == 0) {
            return null;
        }
        List list = this.buffer;
        T t = list.get(i - 1);
        if (!NotificationLite.isComplete(t) && !NotificationLite.isError(t)) {
            return t;
        }
        if (i == 1) {
            return null;
        }
        return list.get(i - 2);
    }

    public T[] getValues(T[] tArr) {
        int i = 0;
        int i2 = this.size;
        if (i2 != 0) {
            T[] tArr2;
            List list = this.buffer;
            Object obj = list.get(i2 - 1);
            if (NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) {
                i2--;
                if (i2 == 0) {
                    if (tArr.length == 0) {
                        return tArr;
                    }
                    tArr[0] = null;
                    return tArr;
                }
            }
            int i3 = i2;
            if (tArr.length < i3) {
                tArr2 = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), i3);
            } else {
                tArr2 = tArr;
            }
            while (i < i3) {
                tArr2[i] = list.get(i);
                i++;
            }
            if (tArr2.length > i3) {
                tArr2[i3] = null;
            }
            return tArr2;
        } else if (tArr.length == 0) {
            return tArr;
        } else {
            tArr[0] = null;
            return tArr;
        }
    }

    public void replay(ReplayProcessor$ReplaySubscription<T> replayProcessor$ReplaySubscription) {
        if (replayProcessor$ReplaySubscription.getAndIncrement() == 0) {
            int intValue;
            int i = 1;
            List list = this.buffer;
            c cVar = replayProcessor$ReplaySubscription.actual;
            Integer num = (Integer) replayProcessor$ReplaySubscription.index;
            if (num != null) {
                intValue = num.intValue();
            } else {
                intValue = 0;
                replayProcessor$ReplaySubscription.index = Integer.valueOf(0);
            }
            while (!replayProcessor$ReplaySubscription.cancelled) {
                int i2 = this.size;
                long j = replayProcessor$ReplaySubscription.requested.get();
                int i3 = i2;
                long j2 = 0;
                while (i3 != intValue) {
                    if (replayProcessor$ReplaySubscription.cancelled) {
                        replayProcessor$ReplaySubscription.index = null;
                        return;
                    }
                    Object obj = list.get(intValue);
                    if (this.done && intValue + 1 == i3) {
                        i3 = this.size;
                        if (intValue + 1 == i3) {
                            if (NotificationLite.isComplete(obj)) {
                                cVar.onComplete();
                            } else {
                                cVar.onError(NotificationLite.getError(obj));
                            }
                            replayProcessor$ReplaySubscription.index = null;
                            replayProcessor$ReplaySubscription.cancelled = true;
                            return;
                        }
                    }
                    if (j == 0) {
                        j = replayProcessor$ReplaySubscription.requested.get() + j2;
                        if (j == 0) {
                            break;
                        }
                    }
                    cVar.onNext(obj);
                    intValue++;
                    j2--;
                    j--;
                }
                if (!(j2 == 0 || replayProcessor$ReplaySubscription.requested.get() == Clock.MAX_TIME)) {
                    j = replayProcessor$ReplaySubscription.requested.addAndGet(j2);
                }
                if (intValue == this.size || r2 == 0) {
                    replayProcessor$ReplaySubscription.index = Integer.valueOf(intValue);
                    i = replayProcessor$ReplaySubscription.addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
            replayProcessor$ReplaySubscription.index = null;
        }
    }

    public int size() {
        int i = this.size;
        if (i == 0) {
            return 0;
        }
        Object obj = this.buffer.get(i - 1);
        if (NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) {
            return i - 1;
        }
        return i;
    }
}
