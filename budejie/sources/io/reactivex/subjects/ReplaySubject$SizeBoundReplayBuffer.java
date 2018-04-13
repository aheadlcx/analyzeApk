package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.NotificationLite;
import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicReference;

final class ReplaySubject$SizeBoundReplayBuffer<T> extends AtomicReference<Object> implements ReplaySubject$ReplayBuffer<T> {
    private static final long serialVersionUID = 1107649250281456395L;
    volatile boolean done;
    volatile ReplaySubject$Node<Object> head;
    final int maxSize;
    int size;
    ReplaySubject$Node<Object> tail;

    ReplaySubject$SizeBoundReplayBuffer(int i) {
        this.maxSize = ObjectHelper.verifyPositive(i, "maxSize");
        ReplaySubject$Node replaySubject$Node = new ReplaySubject$Node(null);
        this.tail = replaySubject$Node;
        this.head = replaySubject$Node;
    }

    void trim() {
        if (this.size > this.maxSize) {
            this.size--;
            this.head = (ReplaySubject$Node) this.head.get();
        }
    }

    public void add(T t) {
        ReplaySubject$Node replaySubject$Node = new ReplaySubject$Node(t);
        ReplaySubject$Node replaySubject$Node2 = this.tail;
        this.tail = replaySubject$Node;
        this.size++;
        replaySubject$Node2.set(replaySubject$Node);
        trim();
    }

    public void addFinal(Object obj) {
        ReplaySubject$Node replaySubject$Node = new ReplaySubject$Node(obj);
        ReplaySubject$Node replaySubject$Node2 = this.tail;
        this.tail = replaySubject$Node;
        this.size++;
        replaySubject$Node2.lazySet(replaySubject$Node);
        this.done = true;
    }

    public T getValue() {
        ReplaySubject$Node replaySubject$Node = this.head;
        ReplaySubject$Node replaySubject$Node2 = null;
        while (true) {
            ReplaySubject$Node replaySubject$Node3 = (ReplaySubject$Node) replaySubject$Node.get();
            if (replaySubject$Node3 == null) {
                break;
            }
            replaySubject$Node2 = replaySubject$Node;
            replaySubject$Node = replaySubject$Node3;
        }
        T t = replaySubject$Node.value;
        if (t == null) {
            return null;
        }
        return (NotificationLite.isComplete(t) || NotificationLite.isError(t)) ? replaySubject$Node2.value : t;
    }

    public T[] getValues(T[] tArr) {
        int i = 0;
        ReplaySubject$Node replaySubject$Node = this.head;
        int size = size();
        if (size != 0) {
            if (tArr.length < size) {
                tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size);
            }
            ReplaySubject$Node replaySubject$Node2 = replaySubject$Node;
            while (i != size) {
                replaySubject$Node2 = (ReplaySubject$Node) replaySubject$Node2.get();
                tArr[i] = replaySubject$Node2.value;
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
            ReplaySubject$Node replaySubject$Node;
            int i;
            Observer observer = replaySubject$ReplayDisposable.actual;
            ReplaySubject$Node replaySubject$Node2 = (ReplaySubject$Node) replaySubject$ReplayDisposable.index;
            if (replaySubject$Node2 == null) {
                replaySubject$Node = this.head;
                i = 1;
            } else {
                replaySubject$Node = replaySubject$Node2;
                i = 1;
            }
            while (!replaySubject$ReplayDisposable.cancelled) {
                replaySubject$Node2 = (ReplaySubject$Node) replaySubject$Node.get();
                if (replaySubject$Node2 != null) {
                    Object obj = replaySubject$Node2.value;
                    if (this.done && replaySubject$Node2.get() == null) {
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
                    replaySubject$Node = replaySubject$Node2;
                } else if (replaySubject$Node.get() == null) {
                    replaySubject$ReplayDisposable.index = replaySubject$Node;
                    int addAndGet = replaySubject$ReplayDisposable.addAndGet(-i);
                    if (addAndGet != 0) {
                        i = addAndGet;
                    } else {
                        return;
                    }
                } else {
                    continue;
                }
            }
            replaySubject$ReplayDisposable.index = null;
        }
    }

    public int size() {
        int i = 0;
        ReplaySubject$Node replaySubject$Node = this.head;
        while (i != Integer.MAX_VALUE) {
            ReplaySubject$Node replaySubject$Node2 = (ReplaySubject$Node) replaySubject$Node.get();
            if (replaySubject$Node2 == null) {
                Object obj = replaySubject$Node.value;
                if (NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) {
                    return i - 1;
                }
                return i;
            }
            i++;
            replaySubject$Node = replaySubject$Node2;
        }
        return i;
    }
}
