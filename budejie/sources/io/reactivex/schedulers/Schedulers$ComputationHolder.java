package io.reactivex.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.internal.schedulers.ComputationScheduler;

final class Schedulers$ComputationHolder {
    static final Scheduler DEFAULT = new ComputationScheduler();

    Schedulers$ComputationHolder() {
    }
}
