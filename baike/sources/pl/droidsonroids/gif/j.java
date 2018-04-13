package pl.droidsonroids.gif;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;

final class j extends ScheduledThreadPoolExecutor {
    private static volatile j a = null;

    private j() {
        super(1, new DiscardPolicy());
    }

    public static j getInstance() {
        if (a == null) {
            synchronized (j.class) {
                if (a == null) {
                    a = new j();
                }
            }
        }
        return a;
    }
}
