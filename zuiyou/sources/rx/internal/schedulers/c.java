package rx.internal.schedulers;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import rx.g;
import rx.g.b;
import rx.g.e;
import rx.k;

public final class c extends g {
    final Executor a;

    static final class a extends rx.g.a implements Runnable {
        final Executor a;
        final b b = new b();
        final ConcurrentLinkedQueue<ScheduledAction> c = new ConcurrentLinkedQueue();
        final AtomicInteger d = new AtomicInteger();
        final ScheduledExecutorService e = d.b();

        public a(Executor executor) {
            this.a = executor;
        }

        public k a(rx.b.a aVar) {
            if (isUnsubscribed()) {
                return e.a();
            }
            k scheduledAction = new ScheduledAction(rx.e.c.a(aVar), this.b);
            this.b.a(scheduledAction);
            this.c.offer(scheduledAction);
            if (this.d.getAndIncrement() != 0) {
                return scheduledAction;
            }
            try {
                this.a.execute(this);
                return scheduledAction;
            } catch (Throwable e) {
                this.b.b(scheduledAction);
                this.d.decrementAndGet();
                rx.e.c.a(e);
                throw e;
            }
        }

        public void run() {
            while (!this.b.isUnsubscribed()) {
                ScheduledAction scheduledAction = (ScheduledAction) this.c.poll();
                if (scheduledAction != null) {
                    if (!scheduledAction.isUnsubscribed()) {
                        if (this.b.isUnsubscribed()) {
                            this.c.clear();
                            return;
                        }
                        scheduledAction.run();
                    }
                    if (this.d.decrementAndGet() == 0) {
                        return;
                    }
                }
                return;
            }
            this.c.clear();
        }

        public k a(rx.b.a aVar, long j, TimeUnit timeUnit) {
            if (j <= 0) {
                return a(aVar);
            }
            if (isUnsubscribed()) {
                return e.a();
            }
            final rx.b.a a = rx.e.c.a(aVar);
            Object cVar = new rx.g.c();
            final k cVar2 = new rx.g.c();
            cVar2.a(cVar);
            this.b.a(cVar2);
            final k a2 = e.a(new rx.b.a(this) {
                final /* synthetic */ a b;

                public void call() {
                    this.b.b.b(cVar2);
                }
            });
            ScheduledAction scheduledAction = new ScheduledAction(new rx.b.a(this) {
                final /* synthetic */ a d;

                public void call() {
                    if (!cVar2.isUnsubscribed()) {
                        k a = this.d.a(a);
                        cVar2.a(a);
                        if (a.getClass() == ScheduledAction.class) {
                            ((ScheduledAction) a).add(a2);
                        }
                    }
                }
            });
            cVar.a(scheduledAction);
            try {
                scheduledAction.add(this.e.schedule(scheduledAction, j, timeUnit));
                return a2;
            } catch (Throwable e) {
                rx.e.c.a(e);
                throw e;
            }
        }

        public boolean isUnsubscribed() {
            return this.b.isUnsubscribed();
        }

        public void unsubscribe() {
            this.b.unsubscribe();
            this.c.clear();
        }
    }

    public c(Executor executor) {
        this.a = executor;
    }

    public rx.g.a a() {
        return new a(this.a);
    }
}
