package bolts;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

final class b {
    private static final b a = new b();
    private final ExecutorService b;
    private final ScheduledExecutorService c;
    private final Executor d;

    private static class a implements Executor {
        private ThreadLocal<Integer> a;

        private a() {
            this.a = new ThreadLocal();
        }

        private int a() {
            Integer num = (Integer) this.a.get();
            if (num == null) {
                num = Integer.valueOf(0);
            }
            int intValue = num.intValue() + 1;
            this.a.set(Integer.valueOf(intValue));
            return intValue;
        }

        private int b() {
            Integer num = (Integer) this.a.get();
            if (num == null) {
                num = Integer.valueOf(0);
            }
            int intValue = num.intValue() - 1;
            if (intValue == 0) {
                this.a.remove();
            } else {
                this.a.set(Integer.valueOf(intValue));
            }
            return intValue;
        }

        public void execute(Runnable runnable) {
            if (a() <= 15) {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    b();
                }
            } else {
                b.background().execute(runnable);
            }
            b();
        }
    }

    private static boolean c() {
        String property = System.getProperty("java.runtime.name");
        if (property == null) {
            return false;
        }
        return property.toLowerCase(Locale.US).contains("android");
    }

    private b() {
        this.b = !c() ? Executors.newCachedThreadPool() : a.newCachedThreadPool();
        this.c = Executors.newSingleThreadScheduledExecutor();
        this.d = new a();
    }

    public static ExecutorService background() {
        return a.b;
    }

    static ScheduledExecutorService a() {
        return a.c;
    }

    static Executor b() {
        return a.d;
    }
}
