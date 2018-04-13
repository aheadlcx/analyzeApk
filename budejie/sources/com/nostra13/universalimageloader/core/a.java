package com.nostra13.universalimageloader.core;

import android.content.Context;
import com.nostra13.universalimageloader.a.a.b.b;
import com.nostra13.universalimageloader.a.b.a.c;
import com.nostra13.universalimageloader.b.g;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.assist.deque.LIFOLinkedBlockingDeque;
import com.nostra13.universalimageloader.core.b.d;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class a {

    private static class a implements ThreadFactory {
        private static final AtomicInteger a = new AtomicInteger(1);
        private final ThreadGroup b;
        private final AtomicInteger c = new AtomicInteger(1);
        private final String d;
        private final int e;

        a(int i, String str) {
            this.e = i;
            SecurityManager securityManager = System.getSecurityManager();
            this.b = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.d = str + a.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.b, runnable, this.d + this.c.getAndIncrement(), 0);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            thread.setPriority(this.e);
            return thread;
        }
    }

    public static Executor a(int i, int i2, QueueProcessingType queueProcessingType) {
        return new ThreadPoolExecutor(i, i, 0, TimeUnit.MILLISECONDS, (queueProcessingType == QueueProcessingType.LIFO ? 1 : null) != null ? new LIFOLinkedBlockingDeque() : new LinkedBlockingQueue(), a(i2, "uil-pool-"));
    }

    public static Executor a() {
        return Executors.newCachedThreadPool(a(5, "uil-pool-d-"));
    }

    public static com.nostra13.universalimageloader.a.a.b.a b() {
        return new b();
    }

    public static com.nostra13.universalimageloader.a.a.b a(Context context, com.nostra13.universalimageloader.a.a.b.a aVar, long j, int i) {
        File b = b(context);
        if (j <= 0 && i <= 0) {
            return new com.nostra13.universalimageloader.a.a.a.b(g.a(context), b, aVar);
        }
        com.nostra13.universalimageloader.a.a.b bVar = new com.nostra13.universalimageloader.a.a.a.a.b(g.b(context), aVar, j, i);
        bVar.a(b);
        return bVar;
    }

    private static File b(Context context) {
        File a = g.a(context, false);
        File file = new File(a, "uil-images");
        if (file.exists() || file.mkdir()) {
            return file;
        }
        return a;
    }

    public static c a(int i) {
        if (i == 0) {
            i = (int) (Runtime.getRuntime().maxMemory() / 8);
        }
        return new com.nostra13.universalimageloader.a.b.a.a.b(i);
    }

    public static com.nostra13.universalimageloader.a.b.b.a b(int i) {
        if (i == 0) {
            i = (int) (Runtime.getRuntime().maxMemory() / 8);
        }
        return new com.nostra13.universalimageloader.a.b.b.a.b(i);
    }

    public static ImageDownloader a(Context context) {
        return new com.nostra13.universalimageloader.core.download.a(context);
    }

    public static com.nostra13.universalimageloader.core.a.b a(boolean z) {
        return new com.nostra13.universalimageloader.core.a.a(z);
    }

    public static com.nostra13.universalimageloader.core.b.a c() {
        return new d();
    }

    private static ThreadFactory a(int i, String str) {
        return new a(i, str);
    }
}
