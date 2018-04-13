package io.reactivex.internal.operators.flowable;

import io.reactivex.Scheduler;
import io.reactivex.internal.operators.flowable.FlowableReplay.ReplayBuffer;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

final class FlowableReplay$ScheduledReplayBufferTask<T> implements Callable<ReplayBuffer<T>> {
    private final int bufferSize;
    private final long maxAge;
    private final Scheduler scheduler;
    private final TimeUnit unit;

    FlowableReplay$ScheduledReplayBufferTask(int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.bufferSize = i;
        this.maxAge = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    public ReplayBuffer<T> call() {
        return new FlowableReplay$SizeAndTimeBoundReplayBuffer(this.bufferSize, this.maxAge, this.unit, this.scheduler);
    }
}
