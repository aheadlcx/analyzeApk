package io.reactivex.internal.subscriptions;

import com.facebook.common.time.Clock;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.d;

public class SubscriptionArbiter extends AtomicInteger implements d {
    private static final long serialVersionUID = -2189523197179400958L;
    d actual;
    volatile boolean cancelled;
    final AtomicLong missedProduced = new AtomicLong();
    final AtomicLong missedRequested = new AtomicLong();
    final AtomicReference<d> missedSubscription = new AtomicReference();
    long requested;
    protected boolean unbounded;

    public final void setSubscription(d dVar) {
        if (this.cancelled) {
            dVar.cancel();
            return;
        }
        ObjectHelper.requireNonNull(dVar, "s is null");
        d dVar2;
        if (get() == 0 && compareAndSet(0, 1)) {
            dVar2 = this.actual;
            if (dVar2 != null) {
                dVar2.cancel();
            }
            this.actual = dVar;
            long j = this.requested;
            if (decrementAndGet() != 0) {
                drainLoop();
            }
            if (j != 0) {
                dVar.request(j);
                return;
            }
            return;
        }
        dVar2 = (d) this.missedSubscription.getAndSet(dVar);
        if (dVar2 != null) {
            dVar2.cancel();
        }
        drain();
    }

    public final void request(long j) {
        if (SubscriptionHelper.validate(j) && !this.unbounded) {
            if (get() == 0 && compareAndSet(0, 1)) {
                long j2 = this.requested;
                if (j2 != Clock.MAX_TIME) {
                    j2 = BackpressureHelper.addCap(j2, j);
                    this.requested = j2;
                    if (j2 == Clock.MAX_TIME) {
                        this.unbounded = true;
                    }
                }
                d dVar = this.actual;
                if (decrementAndGet() != 0) {
                    drainLoop();
                }
                if (dVar != null) {
                    dVar.request(j);
                    return;
                }
                return;
            }
            BackpressureHelper.add(this.missedRequested, j);
            drain();
        }
    }

    public final void produced(long j) {
        long j2 = 0;
        if (!this.unbounded) {
            if (get() == 0 && compareAndSet(0, 1)) {
                long j3 = this.requested;
                if (j3 != Clock.MAX_TIME) {
                    j3 -= j;
                    if (j3 < 0) {
                        SubscriptionHelper.reportMoreProduced(j3);
                    } else {
                        j2 = j3;
                    }
                    this.requested = j2;
                }
                if (decrementAndGet() != 0) {
                    drainLoop();
                    return;
                }
                return;
            }
            BackpressureHelper.add(this.missedProduced, j);
            drain();
        }
    }

    public void cancel() {
        if (!this.cancelled) {
            this.cancelled = true;
            drain();
        }
    }

    final void drain() {
        if (getAndIncrement() == 0) {
            drainLoop();
        }
    }

    final void drainLoop() {
        long j = 0;
        d dVar = null;
        int i = 1;
        while (true) {
            long andSet;
            long andSet2;
            d dVar2 = (d) this.missedSubscription.get();
            if (dVar2 != null) {
                dVar2 = (d) this.missedSubscription.getAndSet(null);
            }
            long j2 = this.missedRequested.get();
            if (j2 != 0) {
                andSet = this.missedRequested.getAndSet(0);
            } else {
                andSet = j2;
            }
            j2 = this.missedProduced.get();
            if (j2 != 0) {
                andSet2 = this.missedProduced.getAndSet(0);
            } else {
                andSet2 = j2;
            }
            d dVar3 = this.actual;
            if (this.cancelled) {
                if (dVar3 != null) {
                    dVar3.cancel();
                    this.actual = null;
                }
                if (dVar2 != null) {
                    dVar2.cancel();
                    dVar2 = dVar;
                    j2 = j;
                }
                dVar2 = dVar;
                j2 = j;
            } else {
                long j3 = this.requested;
                if (j3 != Clock.MAX_TIME) {
                    j3 = BackpressureHelper.addCap(j3, andSet);
                    if (j3 != Clock.MAX_TIME) {
                        andSet2 = j3 - andSet2;
                        if (andSet2 < 0) {
                            SubscriptionHelper.reportMoreProduced(andSet2);
                            andSet2 = 0;
                        }
                    } else {
                        andSet2 = j3;
                    }
                    this.requested = andSet2;
                } else {
                    andSet2 = j3;
                }
                if (dVar2 != null) {
                    if (dVar3 != null) {
                        dVar3.cancel();
                    }
                    this.actual = dVar2;
                    if (andSet2 != 0) {
                        j2 = BackpressureHelper.addCap(j, andSet2);
                    }
                    dVar2 = dVar;
                    j2 = j;
                } else {
                    if (!(dVar3 == null || andSet == 0)) {
                        d dVar4 = dVar3;
                        j2 = BackpressureHelper.addCap(j, andSet);
                        dVar2 = dVar4;
                    }
                    dVar2 = dVar;
                    j2 = j;
                }
            }
            int addAndGet = addAndGet(-i);
            if (addAndGet == 0) {
                break;
            }
            j = j2;
            i = addAndGet;
            dVar = dVar2;
        }
        if (j2 != 0) {
            dVar2.request(j2);
        }
    }

    public final boolean isUnbounded() {
        return this.unbounded;
    }

    public final boolean isCancelled() {
        return this.cancelled;
    }
}
