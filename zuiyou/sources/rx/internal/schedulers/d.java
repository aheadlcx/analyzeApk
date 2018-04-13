package rx.internal.schedulers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicReference;

public final class d implements i {
    public static final d a = new d();
    private static final ScheduledExecutorService[] b = new ScheduledExecutorService[0];
    private static final ScheduledExecutorService c = Executors.newScheduledThreadPool(0);
    private static int e;
    private final AtomicReference<ScheduledExecutorService[]> d = new AtomicReference(b);

    static {
        c.shutdown();
    }

    private d() {
        a();
    }

    public void a() {
        int i = 8;
        int i2 = 0;
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (availableProcessors > 4) {
            availableProcessors /= 2;
        }
        if (availableProcessors <= 8) {
            i = availableProcessors;
        }
        Object obj = new ScheduledExecutorService[i];
        for (availableProcessors = 0; availableProcessors < i; availableProcessors++) {
            obj[availableProcessors] = GenericScheduledExecutorServiceFactory.create();
        }
        if (this.d.compareAndSet(b, obj)) {
            availableProcessors = obj.length;
            while (i2 < availableProcessors) {
                ScheduledExecutorService scheduledExecutorService = obj[i2];
                if (!g.b(scheduledExecutorService) && (scheduledExecutorService instanceof ScheduledThreadPoolExecutor)) {
                    g.a((ScheduledThreadPoolExecutor) scheduledExecutorService);
                }
                i2++;
            }
            return;
        }
        for (ScheduledExecutorService shutdownNow : obj) {
            shutdownNow.shutdownNow();
        }
    }

    public void d() {
        ScheduledExecutorService[] scheduledExecutorServiceArr;
        do {
            scheduledExecutorServiceArr = (ScheduledExecutorService[]) this.d.get();
            if (scheduledExecutorServiceArr == b) {
                return;
            }
        } while (!this.d.compareAndSet(scheduledExecutorServiceArr, b));
        for (ScheduledExecutorService scheduledExecutorService : scheduledExecutorServiceArr) {
            g.a(scheduledExecutorService);
            scheduledExecutorService.shutdownNow();
        }
    }

    public static ScheduledExecutorService b() {
        ScheduledExecutorService[] scheduledExecutorServiceArr = (ScheduledExecutorService[]) a.d.get();
        if (scheduledExecutorServiceArr == b) {
            return c;
        }
        int i = e + 1;
        if (i >= scheduledExecutorServiceArr.length) {
            i = 0;
        }
        e = i;
        return scheduledExecutorServiceArr[i];
    }
}
