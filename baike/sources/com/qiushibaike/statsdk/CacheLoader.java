package com.qiushibaike.statsdk;

import android.content.Context;
import android.os.Process;
import java.lang.ref.WeakReference;

public class CacheLoader {
    private static CacheLoader b;
    a a;

    class a {
        boolean a = false;
        boolean b = false;
        b c;
        final /* synthetic */ CacheLoader d;

        public a(CacheLoader cacheLoader, Context context) {
            this.d = cacheLoader;
            this.c = new b(cacheLoader, context);
        }
    }

    class b extends Thread {
        WeakReference<Context> a;
        final /* synthetic */ CacheLoader b;

        public b(CacheLoader cacheLoader, Context context) {
            this.b = cacheLoader;
            this.a = new WeakReference(context);
        }

        public void run() {
            Process.setThreadPriority(19);
            Context context = (Context) this.a.get();
            if (context != null) {
                DataObjConstructor.getInstance().loadStatData(context);
            }
            synchronized (this) {
                try {
                    L.d("cache loaded.");
                    this.b.setFinished();
                    notifyAll();
                } catch (Throwable e) {
                    L.d("CacheLoaderThread", e);
                }
            }
            this.a.clear();
            this.a = null;
        }
    }

    private CacheLoader() {
    }

    public static CacheLoader getInstance() {
        CacheLoader cacheLoader;
        synchronized (CacheLoader.class) {
            if (b == null) {
                b = new CacheLoader();
            }
            cacheLoader = b;
        }
        return cacheLoader;
    }

    public void checkLoadFinished(Context context) {
        if (context != null) {
            a(context);
            if (this.a != null && !isFinished()) {
                synchronized (this.a.c) {
                    try {
                        this.a.c.wait();
                    } catch (Throwable e) {
                        L.d("check load cache finished failed ", e);
                    }
                }
            }
        }
    }

    public void checkLoadStarted(Context context) {
        if (context != null) {
            a(context);
            if (this.a != null && !isStarted()) {
                L.d("cache loading.");
                setStarted();
                try {
                    this.a.c.start();
                } catch (Throwable e) {
                    L.d("start load cache thread ", e);
                }
            }
        }
    }

    public void setFinished() {
        this.a.b = true;
    }

    public boolean isFinished() {
        return this.a.b;
    }

    public void setStarted() {
        this.a.a = true;
    }

    public boolean isStarted() {
        return this.a.a;
    }

    private void a(Context context) {
        if (this.a == null) {
            this.a = new a(this, context);
        }
    }
}
