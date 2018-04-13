package io.reactivex.processors;

import com.facebook.common.time.Clock;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.NotificationLite;
import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;

final class ReplayProcessor$SizeBoundReplayBuffer<T> extends AtomicReference<Object> implements ReplayProcessor$ReplayBuffer<T> {
    private static final long serialVersionUID = 3027920763113911982L;
    volatile boolean done;
    volatile ReplayProcessor$Node<Object> head;
    final int maxSize;
    int size;
    ReplayProcessor$Node<Object> tail;

    ReplayProcessor$SizeBoundReplayBuffer(int i) {
        this.maxSize = ObjectHelper.verifyPositive(i, "maxSize");
        ReplayProcessor$Node replayProcessor$Node = new ReplayProcessor$Node(null);
        this.tail = replayProcessor$Node;
        this.head = replayProcessor$Node;
    }

    void trim() {
        if (this.size > this.maxSize) {
            this.size--;
            this.head = (ReplayProcessor$Node) this.head.get();
        }
    }

    public void add(T t) {
        ReplayProcessor$Node replayProcessor$Node = new ReplayProcessor$Node(t);
        ReplayProcessor$Node replayProcessor$Node2 = this.tail;
        this.tail = replayProcessor$Node;
        this.size++;
        replayProcessor$Node2.set(replayProcessor$Node);
        trim();
    }

    public void addFinal(Object obj) {
        lazySet(obj);
        ReplayProcessor$Node replayProcessor$Node = new ReplayProcessor$Node(obj);
        ReplayProcessor$Node replayProcessor$Node2 = this.tail;
        this.tail = replayProcessor$Node;
        this.size++;
        replayProcessor$Node2.set(replayProcessor$Node);
        this.done = true;
    }

    public T getValue() {
        ReplayProcessor$Node replayProcessor$Node = this.head;
        ReplayProcessor$Node replayProcessor$Node2 = null;
        while (true) {
            ReplayProcessor$Node replayProcessor$Node3 = (ReplayProcessor$Node) replayProcessor$Node.get();
            if (replayProcessor$Node3 == null) {
                break;
            }
            replayProcessor$Node2 = replayProcessor$Node;
            replayProcessor$Node = replayProcessor$Node3;
        }
        T t = replayProcessor$Node.value;
        if (t == null) {
            return null;
        }
        return (NotificationLite.isComplete(t) || NotificationLite.isError(t)) ? replayProcessor$Node2.value : t;
    }

    public T[] getValues(T[] tArr) {
        int i = 0;
        ReplayProcessor$Node replayProcessor$Node = this.head;
        int size = size();
        if (size != 0) {
            if (tArr.length < size) {
                tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size);
            }
            ReplayProcessor$Node replayProcessor$Node2 = replayProcessor$Node;
            while (i != size) {
                replayProcessor$Node2 = (ReplayProcessor$Node) replayProcessor$Node2.get();
                tArr[i] = replayProcessor$Node2.value;
                i++;
            }
            if (tArr.length > size) {
                tArr[size] = null;
            }
        } else if (tArr.length != 0) {
            tArr[0] = null;
        }
        return tArr;
    }

    public void replay(ReplayProcessor$ReplaySubscription<T> replayProcessor$ReplaySubscription) {
        if (replayProcessor$ReplaySubscription.getAndIncrement() == 0) {
            int i;
            Object obj;
            c cVar = replayProcessor$ReplaySubscription.actual;
            ReplayProcessor$Node replayProcessor$Node = (ReplayProcessor$Node) replayProcessor$ReplaySubscription.index;
            if (replayProcessor$Node == null) {
                replayProcessor$Node = this.head;
                i = 1;
            } else {
                i = 1;
            }
            loop0:
            while (true) {
                long j = replayProcessor$ReplaySubscription.requested.get();
                long j2 = 0;
                ReplayProcessor$Node replayProcessor$Node2 = replayProcessor$Node;
                while (!replayProcessor$ReplaySubscription.cancelled) {
                    replayProcessor$Node = (ReplayProcessor$Node) replayProcessor$Node2.get();
                    if (replayProcessor$Node != null) {
                        obj = replayProcessor$Node.value;
                        if (this.done && replayProcessor$Node.get() == null) {
                            break loop0;
                        }
                        if (j == 0) {
                            j = replayProcessor$ReplaySubscription.requested.get() + j2;
                            if (j != 0) {
                            }
                        }
                        cVar.onNext(obj);
                        j--;
                        j2--;
                        replayProcessor$Node2 = replayProcessor$Node;
                    }
                    if (!(j2 == 0 || replayProcessor$ReplaySubscription.requested.get() == Clock.MAX_TIME)) {
                        replayProcessor$ReplaySubscription.requested.addAndGet(j2);
                    }
                    replayProcessor$ReplaySubscription.index = replayProcessor$Node2;
                    int addAndGet = replayProcessor$ReplaySubscription.addAndGet(-i);
                    if (addAndGet != 0) {
                        i = addAndGet;
                        replayProcessor$Node = replayProcessor$Node2;
                    } else {
                        return;
                    }
                }
                replayProcessor$ReplaySubscription.index = null;
                return;
            }
            if (NotificationLite.isComplete(obj)) {
                cVar.onComplete();
            } else {
                cVar.onError(NotificationLite.getError(obj));
            }
            replayProcessor$ReplaySubscription.index = null;
            replayProcessor$ReplaySubscription.cancelled = true;
        }
    }

    public int size() {
        int i = 0;
        ReplayProcessor$Node replayProcessor$Node = this.head;
        while (i != Integer.MAX_VALUE) {
            ReplayProcessor$Node replayProcessor$Node2 = (ReplayProcessor$Node) replayProcessor$Node.get();
            if (replayProcessor$Node2 == null) {
                Object obj = replayProcessor$Node.value;
                if (NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) {
                    return i - 1;
                }
                return i;
            }
            i++;
            replayProcessor$Node = replayProcessor$Node2;
        }
        return i;
    }
}
