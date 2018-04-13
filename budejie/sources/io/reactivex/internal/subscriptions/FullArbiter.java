package io.reactivex.internal.subscriptions;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import org.a.c;
import org.a.d;

public final class FullArbiter<T> extends FullArbiterPad2 implements d {
    static final d INITIAL = new InitialSubscription();
    static final Object REQUEST = new Object();
    final c<? super T> actual;
    volatile boolean cancelled;
    final SpscLinkedArrayQueue<Object> queue;
    long requested;
    Disposable resource;
    volatile d s = INITIAL;

    static final class InitialSubscription implements d {
        InitialSubscription() {
        }

        public void request(long j) {
        }

        public void cancel() {
        }
    }

    public FullArbiter(c<? super T> cVar, Disposable disposable, int i) {
        this.actual = cVar;
        this.resource = disposable;
        this.queue = new SpscLinkedArrayQueue(i);
    }

    public void request(long j) {
        if (SubscriptionHelper.validate(j)) {
            BackpressureHelper.add(this.missedRequested, j);
            this.queue.offer(REQUEST, REQUEST);
            drain();
        }
    }

    public void cancel() {
        if (!this.cancelled) {
            this.cancelled = true;
            dispose();
        }
    }

    void dispose() {
        Disposable disposable = this.resource;
        this.resource = null;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public boolean setSubscription(d dVar) {
        if (this.cancelled) {
            if (dVar != null) {
                dVar.cancel();
            }
            return false;
        }
        ObjectHelper.requireNonNull(dVar, "s is null");
        this.queue.offer(this.s, NotificationLite.subscription(dVar));
        drain();
        return true;
    }

    public boolean onNext(T t, d dVar) {
        if (this.cancelled) {
            return false;
        }
        this.queue.offer(dVar, NotificationLite.next(t));
        drain();
        return true;
    }

    public void onError(Throwable th, d dVar) {
        if (this.cancelled) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.queue.offer(dVar, NotificationLite.error(th));
        drain();
    }

    public void onComplete(d dVar) {
        this.queue.offer(dVar, NotificationLite.complete());
        drain();
    }

    void drain() {
        if (this.wip.getAndIncrement() == 0) {
            SpscLinkedArrayQueue spscLinkedArrayQueue = this.queue;
            c cVar = this.actual;
            int i = 1;
            while (true) {
                d poll = spscLinkedArrayQueue.poll();
                if (poll == null) {
                    i = this.wip.addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    Object poll2 = spscLinkedArrayQueue.poll();
                    if (poll == REQUEST) {
                        long andSet = this.missedRequested.getAndSet(0);
                        if (andSet != 0) {
                            this.requested = BackpressureHelper.addCap(this.requested, andSet);
                            this.s.request(andSet);
                        }
                    } else if (poll == this.s) {
                        long j;
                        if (NotificationLite.isSubscription(poll2)) {
                            poll = NotificationLite.getSubscription(poll2);
                            if (this.cancelled) {
                                poll.cancel();
                            } else {
                                this.s = poll;
                                j = this.requested;
                                if (j != 0) {
                                    poll.request(j);
                                }
                            }
                        } else if (NotificationLite.isError(poll2)) {
                            spscLinkedArrayQueue.clear();
                            dispose();
                            Throwable error = NotificationLite.getError(poll2);
                            if (this.cancelled) {
                                RxJavaPlugins.onError(error);
                            } else {
                                this.cancelled = true;
                                cVar.onError(error);
                            }
                        } else if (NotificationLite.isComplete(poll2)) {
                            spscLinkedArrayQueue.clear();
                            dispose();
                            if (!this.cancelled) {
                                this.cancelled = true;
                                cVar.onComplete();
                            }
                        } else {
                            j = this.requested;
                            if (j != 0) {
                                cVar.onNext(NotificationLite.getValue(poll2));
                                this.requested = j - 1;
                            }
                        }
                    }
                }
            }
        }
    }
}
