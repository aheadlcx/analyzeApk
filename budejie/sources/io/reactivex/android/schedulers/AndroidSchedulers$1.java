package io.reactivex.android.schedulers;

import io.reactivex.Scheduler;
import java.util.concurrent.Callable;

class AndroidSchedulers$1 implements Callable<Scheduler> {
    AndroidSchedulers$1() {
    }

    public Scheduler call() throws Exception {
        return AndroidSchedulers$MainHolder.DEFAULT;
    }
}
