package cn.v6.sixrooms.surfaceanim;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolManager {
    private static CachedThreadPoolManager a;
    private ExecutorService b = Executors.newCachedThreadPool();

    private CachedThreadPoolManager() {
    }

    public static CachedThreadPoolManager getInstance() {
        if (a == null) {
            synchronized (CachedThreadPoolManager.class) {
                if (a == null) {
                    a = new CachedThreadPoolManager();
                }
            }
        }
        return a;
    }

    public ExecutorService getThreadPool() {
        return this.b;
    }
}
