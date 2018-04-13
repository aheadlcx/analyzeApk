package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

public class HttpManager implements Transport {
    public static final String TAG = "HttpManager";
    private static HttpManager b = null;
    private static final ThreadFactory i = new h();
    Context a;
    private ThreadPoolExecutor c;
    private AndroidHttpClient d;
    private long e;
    private long f;
    private long g;
    private int h;

    public HttpManager(Context context) {
        this.a = context;
        a();
    }

    private static final synchronized HttpManager a(Context context) {
        HttpManager httpManager;
        synchronized (HttpManager.class) {
            if (b != null) {
                httpManager = b;
            } else {
                httpManager = new HttpManager(context);
                b = httpManager;
            }
        }
        return httpManager;
    }

    private FutureTask<Response> a(HttpWorker httpWorker) {
        return new g(this, httpWorker, httpWorker);
    }

    private void a() {
        this.d = AndroidHttpClient.newInstance("android");
        this.c = new ThreadPoolExecutor(10, 11, 3, TimeUnit.SECONDS, new ArrayBlockingQueue(20), i, new CallerRunsPolicy());
        try {
            this.c.allowCoreThreadTimeOut(true);
        } catch (Exception e) {
        }
        CookieSyncManager.createInstance(this.a);
        CookieManager.getInstance().setAcceptCookie(true);
    }

    public static final HttpManager getInstance(Context context) {
        return b != null ? b : a(context);
    }

    protected HttpWorker a(HttpUrlRequest httpUrlRequest) {
        return new HttpWorker(this, httpUrlRequest);
    }

    public void addConnectTime(long j) {
        this.f += j;
        this.h++;
    }

    public void addDataSize(long j) {
        this.e += j;
    }

    public void addSocketTime(long j) {
        this.g += j;
    }

    public void close() {
        if (this.c != null) {
            this.c.shutdown();
            this.c = null;
        }
        if (this.d != null) {
            this.d.close();
        }
        this.d = null;
    }

    public String dumpPerf() {
        return String.format(new StringBuilder(TAG).append(hashCode()).append(": Active Task = %d, Completed Task = %d, All Task = %d,Avarage Speed = %d KB/S, Connetct Time = %d ms, All data size = %d bytes, All enqueueConnect time = %d ms, All socket time = %d ms, All request times = %d times").toString(), new Object[]{Integer.valueOf(this.c.getActiveCount()), Long.valueOf(this.c.getCompletedTaskCount()), Long.valueOf(this.c.getTaskCount()), Long.valueOf(getAverageSpeed()), Long.valueOf(getAverageConnectTime()), Long.valueOf(this.e), Long.valueOf(this.f), Long.valueOf(this.g), Integer.valueOf(this.h)});
    }

    public Future<Response> execute(Request request) {
        if (request instanceof HttpUrlRequest) {
            if (MiscUtils.isDebugger(this.a)) {
                dumpPerf();
            }
            Object a = a(a((HttpUrlRequest) request));
            this.c.execute(a);
            return a;
        }
        throw new RuntimeException("request send error.");
    }

    public long getAverageConnectTime() {
        return this.h == 0 ? 0 : this.f / ((long) this.h);
    }

    public long getAverageSpeed() {
        return this.g == 0 ? 0 : ((this.e * 1000) / this.g) >> 10;
    }

    public AndroidHttpClient getHttpClient() {
        return this.d;
    }
}
