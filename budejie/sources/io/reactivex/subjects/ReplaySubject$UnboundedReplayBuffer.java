package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.NotificationLite;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

final class ReplaySubject$UnboundedReplayBuffer<T> extends AtomicReference<Object> implements ReplaySubject$ReplayBuffer<T> {
    private static final long serialVersionUID = -733876083048047795L;
    final List<Object> buffer;
    volatile boolean done;
    volatile int size;

    ReplaySubject$UnboundedReplayBuffer(int i) {
        this.buffer = new ArrayList(ObjectHelper.verifyPositive(i, "capacityHint"));
    }

    public void add(T t) {
        this.buffer.add(t);
        this.size++;
    }

    public void addFinal(Object obj) {
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

    public void replay(ReplaySubject$ReplayDisposable<T> replaySubject$ReplayDisposable) {
        if (replaySubject$ReplayDisposable.getAndIncrement() == 0) {
            int intValue;
            int i;
            List list = this.buffer;
            Observer observer = replaySubject$ReplayDisposable.actual;
            Integer num = (Integer) replaySubject$ReplayDisposable.index;
            if (num != null) {
                intValue = num.intValue();
                i = 1;
            } else {
                replaySubject$ReplayDisposable.index = Integer.valueOf(0);
                intValue = 0;
                i = 1;
            }
            while (!replaySubject$ReplayDisposable.cancelled) {
                int i2 = this.size;
                while (i2 != intValue) {
                    if (replaySubject$ReplayDisposable.cancelled) {
                        replaySubject$ReplayDisposable.index = null;
                        return;
                    }
                    Object obj = list.get(intValue);
                    if (this.done && intValue + 1 == i2) {
                        i2 = this.size;
                        if (intValue + 1 == i2) {
                            if (NotificationLite.isComplete(obj)) {
                                observer.onComplete();
                            } else {
                                observer.onError(NotificationLite.getError(obj));
                            }
                            replaySubject$ReplayDisposable.index = null;
                            replaySubject$ReplayDisposable.cancelled = true;
                            return;
                        }
                    }
                    observer.onNext(obj);
                    intValue++;
                }
                if (intValue == this.size) {
                    replaySubject$ReplayDisposable.index = Integer.valueOf(intValue);
                    i = replaySubject$ReplayDisposable.addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
            replaySubject$ReplayDisposable.index = null;
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
