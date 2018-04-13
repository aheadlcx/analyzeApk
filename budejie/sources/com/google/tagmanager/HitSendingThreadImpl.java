package com.google.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

class HitSendingThreadImpl extends Thread implements HitSendingThread {
    private static HitSendingThreadImpl sInstance;
    private volatile boolean mClosed = false;
    private final Context mContext;
    private volatile boolean mDisabled = false;
    private volatile HitStore mUrlStore;
    private final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue();

    static HitSendingThreadImpl getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new HitSendingThreadImpl(context);
        }
        return sInstance;
    }

    private HitSendingThreadImpl(Context context) {
        super("GAThread");
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        start();
    }

    @VisibleForTesting
    HitSendingThreadImpl(Context context, HitStore hitStore) {
        super("GAThread");
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        this.mUrlStore = hitStore;
        start();
    }

    @VisibleForTesting
    HitStore getStore() {
        return this.mUrlStore;
    }

    public void sendHit(String str) {
        sendHit(str, System.currentTimeMillis());
    }

    @VisibleForTesting
    void sendHit(String str, long j) {
        final HitSendingThreadImpl hitSendingThreadImpl = this;
        final long j2 = j;
        final String str2 = str;
        queueToThread(new Runnable() {
            public void run() {
                if (HitSendingThreadImpl.this.mUrlStore == null) {
                    ServiceManagerImpl instance = ServiceManagerImpl.getInstance();
                    instance.initialize(HitSendingThreadImpl.this.mContext, hitSendingThreadImpl);
                    HitSendingThreadImpl.this.mUrlStore = instance.getStore();
                }
                HitSendingThreadImpl.this.mUrlStore.putHit(j2, str2);
            }
        });
    }

    public void queueToThread(Runnable runnable) {
        this.queue.add(runnable);
    }

    @VisibleForTesting
    int getQueueSize() {
        return this.queue.size();
    }

    private String printStackTrace(Throwable th) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        th.printStackTrace(printStream);
        printStream.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }

    public void run() {
        while (!this.mClosed) {
            try {
                Runnable runnable = (Runnable) this.queue.take();
                if (!this.mDisabled) {
                    runnable.run();
                }
            } catch (InterruptedException e) {
                Log.i(e.toString());
            } catch (Throwable th) {
                Log.e("Error on GAThread: " + printStackTrace(th));
                Log.e("Google Analytics is shutting down.");
                this.mDisabled = true;
            }
        }
    }

    @VisibleForTesting
    void close() {
        this.mClosed = true;
        interrupt();
    }

    @VisibleForTesting
    boolean isDisabled() {
        return this.mDisabled;
    }
}
