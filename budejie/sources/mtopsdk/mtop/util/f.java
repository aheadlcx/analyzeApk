package mtopsdk.mtop.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import mtopsdk.common.util.l;

class f implements ThreadFactory {
    private int a = 10;
    private final AtomicInteger b = new AtomicInteger();
    private String c = "";

    public f(int i) {
        this.a = i;
    }

    public f(int i, String str) {
        this.a = i;
        this.c = str;
    }

    public Thread newThread(Runnable runnable) {
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append("MTOPSDK ");
        if (l.a(this.c)) {
            stringBuilder.append(this.c).append(" ");
        } else {
            stringBuilder.append("DefaultPool ");
        }
        stringBuilder.append("Thread:").append(this.b.getAndIncrement());
        return new g(this, runnable, stringBuilder.toString());
    }
}
