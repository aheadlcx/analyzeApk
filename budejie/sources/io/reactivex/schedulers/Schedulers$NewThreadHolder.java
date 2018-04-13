package io.reactivex.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.internal.schedulers.NewThreadScheduler;

final class Schedulers$NewThreadHolder {
    static final Scheduler DEFAULT = new NewThreadScheduler();

    Schedulers$NewThreadHolder() {
    }
}
