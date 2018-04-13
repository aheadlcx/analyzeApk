package io.reactivex.disposables;

import io.reactivex.annotations.NonNull;
import org.a.d;

final class SubscriptionDisposable extends ReferenceDisposable<d> {
    private static final long serialVersionUID = -707001650852963139L;

    SubscriptionDisposable(d dVar) {
        super(dVar);
    }

    protected void onDisposed(@NonNull d dVar) {
        dVar.cancel();
    }
}
