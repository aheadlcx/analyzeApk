package rx.internal.subscriptions;

import rx.k;

public enum Unsubscribed implements k {
    INSTANCE;

    public boolean isUnsubscribed() {
        return true;
    }

    public void unsubscribe() {
    }
}
