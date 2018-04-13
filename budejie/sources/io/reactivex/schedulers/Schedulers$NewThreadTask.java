package io.reactivex.schedulers;

import io.reactivex.Scheduler;
import java.util.concurrent.Callable;

final class Schedulers$NewThreadTask implements Callable<Scheduler> {
    Schedulers$NewThreadTask() {
    }

    public Scheduler call() throws Exception {
        return Schedulers$NewThreadHolder.DEFAULT;
    }
}
