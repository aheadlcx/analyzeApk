package io.reactivex;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.util.ExceptionHelper;

class Scheduler$PeriodicDirectTask implements Disposable, Runnable {
    @NonNull
    volatile boolean disposed;
    final Runnable run;
    @NonNull
    final Scheduler$Worker worker;

    Scheduler$PeriodicDirectTask(@NonNull Runnable runnable, @NonNull Scheduler$Worker scheduler$Worker) {
        this.run = runnable;
        this.worker = scheduler$Worker;
    }

    public void run() {
        if (!this.disposed) {
            try {
                this.run.run();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.worker.dispose();
                RuntimeException wrapOrThrow = ExceptionHelper.wrapOrThrow(th);
            }
        }
    }

    public void dispose() {
        this.disposed = true;
        this.worker.dispose();
    }

    public boolean isDisposed() {
        return this.disposed;
    }
}
