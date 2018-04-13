package pl.droidsonroids.gif;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;

final class c extends ScheduledThreadPoolExecutor {

    private static final class a {
        private static final c a = new c();
    }

    static c a() {
        return a.a;
    }

    private c() {
        super(1, new DiscardPolicy());
    }
}
