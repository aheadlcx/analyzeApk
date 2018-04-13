package okhttp3.internal;

import java.util.concurrent.ThreadFactory;

final class b implements ThreadFactory {
    final /* synthetic */ String a;
    final /* synthetic */ boolean b;

    b(String str, boolean z) {
        this.a = str;
        this.b = z;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.a);
        thread.setDaemon(this.b);
        return thread;
    }
}
