package mtopsdk.mtop.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import mtopsdk.common.util.m;

public class e {
    private static int a = 10;
    private static volatile ThreadPoolExecutor b;
    private static volatile ThreadPoolExecutor c;
    private static volatile ExecutorService[] d;

    public static Future a(int i, Runnable runnable) {
        Future future = null;
        try {
            future = c()[Math.abs(i % c().length)].submit(runnable);
        } catch (Throwable th) {
            m.d("mtopsdk.MtopSDKThreadPoolExecutorFactory", "[submitCallbackTask]submit runnable to Mtop Callback ThreadPool error ---" + th.toString());
        }
        return future;
    }

    public static Future a(Runnable runnable) {
        Future future = null;
        try {
            future = a().submit(runnable);
        } catch (Throwable th) {
            m.d("mtopsdk.MtopSDKThreadPoolExecutorFactory", "[submit]submit runnable to Mtop Default ThreadPool error ---" + th.toString());
        }
        return future;
    }

    public static ThreadPoolExecutor a() {
        if (b == null) {
            synchronized (e.class) {
                if (b == null) {
                    b = a(3, 3, 1, 128, new f(a));
                }
            }
        }
        return b;
    }

    public static ThreadPoolExecutor a(int i, int i2, int i3, int i4, ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(i, i2, (long) i3, TimeUnit.SECONDS, i4 > 0 ? new LinkedBlockingQueue(i4) : new LinkedBlockingQueue(), threadFactory);
    }

    public static ThreadPoolExecutor b() {
        if (c == null) {
            synchronized (e.class) {
                if (c == null) {
                    c = a(4, 4, 1, 0, new f(a, "RequestPool"));
                }
            }
        }
        return c;
    }

    public static ExecutorService[] c() {
        if (d == null) {
            synchronized (e.class) {
                if (d == null) {
                    d = new ExecutorService[2];
                    for (int i = 0; i < 2; i++) {
                        d[i] = Executors.newSingleThreadExecutor(new f(a, "CallbackPool" + i));
                    }
                }
            }
        }
        return d;
    }
}
