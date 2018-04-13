package rx.g;

import rx.internal.subscriptions.SequentialSubscription;
import rx.k;

public final class d implements k {
    final SequentialSubscription a = new SequentialSubscription();

    public boolean isUnsubscribed() {
        return this.a.isUnsubscribed();
    }

    public void unsubscribe() {
        this.a.unsubscribe();
    }

    public void a(k kVar) {
        if (kVar == null) {
            throw new IllegalArgumentException("Subscription can not be null");
        }
        this.a.update(kVar);
    }
}
