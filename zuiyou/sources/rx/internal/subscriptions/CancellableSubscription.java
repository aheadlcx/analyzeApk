package rx.internal.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import rx.b.e;
import rx.e.c;
import rx.exceptions.a;
import rx.k;

public final class CancellableSubscription extends AtomicReference<e> implements k {
    private static final long serialVersionUID = 5718521705281392066L;

    public CancellableSubscription(e eVar) {
        super(eVar);
    }

    public boolean isUnsubscribed() {
        return get() == null;
    }

    public void unsubscribe() {
        if (get() != null) {
            e eVar = (e) getAndSet(null);
            if (eVar != null) {
                try {
                    eVar.a();
                } catch (Throwable e) {
                    a.b(e);
                    c.a(e);
                }
            }
        }
    }
}
