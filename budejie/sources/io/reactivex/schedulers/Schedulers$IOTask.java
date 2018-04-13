package io.reactivex.schedulers;

import io.reactivex.Scheduler;
import java.util.concurrent.Callable;

final class Schedulers$IOTask implements Callable<Scheduler> {
    Schedulers$IOTask() {
    }

    public Scheduler call() throws Exception {
        return Schedulers$IoHolder.DEFAULT;
    }
}
