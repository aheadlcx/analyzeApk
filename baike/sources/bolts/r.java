package bolts;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

final class r implements Continuation<Object, Void> {
    final /* synthetic */ Object a;
    final /* synthetic */ ArrayList b;
    final /* synthetic */ AtomicBoolean c;
    final /* synthetic */ AtomicInteger d;
    final /* synthetic */ TaskCompletionSource e;

    r(Object obj, ArrayList arrayList, AtomicBoolean atomicBoolean, AtomicInteger atomicInteger, TaskCompletionSource taskCompletionSource) {
        this.a = obj;
        this.b = arrayList;
        this.c = atomicBoolean;
        this.d = atomicInteger;
        this.e = taskCompletionSource;
    }

    public Void then(Task<Object> task) {
        if (task.isFaulted()) {
            synchronized (this.a) {
                this.b.add(task.getError());
            }
        }
        if (task.isCancelled()) {
            this.c.set(true);
        }
        if (this.d.decrementAndGet() == 0) {
            if (this.b.size() != 0) {
                if (this.b.size() == 1) {
                    this.e.setError((Exception) this.b.get(0));
                } else {
                    this.e.setError(new AggregateException(String.format("There were %d exceptions.", new Object[]{Integer.valueOf(this.b.size())}), this.b));
                }
            } else if (this.c.get()) {
                this.e.setCancelled();
            } else {
                this.e.setResult(null);
            }
        }
        return null;
    }
}
