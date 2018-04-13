package io.reactivex.internal.operators.flowable;

import io.reactivex.Scheduler;
import io.reactivex.internal.operators.flowable.FlowableReplay.BoundedReplayBuffer;
import io.reactivex.internal.operators.flowable.FlowableReplay.Node;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.schedulers.Timed;
import java.util.concurrent.TimeUnit;

final class FlowableReplay$SizeAndTimeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
    private static final long serialVersionUID = 3457957419649567404L;
    final int limit;
    final long maxAge;
    final Scheduler scheduler;
    final TimeUnit unit;

    FlowableReplay$SizeAndTimeBoundReplayBuffer(int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.scheduler = scheduler;
        this.limit = i;
        this.maxAge = j;
        this.unit = timeUnit;
    }

    Object enterTransform(Object obj) {
        return new Timed(obj, this.scheduler.now(this.unit), this.unit);
    }

    Object leaveTransform(Object obj) {
        return ((Timed) obj).value();
    }

    void truncate() {
        long now = this.scheduler.now(this.unit) - this.maxAge;
        Node node = (Node) get();
        Node node2 = node;
        int i = 0;
        Node node3 = (Node) node.get();
        while (node3 != null) {
            if (this.size <= this.limit) {
                if (((Timed) node3.value).time() > now) {
                    break;
                }
                i++;
                this.size--;
                node2 = node3;
                node3 = (Node) node3.get();
            } else {
                i++;
                this.size--;
                node2 = node3;
                node3 = (Node) node3.get();
            }
        }
        if (i != 0) {
            setFirst(node2);
        }
    }

    void truncateFinal() {
        long now = this.scheduler.now(this.unit) - this.maxAge;
        Node node = (Node) get();
        Node node2 = node;
        int i = 0;
        Node node3 = (Node) node.get();
        while (node3 != null && this.size > 1 && ((Timed) node3.value).time() <= now) {
            i++;
            this.size--;
            node2 = node3;
            node3 = (Node) node3.get();
        }
        if (i != 0) {
            setFirst(node2);
        }
    }

    Node getHead() {
        long now = this.scheduler.now(this.unit) - this.maxAge;
        Node node = (Node) get();
        Node node2 = node;
        for (Node node3 = (Node) node.get(); node3 != null; node3 = (Node) node3.get()) {
            Timed timed = (Timed) node3.value;
            if (NotificationLite.isComplete(timed.value()) || NotificationLite.isError(timed.value()) || timed.time() > now) {
                break;
            }
            node2 = node3;
        }
        return node2;
    }
}
