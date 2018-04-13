package io.reactivex.processors;

import com.facebook.common.time.Clock;
import io.reactivex.Scheduler;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.NotificationLite;
import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;

final class ReplayProcessor$SizeAndTimeBoundReplayBuffer<T> extends AtomicReference<Object> implements ReplayProcessor$ReplayBuffer<T> {
    private static final long serialVersionUID = 1242561386470847675L;
    volatile boolean done;
    volatile ReplayProcessor$TimedNode<Object> head;
    final long maxAge;
    final int maxSize;
    final Scheduler scheduler;
    int size;
    ReplayProcessor$TimedNode<Object> tail;
    final TimeUnit unit;

    ReplayProcessor$SizeAndTimeBoundReplayBuffer(int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.maxSize = ObjectHelper.verifyPositive(i, "maxSize");
        this.maxAge = ObjectHelper.verifyPositive(j, "maxAge");
        this.unit = (TimeUnit) ObjectHelper.requireNonNull(timeUnit, "unit is null");
        this.scheduler = (Scheduler) ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        ReplayProcessor$TimedNode replayProcessor$TimedNode = new ReplayProcessor$TimedNode(null, 0);
        this.tail = replayProcessor$TimedNode;
        this.head = replayProcessor$TimedNode;
    }

    void trim() {
        if (this.size > this.maxSize) {
            this.size--;
            this.head = (ReplayProcessor$TimedNode) this.head.get();
        }
        long now = this.scheduler.now(this.unit) - this.maxAge;
        ReplayProcessor$TimedNode replayProcessor$TimedNode = this.head;
        while (true) {
            ReplayProcessor$TimedNode replayProcessor$TimedNode2 = (ReplayProcessor$TimedNode) replayProcessor$TimedNode.get();
            if (replayProcessor$TimedNode2 == null) {
                this.head = replayProcessor$TimedNode;
                return;
            } else if (replayProcessor$TimedNode2.time > now) {
                this.head = replayProcessor$TimedNode;
                return;
            } else {
                replayProcessor$TimedNode = replayProcessor$TimedNode2;
            }
        }
    }

    void trimFinal() {
        long now = this.scheduler.now(this.unit) - this.maxAge;
        ReplayProcessor$TimedNode replayProcessor$TimedNode = this.head;
        while (true) {
            ReplayProcessor$TimedNode replayProcessor$TimedNode2 = (ReplayProcessor$TimedNode) replayProcessor$TimedNode.get();
            if (replayProcessor$TimedNode2.get() == null) {
                this.head = replayProcessor$TimedNode;
                return;
            } else if (replayProcessor$TimedNode2.time > now) {
                this.head = replayProcessor$TimedNode;
                return;
            } else {
                replayProcessor$TimedNode = replayProcessor$TimedNode2;
            }
        }
    }

    public void add(T t) {
        ReplayProcessor$TimedNode replayProcessor$TimedNode = new ReplayProcessor$TimedNode(t, this.scheduler.now(this.unit));
        ReplayProcessor$TimedNode replayProcessor$TimedNode2 = this.tail;
        this.tail = replayProcessor$TimedNode;
        this.size++;
        replayProcessor$TimedNode2.set(replayProcessor$TimedNode);
        trim();
    }

    public void addFinal(Object obj) {
        lazySet(obj);
        ReplayProcessor$TimedNode replayProcessor$TimedNode = new ReplayProcessor$TimedNode(obj, Clock.MAX_TIME);
        ReplayProcessor$TimedNode replayProcessor$TimedNode2 = this.tail;
        this.tail = replayProcessor$TimedNode;
        this.size++;
        replayProcessor$TimedNode2.set(replayProcessor$TimedNode);
        trimFinal();
        this.done = true;
    }

    public T getValue() {
        ReplayProcessor$TimedNode replayProcessor$TimedNode = this.head;
        ReplayProcessor$TimedNode replayProcessor$TimedNode2 = null;
        while (true) {
            ReplayProcessor$TimedNode replayProcessor$TimedNode3 = (ReplayProcessor$TimedNode) replayProcessor$TimedNode.get();
            if (replayProcessor$TimedNode3 == null) {
                break;
            }
            replayProcessor$TimedNode2 = replayProcessor$TimedNode;
            replayProcessor$TimedNode = replayProcessor$TimedNode3;
        }
        T t = replayProcessor$TimedNode.value;
        if (t == null) {
            return null;
        }
        return (NotificationLite.isComplete(t) || NotificationLite.isError(t)) ? replayProcessor$TimedNode2.value : t;
    }

    public T[] getValues(T[] tArr) {
        int i = 0;
        ReplayProcessor$TimedNode head = getHead();
        int size = size(head);
        if (size != 0) {
            if (tArr.length < size) {
                tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size);
            }
            ReplayProcessor$TimedNode replayProcessor$TimedNode = head;
            while (i != size) {
                replayProcessor$TimedNode = (ReplayProcessor$TimedNode) replayProcessor$TimedNode.get();
                tArr[i] = replayProcessor$TimedNode.value;
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

    ReplayProcessor$TimedNode<Object> getHead() {
        ReplayProcessor$TimedNode<Object> replayProcessor$TimedNode = this.head;
        long now = this.scheduler.now(this.unit) - this.maxAge;
        ReplayProcessor$TimedNode<Object> replayProcessor$TimedNode2 = replayProcessor$TimedNode;
        replayProcessor$TimedNode = (ReplayProcessor$TimedNode) replayProcessor$TimedNode.get();
        while (replayProcessor$TimedNode != null && replayProcessor$TimedNode.time <= now) {
            replayProcessor$TimedNode2 = replayProcessor$TimedNode;
            replayProcessor$TimedNode = (ReplayProcessor$TimedNode) replayProcessor$TimedNode.get();
        }
        return replayProcessor$TimedNode2;
    }

    public void replay(ReplayProcessor$ReplaySubscription<T> replayProcessor$ReplaySubscription) {
        if (replayProcessor$ReplaySubscription.getAndIncrement() == 0) {
            int i;
            Object obj;
            c cVar = replayProcessor$ReplaySubscription.actual;
            ReplayProcessor$TimedNode replayProcessor$TimedNode = (ReplayProcessor$TimedNode) replayProcessor$ReplaySubscription.index;
            if (replayProcessor$TimedNode == null) {
                replayProcessor$TimedNode = getHead();
                i = 1;
            } else {
                i = 1;
            }
            loop0:
            while (true) {
                long j = replayProcessor$ReplaySubscription.requested.get();
                long j2 = 0;
                ReplayProcessor$TimedNode replayProcessor$TimedNode2 = replayProcessor$TimedNode;
                while (!replayProcessor$ReplaySubscription.cancelled) {
                    replayProcessor$TimedNode = (ReplayProcessor$TimedNode) replayProcessor$TimedNode2.get();
                    if (replayProcessor$TimedNode != null) {
                        obj = replayProcessor$TimedNode.value;
                        if (this.done && replayProcessor$TimedNode.get() == null) {
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
                        replayProcessor$TimedNode2 = replayProcessor$TimedNode;
                    }
                    if (!(j2 == 0 || replayProcessor$ReplaySubscription.requested.get() == Clock.MAX_TIME)) {
                        replayProcessor$ReplaySubscription.requested.addAndGet(j2);
                    }
                    replayProcessor$ReplaySubscription.index = replayProcessor$TimedNode2;
                    int addAndGet = replayProcessor$ReplaySubscription.addAndGet(-i);
                    if (addAndGet != 0) {
                        i = addAndGet;
                        replayProcessor$TimedNode = replayProcessor$TimedNode2;
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
        return size(getHead());
    }

    int size(ReplayProcessor$TimedNode<Object> replayProcessor$TimedNode) {
        int i = 0;
        while (i != Integer.MAX_VALUE) {
            ReplayProcessor$TimedNode<Object> replayProcessor$TimedNode2 = (ReplayProcessor$TimedNode) replayProcessor$TimedNode.get();
            if (replayProcessor$TimedNode2 == null) {
                Object obj = replayProcessor$TimedNode.value;
                if (NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) {
                    return i - 1;
                }
                return i;
            }
            i++;
            replayProcessor$TimedNode = replayProcessor$TimedNode2;
        }
        return i;
    }
}
