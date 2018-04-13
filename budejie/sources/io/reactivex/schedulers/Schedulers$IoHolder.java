package io.reactivex.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.internal.schedulers.IoScheduler;

final class Schedulers$IoHolder {
    static final Scheduler DEFAULT = new IoScheduler();

    Schedulers$IoHolder() {
    }
}
