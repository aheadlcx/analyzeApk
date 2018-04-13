package rx.internal.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import rx.g.e;
import rx.k;

public final class SequentialSubscription extends AtomicReference<k> implements k {
    private static final long serialVersionUID = 995205034283130269L;

    public SequentialSubscription(k kVar) {
        lazySet(kVar);
    }

    public k current() {
        k kVar = (k) super.get();
        if (kVar == Unsubscribed.INSTANCE) {
            return e.a();
        }
        return kVar;
    }

    public boolean update(k kVar) {
        k kVar2;
        do {
            kVar2 = (k) get();
            if (kVar2 == Unsubscribed.INSTANCE) {
                if (kVar != null) {
                    kVar.unsubscribe();
                }
                return false;
            }
        } while (!compareAndSet(kVar2, kVar));
        if (kVar2 != null) {
            kVar2.unsubscribe();
        }
        return true;
    }

    public boolean replace(k kVar) {
        k kVar2;
        do {
            kVar2 = (k) get();
            if (kVar2 == Unsubscribed.INSTANCE) {
                if (kVar != null) {
                    kVar.unsubscribe();
                }
                return false;
            }
        } while (!compareAndSet(kVar2, kVar));
        return true;
    }

    public boolean updateWeak(k kVar) {
        k kVar2 = (k) get();
        if (kVar2 == Unsubscribed.INSTANCE) {
            if (kVar == null) {
                return false;
            }
            kVar.unsubscribe();
            return false;
        } else if (compareAndSet(kVar2, kVar)) {
            return true;
        } else {
            kVar2 = (k) get();
            if (kVar != null) {
                kVar.unsubscribe();
            }
            return kVar2 == Unsubscribed.INSTANCE;
        }
    }

    public boolean replaceWeak(k kVar) {
        k kVar2 = (k) get();
        if (kVar2 == Unsubscribed.INSTANCE) {
            if (kVar != null) {
                kVar.unsubscribe();
            }
            return false;
        } else if (compareAndSet(kVar2, kVar)) {
            return true;
        } else {
            if (((k) get()) != Unsubscribed.INSTANCE) {
                return true;
            }
            if (kVar != null) {
                kVar.unsubscribe();
            }
            return false;
        }
    }

    public void unsubscribe() {
        if (((k) get()) != Unsubscribed.INSTANCE) {
            k kVar = (k) getAndSet(Unsubscribed.INSTANCE);
            if (kVar != null && kVar != Unsubscribed.INSTANCE) {
                kVar.unsubscribe();
            }
        }
    }

    public boolean isUnsubscribed() {
        return get() == Unsubscribed.INSTANCE;
    }
}
