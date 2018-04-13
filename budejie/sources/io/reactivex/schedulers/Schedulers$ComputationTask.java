package io.reactivex.schedulers;

import io.reactivex.Scheduler;
import java.util.concurrent.Callable;

final class Schedulers$ComputationTask implements Callable<Scheduler> {
    Schedulers$ComputationTask() {
    }

    public Scheduler call() throws Exception {
        return Schedulers$ComputationHolder.DEFAULT;
    }
}
