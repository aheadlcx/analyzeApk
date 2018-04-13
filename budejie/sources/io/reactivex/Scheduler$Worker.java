package io.reactivex;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;

public abstract class Scheduler$Worker implements Disposable {

    final class PeriodicTask implements Runnable {
        long count;
        @NonNull
        final Runnable decoratedRun;
        long lastNowNanoseconds;
        final long periodInNanoseconds;
        @NonNull
        final SequentialDisposable sd;
        long startInNanoseconds;

        PeriodicTask(long j, Runnable runnable, @NonNull long j2, SequentialDisposable sequentialDisposable, @NonNull long j3) {
            this.decoratedRun = runnable;
            this.sd = sequentialDisposable;
            this.periodInNanoseconds = j3;
            this.lastNowNanoseconds = j2;
            this.startInNanoseconds = j;
        }

        public void run() {
            this.decoratedRun.run();
            if (!this.sd.isDisposed()) {
                long j;
                long now = Scheduler$Worker.this.now(TimeUnit.NANOSECONDS);
                long j2;
                if (Scheduler.CLOCK_DRIFT_TOLERANCE_NANOSECONDS + now < this.lastNowNanoseconds || now >= (this.lastNowNanoseconds + this.periodInNanoseconds) + Scheduler.CLOCK_DRIFT_TOLERANCE_NANOSECONDS) {
                    j = this.periodInNanoseconds + now;
                    j2 = this.periodInNanoseconds;
                    long j3 = this.count + 1;
                    this.count = j3;
                    this.startInNanoseconds = j - (j2 * j3);
                } else {
                    j = this.startInNanoseconds;
                    j2 = this.count + 1;
                    this.count = j2;
                    j += j2 * this.periodInNanoseconds;
                }
                this.lastNowNanoseconds = now;
                this.sd.replace(Scheduler$Worker.this.schedule(this, j - now, TimeUnit.NANOSECONDS));
            }
        }
    }

    @NonNull
    public abstract Disposable schedule(@NonNull Runnable runnable, long j, @NonNull TimeUnit timeUnit);

    @NonNull
    public Disposable schedule(@NonNull Runnable runnable) {
        return schedule(runnable, 0, TimeUnit.NANOSECONDS);
    }

    @NonNull
    public Disposable schedulePeriodically(@NonNull Runnable runnable, long j, long j2, @NonNull TimeUnit timeUnit) {
        Object sequentialDisposable = new SequentialDisposable();
        Disposable sequentialDisposable2 = new SequentialDisposable(sequentialDisposable);
        Runnable onSchedule = RxJavaPlugins.onSchedule(runnable);
        long toNanos = timeUnit.toNanos(j2);
        long now = now(TimeUnit.NANOSECONDS);
        Disposable schedule = schedule(new PeriodicTask(now + timeUnit.toNanos(j), onSchedule, now, sequentialDisposable2, toNanos), j, timeUnit);
        if (schedule == EmptyDisposable.INSTANCE) {
            return schedule;
        }
        sequentialDisposable.replace(schedule);
        return sequentialDisposable2;
    }

    public long now(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }
}
