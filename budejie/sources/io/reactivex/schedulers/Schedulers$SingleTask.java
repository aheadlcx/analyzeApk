package io.reactivex.schedulers;

import io.reactivex.Scheduler;
import java.util.concurrent.Callable;

final class Schedulers$SingleTask implements Callable<Scheduler> {
    Schedulers$SingleTask() {
    }

    public Scheduler call() throws Exception {
        return Schedulers$SingleHolder.DEFAULT;
    }
}
