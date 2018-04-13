package io.reactivex;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.NewThreadWorker;

final class Scheduler$DisposeTask implements Disposable, Runnable {
    final Runnable decoratedRun;
    Thread runner;
    final Scheduler$Worker w;

    Scheduler$DisposeTask(Runnable runnable, Scheduler$Worker scheduler$Worker) {
        this.decoratedRun = runnable;
        this.w = scheduler$Worker;
    }

    public void run() {
        this.runner = Thread.currentThread();
        try {
            this.decoratedRun.run();
        } finally {
            dispose();
            this.runner = null;
        }
    }

    public void dispose() {
        if (this.runner == Thread.currentThread() && (this.w instanceof NewThreadWorker)) {
            ((NewThreadWorker) this.w).shutdown();
        } else {
            this.w.dispose();
        }
    }

    public boolean isDisposed() {
        return this.w.isDisposed();
    }
}
