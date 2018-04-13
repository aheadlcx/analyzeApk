package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.operators.flowable.FlowableReplay.BoundedReplayBuffer;

final class FlowableReplay$SizeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
    private static final long serialVersionUID = -5898283885385201806L;
    final int limit;

    FlowableReplay$SizeBoundReplayBuffer(int i) {
        this.limit = i;
    }

    void truncate() {
        if (this.size > this.limit) {
            removeFirst();
        }
    }
}
