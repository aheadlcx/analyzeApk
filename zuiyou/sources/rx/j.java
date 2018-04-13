package rx;

import rx.internal.util.f;

public abstract class j<T> implements e<T>, k {
    private static final long NOT_SET = Long.MIN_VALUE;
    private f producer;
    private long requested;
    private final j<?> subscriber;
    private final f subscriptions;

    protected j() {
        this(null, false);
    }

    protected j(j<?> jVar) {
        this(jVar, true);
    }

    protected j(j<?> jVar, boolean z) {
        this.requested = NOT_SET;
        this.subscriber = jVar;
        f fVar = (!z || jVar == null) ? new f() : jVar.subscriptions;
        this.subscriptions = fVar;
    }

    public final void add(k kVar) {
        this.subscriptions.a(kVar);
    }

    public final void unsubscribe() {
        this.subscriptions.unsubscribe();
    }

    public final boolean isUnsubscribed() {
        return this.subscriptions.isUnsubscribed();
    }

    public void onStart() {
    }

    protected final void request(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("number requested cannot be negative: " + j);
        }
        synchronized (this) {
            if (this.producer != null) {
                f fVar = this.producer;
                fVar.request(j);
                return;
            }
            addToRequested(j);
        }
    }

    private void addToRequested(long j) {
        if (this.requested == NOT_SET) {
            this.requested = j;
            return;
        }
        long j2 = this.requested + j;
        if (j2 < 0) {
            this.requested = Long.MAX_VALUE;
        } else {
            this.requested = j2;
        }
    }

    public void setProducer(f fVar) {
        Object obj = null;
        synchronized (this) {
            long j = this.requested;
            this.producer = fVar;
            if (this.subscriber != null && j == NOT_SET) {
                obj = 1;
            }
        }
        if (obj != null) {
            this.subscriber.setProducer(this.producer);
        } else if (j == NOT_SET) {
            this.producer.request(Long.MAX_VALUE);
        } else {
            this.producer.request(j);
        }
    }
}
