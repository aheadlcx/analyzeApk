package qsbk.app.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class MyThreadPool {
    private static final String a = MyThreadPool.class.getSimpleName();
    private static ExecutorService b = Executors.newCachedThreadPool();
    private static int c = 0;

    public static void submit(Runnable runnable) {
        c++;
        DebugUtil.debug(a, "i = " + c + ",task=" + runnable.toString());
        try {
            b.submit(runnable);
        } catch (RejectedExecutionException e) {
            DebugUtil.debug(a, e.toString());
        } catch (NullPointerException e2) {
            DebugUtil.debug(a, e2.toString());
        }
    }
}
