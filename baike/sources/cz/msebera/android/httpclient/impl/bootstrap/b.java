package cz.msebera.android.httpclient.impl.bootstrap;

import com.xiaomi.mipush.sdk.Constants;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

class b implements ThreadFactory {
    private final String a;
    private final ThreadGroup b;
    private final AtomicLong c;

    b(String str, ThreadGroup threadGroup) {
        this.a = str;
        this.b = threadGroup;
        this.c = new AtomicLong();
    }

    b(String str) {
        this(str, null);
    }

    public Thread newThread(Runnable runnable) {
        return new Thread(this.b, runnable, this.a + Constants.ACCEPT_TIME_SEPARATOR_SERVER + this.c.incrementAndGet());
    }
}
