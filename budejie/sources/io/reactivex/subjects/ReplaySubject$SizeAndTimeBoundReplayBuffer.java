package io.reactivex.subjects;

import com.facebook.common.time.Clock;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.NotificationLite;
import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

final class ReplaySubject$SizeAndTimeBoundReplayBuffer<T> extends AtomicReference<Object> implements ReplaySubject$ReplayBuffer<T> {
    private static final long serialVersionUID = -8056260896137901749L;
    volatile boolean done;
    volatile ReplaySubject$TimedNode<Object> head;
    final long maxAge;
    final int maxSize;
    final Scheduler scheduler;
    int size;
    ReplaySubject$TimedNode<Object> tail;
    final TimeUnit unit;

    ReplaySubject$SizeAndTimeBoundReplayBuffer(int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.maxSize = ObjectHelper.verifyPositive(i, "maxSize");
        this.maxAge = ObjectHelper.verifyPositive(j, "maxAge");
        this.unit = (TimeUnit) ObjectHelper.requireNonNull(timeUnit, "unit is null");
        this.scheduler = (Scheduler) ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        ReplaySubject$TimedNode replaySubject$TimedNode = new ReplaySubject$TimedNode(null, 0);
        this.tail = replaySubject$TimedNode;
        this.head = replaySubject$TimedNode;
    }

    void trim() {
        if (this.size > this.maxSize) {
            this.size--;
            this.head = (ReplaySubject$TimedNode) this.head.get();
        }
        long now = this.scheduler.now(this.unit) - this.maxAge;
        ReplaySubject$TimedNode replaySubject$TimedNode = this.head;
        while (true) {
            ReplaySubject$TimedNode replaySubject$TimedNode2 = (ReplaySubject$TimedNode) replaySubject$TimedNode.get();
            if (replaySubject$TimedNode2 == null) {
                this.head = replaySubject$TimedNode;
                return;
            } else if (replaySubject$TimedNode2.time > now) {
                this.head = replaySubject$TimedNode;
                return;
            } else {
                replaySubject$TimedNode = replaySubject$TimedNode2;
            }
        }
    }

    void trimFinal() {
        long now = this.scheduler.now(this.unit) - this.maxAge;
        ReplaySubject$TimedNode replaySubject$TimedNode = this.head;
        while (true) {
            ReplaySubject$TimedNode replaySubject$TimedNode2 = (ReplaySubject$TimedNode) replaySubject$TimedNode.get();
            if (replaySubject$TimedNode2.get() == null) {
                this.head = replaySubject$TimedNode;
                return;
            } else if (replaySubject$TimedNode2.time > now) {
                this.head = replaySubject$TimedNode;
                return;
            } else {
                replaySubject$TimedNode = replaySubject$TimedNode2;
            }
        }
    }

    public void add(T t) {
        ReplaySubject$TimedNode replaySubject$TimedNode = new ReplaySubject$TimedNode(t, this.scheduler.now(this.unit));
        ReplaySubject$TimedNode replaySubject$TimedNode2 = this.tail;
        this.tail = replaySubject$TimedNode;
        this.size++;
        replaySubject$TimedNode2.set(replaySubject$TimedNode);
        trim();
    }

    public void addFinal(Object obj) {
        ReplaySubject$TimedNode replaySubject$TimedNode = new ReplaySubject$TimedNode(obj, Clock.MAX_TIME);
        ReplaySubject$TimedNode replaySubject$TimedNode2 = this.tail;
        this.tail = replaySubject$TimedNode;
        this.size++;
        replaySubject$TimedNode2.lazySet(replaySubject$TimedNode);
        trimFinal();
        this.done = true;
    }

    public T getValue() {
        ReplaySubject$TimedNode replaySubject$TimedNode = this.head;
        ReplaySubject$TimedNode replaySubject$TimedNode2 = null;
        while (true) {
            ReplaySubject$TimedNode replaySubject$TimedNode3 = (ReplaySubject$TimedNode) replaySubject$TimedNode.get();
            if (replaySubject$TimedNode3 == null) {
                break;
            }
            replaySubject$TimedNode2 = replaySubject$TimedNode;
            replaySubject$TimedNode = replaySubject$TimedNode3;
        }
        T t = replaySubject$TimedNode.value;
        if (t == null) {
            return null;
        }
        return (NotificationLite.isComplete(t) || NotificationLite.isError(t)) ? replaySubject$TimedNode2.value : t;
    }

    ReplaySubject$TimedNode<Object> getHead() {
        ReplaySubject$TimedNode<Object> replaySubject$TimedNode = this.head;
        long now = this.scheduler.now(this.unit) - this.maxAge;
        ReplaySubject$TimedNode<Object> replaySubject$TimedNode2 = replaySubject$TimedNode;
        replaySubject$TimedNode = (ReplaySubject$TimedNode) replaySubject$TimedNode.get();
        while (replaySubject$TimedNode != null && replaySubject$TimedNode.time <= now) {
            replaySubject$TimedNode2 = replaySubject$TimedNode;
            replaySubject$TimedNode = (ReplaySubject$TimedNode) replaySubject$TimedNode.get();
        }
        return replaySubject$TimedNode2;
    }

    public T[] getValues(T[] tArr) {
        int i = 0;
        ReplaySubject$TimedNode head = getHead();
        int size = size(head);
        if (size != 0) {
            if (tArr.length < size) {
                tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size);
            }
            ReplaySubject$TimedNode replaySubject$TimedNode = head;
            while (i != size) {
                replaySubject$TimedNode = (ReplaySubject$TimedNode) replaySubject$TimedNode.get();
                tArr[i] = replaySubject$TimedNode.value;
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

    public void replay(ReplaySubject$ReplayDisposable<T> replaySubject$ReplayDisposable) {
        if (replaySubject$ReplayDisposable.getAndIncrement() == 0) {
            int i;
            Observer observer = replaySubject$ReplayDisposable.actual;
            ReplaySubject$TimedNode replaySubject$TimedNode = (ReplaySubject$TimedNode) replaySubject$ReplayDisposable.index;
            if (replaySubject$TimedNode == null) {
                replaySubject$TimedNode = getHead();
                i = 1;
            } else {
                i = 1;
            }
            while (!replaySubject$ReplayDisposable.cancelled) {
                ReplaySubject$TimedNode replaySubject$TimedNode2 = replaySubject$TimedNode;
                while (!replaySubject$ReplayDisposable.cancelled) {
                    replaySubject$TimedNode = (ReplaySubject$TimedNode) replaySubject$TimedNode2.get();
                    if (replaySubject$TimedNode != null) {
                        Object obj = replaySubject$TimedNode.value;
                        if (this.done && replaySubject$TimedNode.get() == null) {
                            if (NotificationLite.isComplete(obj)) {
                                observer.onComplete();
                            } else {
                                observer.onError(NotificationLite.getError(obj));
                            }
                            replaySubject$ReplayDisposable.index = null;
                            replaySubject$ReplayDisposable.cancelled = true;
                            return;
                        }
                        observer.onNext(obj);
                        replaySubject$TimedNode2 = replaySubject$TimedNode;
                    } else if (replaySubject$TimedNode2.get() != null) {
                        replaySubject$TimedNode = replaySubject$TimedNode2;
                    } else {
                        replaySubject$ReplayDisposable.index = replaySubject$TimedNode2;
                        int addAndGet = replaySubject$ReplayDisposable.addAndGet(-i);
                        if (addAndGet != 0) {
                            i = addAndGet;
                            replaySubject$TimedNode = replaySubject$TimedNode2;
                        } else {
                            return;
                        }
                    }
                }
                replaySubject$ReplayDisposable.index = null;
                return;
            }
            replaySubject$ReplayDisposable.index = null;
        }
    }

    public int size() {
        return size(getHead());
    }

    int size(ReplaySubject$TimedNode<Object> replaySubject$TimedNode) {
        int i = 0;
        while (i != Integer.MAX_VALUE) {
            ReplaySubject$TimedNode<Object> replaySubject$TimedNode2 = (ReplaySubject$TimedNode) replaySubject$TimedNode.get();
            if (replaySubject$TimedNode2 == null) {
                Object obj = replaySubject$TimedNode.value;
                if (NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) {
                    return i - 1;
                }
                return i;
            }
            i++;
            replaySubject$TimedNode = replaySubject$TimedNode2;
        }
        return i;
    }
}
