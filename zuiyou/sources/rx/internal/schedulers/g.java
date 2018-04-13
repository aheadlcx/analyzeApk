package rx.internal.schedulers;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.e.c;
import rx.g.a;
import rx.g.b;
import rx.g.e;
import rx.internal.util.RxThreadFactory;
import rx.internal.util.d;
import rx.internal.util.f;
import rx.k;

public class g extends a implements k {
    public static final int b = Integer.getInteger("rx.scheduler.jdk6.purge-frequency-millis", 1000).intValue();
    private static final boolean d;
    private static final ConcurrentHashMap<ScheduledThreadPoolExecutor, ScheduledThreadPoolExecutor> e = new ConcurrentHashMap();
    private static final AtomicReference<ScheduledExecutorService> f = new AtomicReference();
    private static volatile Object g;
    private static final Object h = new Object();
    volatile boolean a;
    private final ScheduledExecutorService c;

    static {
        boolean z = Boolean.getBoolean("rx.scheduler.jdk6.purge-force");
        int b = d.b();
        z = !z && (b == 0 || b >= 21);
        d = z;
    }

    public static void a(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        while (((ScheduledExecutorService) f.get()) == null) {
            ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, new RxThreadFactory("RxSchedulerPurge-"));
            if (f.compareAndSet(null, newScheduledThreadPool)) {
                newScheduledThreadPool.scheduleAtFixedRate(new Runnable() {
                    public void run() {
                        g.c();
                    }
                }, (long) b, (long) b, TimeUnit.MILLISECONDS);
                break;
            }
            newScheduledThreadPool.shutdownNow();
        }
        e.putIfAbsent(scheduledThreadPoolExecutor, scheduledThreadPoolExecutor);
    }

    public static void a(ScheduledExecutorService scheduledExecutorService) {
        e.remove(scheduledExecutorService);
    }

    static void c() {
        try {
            Iterator it = e.keySet().iterator();
            while (it.hasNext()) {
                ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) it.next();
                if (scheduledThreadPoolExecutor.isShutdown()) {
                    it.remove();
                } else {
                    scheduledThreadPoolExecutor.purge();
                }
            }
        } catch (Throwable th) {
            rx.exceptions.a.b(th);
            c.a(th);
        }
    }

    public static boolean b(ScheduledExecutorService scheduledExecutorService) {
        if (d) {
            Method c;
            if (scheduledExecutorService instanceof ScheduledThreadPoolExecutor) {
                Object obj = g;
                if (obj == h) {
                    return false;
                }
                if (obj == null) {
                    c = c(scheduledExecutorService);
                    if (c != null) {
                        obj = c;
                    } else {
                        obj = h;
                    }
                    g = obj;
                } else {
                    c = (Method) obj;
                }
            } else {
                c = c(scheduledExecutorService);
            }
            if (c != null) {
                try {
                    c.invoke(scheduledExecutorService, new Object[]{Boolean.valueOf(true)});
                    return true;
                } catch (Throwable e) {
                    c.a(e);
                } catch (Throwable e2) {
                    c.a(e2);
                } catch (Throwable e22) {
                    c.a(e22);
                }
            }
        }
        return false;
    }

    static Method c(ScheduledExecutorService scheduledExecutorService) {
        for (Method method : scheduledExecutorService.getClass().getMethods()) {
            if (method.getName().equals("setRemoveOnCancelPolicy")) {
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1 && parameterTypes[0] == Boolean.TYPE) {
                    return method;
                }
            }
        }
        return null;
    }

    public g(ThreadFactory threadFactory) {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        if (!b(newScheduledThreadPool) && (newScheduledThreadPool instanceof ScheduledThreadPoolExecutor)) {
            a((ScheduledThreadPoolExecutor) newScheduledThreadPool);
        }
        this.c = newScheduledThreadPool;
    }

    public k a(rx.b.a aVar) {
        return a(aVar, 0, null);
    }

    public k a(rx.b.a aVar, long j, TimeUnit timeUnit) {
        if (this.a) {
            return e.a();
        }
        return b(aVar, j, timeUnit);
    }

    public ScheduledAction b(rx.b.a aVar, long j, TimeUnit timeUnit) {
        Future submit;
        Runnable scheduledAction = new ScheduledAction(c.a(aVar));
        if (j <= 0) {
            submit = this.c.submit(scheduledAction);
        } else {
            submit = this.c.schedule(scheduledAction, j, timeUnit);
        }
        scheduledAction.add(submit);
        return scheduledAction;
    }

    public ScheduledAction a(rx.b.a aVar, long j, TimeUnit timeUnit, b bVar) {
        Future submit;
        k scheduledAction = new ScheduledAction(c.a(aVar), bVar);
        bVar.a(scheduledAction);
        if (j <= 0) {
            submit = this.c.submit(scheduledAction);
        } else {
            submit = this.c.schedule(scheduledAction, j, timeUnit);
        }
        scheduledAction.add(submit);
        return scheduledAction;
    }

    public ScheduledAction a(rx.b.a aVar, long j, TimeUnit timeUnit, f fVar) {
        Future submit;
        k scheduledAction = new ScheduledAction(c.a(aVar), fVar);
        fVar.a(scheduledAction);
        if (j <= 0) {
            submit = this.c.submit(scheduledAction);
        } else {
            submit = this.c.schedule(scheduledAction, j, timeUnit);
        }
        scheduledAction.add(submit);
        return scheduledAction;
    }

    public void unsubscribe() {
        this.a = true;
        this.c.shutdownNow();
        a(this.c);
    }

    public boolean isUnsubscribed() {
        return this.a;
    }
}
