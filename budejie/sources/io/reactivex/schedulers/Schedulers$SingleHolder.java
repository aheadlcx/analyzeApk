package io.reactivex.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.internal.schedulers.SingleScheduler;

final class Schedulers$SingleHolder {
    static final Scheduler DEFAULT = new SingleScheduler();

    Schedulers$SingleHolder() {
    }
}
