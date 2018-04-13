package rx.internal.schedulers;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import rx.e.c;
import rx.g.b;
import rx.internal.util.f;
import rx.k;

public final class ScheduledAction extends AtomicReference<Thread> implements Runnable, k {
    private static final long serialVersionUID = -3962399486978279857L;
    final rx.b.a action;
    final f cancel;

    static final class Remover2 extends AtomicBoolean implements k {
        private static final long serialVersionUID = 247232374289553518L;
        final f parent;
        final ScheduledAction s;

        public Remover2(ScheduledAction scheduledAction, f fVar) {
            this.s = scheduledAction;
            this.parent = fVar;
        }

        public boolean isUnsubscribed() {
            return this.s.isUnsubscribed();
        }

        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.parent.b(this.s);
            }
        }
    }

    static final class Remover extends AtomicBoolean implements k {
        private static final long serialVersionUID = 247232374289553518L;
        final b parent;
        final ScheduledAction s;

        public Remover(ScheduledAction scheduledAction, b bVar) {
            this.s = scheduledAction;
            this.parent = bVar;
        }

        public boolean isUnsubscribed() {
            return this.s.isUnsubscribed();
        }

        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.parent.b(this.s);
            }
        }
    }

    final class a implements k {
        final /* synthetic */ ScheduledAction a;
        private final Future<?> b;

        a(ScheduledAction scheduledAction, Future<?> future) {
            this.a = scheduledAction;
            this.b = future;
        }

        public void unsubscribe() {
            if (this.a.get() != Thread.currentThread()) {
                this.b.cancel(true);
            } else {
                this.b.cancel(false);
            }
        }

        public boolean isUnsubscribed() {
            return this.b.isCancelled();
        }
    }

    public ScheduledAction(rx.b.a aVar) {
        this.action = aVar;
        this.cancel = new f();
    }

    public ScheduledAction(rx.b.a aVar, b bVar) {
        this.action = aVar;
        this.cancel = new f(new Remover(this, bVar));
    }

    public ScheduledAction(rx.b.a aVar, f fVar) {
        this.action = aVar;
        this.cancel = new f(new Remover2(this, fVar));
    }

    public void run() {
        try {
            lazySet(Thread.currentThread());
            this.action.call();
        } catch (Throwable e) {
            signalError(new IllegalStateException("Exception thrown on Scheduler.Worker thread. Add `onError` handling.", e));
        } catch (Throwable e2) {
            signalError(new IllegalStateException("Fatal Exception thrown on Scheduler.Worker thread.", e2));
        } finally {
            unsubscribe();
        }
    }

    void signalError(Throwable th) {
        c.a(th);
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }

    public boolean isUnsubscribed() {
        return this.cancel.isUnsubscribed();
    }

    public void unsubscribe() {
        if (!this.cancel.isUnsubscribed()) {
            this.cancel.unsubscribe();
        }
    }

    public void add(k kVar) {
        this.cancel.a(kVar);
    }

    public void add(Future<?> future) {
        this.cancel.a(new a(this, future));
    }

    public void addParent(b bVar) {
        this.cancel.a(new Remover(this, bVar));
    }

    public void addParent(f fVar) {
        this.cancel.a(new Remover2(this, fVar));
    }
}
