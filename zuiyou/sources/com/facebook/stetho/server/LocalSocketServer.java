package com.facebook.stetho.server;

import android.net.LocalServerSocket;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.BindException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;

public class LocalSocketServer {
    private static final int MAX_BIND_RETRIES = 2;
    private static final int TIME_BETWEEN_BIND_RETRIES_MS = 1000;
    private static final String WORKER_THREAD_NAME_PREFIX = "StethoWorker";
    private final String mAddress;
    private final String mFriendlyName;
    private Thread mListenerThread;
    private LocalServerSocket mServerSocket;
    private final SocketHandler mSocketHandler;
    private boolean mStopped;
    private final AtomicInteger mThreadId = new AtomicInteger();

    public LocalSocketServer(String str, String str2, SocketHandler socketHandler) {
        this.mFriendlyName = (String) Util.throwIfNull(str);
        this.mAddress = (String) Util.throwIfNull(str2);
        this.mSocketHandler = socketHandler;
    }

    public String getName() {
        return this.mFriendlyName;
    }

    public void run() throws IOException {
        synchronized (this) {
            if (this.mStopped) {
                return;
            }
            this.mListenerThread = Thread.currentThread();
            listenOnAddress(this.mAddress);
        }
    }

    private void listenOnAddress(String str) throws IOException {
        this.mServerSocket = bindToSocket(str);
        LogUtil.i("Listening on @" + str);
        while (!Thread.interrupted()) {
            try {
                Thread localSocketServer$WorkerThread = new LocalSocketServer$WorkerThread(this.mServerSocket.accept(), this.mSocketHandler);
                localSocketServer$WorkerThread.setName("StethoWorker-" + this.mFriendlyName + "-" + this.mThreadId.incrementAndGet());
                localSocketServer$WorkerThread.setDaemon(true);
                localSocketServer$WorkerThread.start();
            } catch (Throwable e) {
                if (Thread.interrupted()) {
                    break;
                }
                LogUtil.w(e, "I/O error");
            } catch (InterruptedIOException e2) {
            } catch (Throwable e3) {
                LogUtil.w(e3, "I/O error initialising connection thread");
            }
        }
        LogUtil.i("Server shutdown on @" + str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void stop() {
        /*
        r1 = this;
        monitor-enter(r1);
        r0 = 1;
        r1.mStopped = r0;	 Catch:{ all -> 0x001c }
        r0 = r1.mListenerThread;	 Catch:{ all -> 0x001c }
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
    L_0x0009:
        return;
    L_0x000a:
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
        r0 = r1.mListenerThread;
        r0.interrupt();
        r0 = r1.mServerSocket;	 Catch:{ IOException -> 0x001a }
        if (r0 == 0) goto L_0x0009;
    L_0x0014:
        r0 = r1.mServerSocket;	 Catch:{ IOException -> 0x001a }
        r0.close();	 Catch:{ IOException -> 0x001a }
        goto L_0x0009;
    L_0x001a:
        r0 = move-exception;
        goto L_0x0009;
    L_0x001c:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.stetho.server.LocalSocketServer.stop():void");
    }

    @Nonnull
    private static LocalServerSocket bindToSocket(String str) throws IOException {
        Throwable e;
        Throwable th = null;
        int i = 2;
        while (true) {
            try {
                break;
            } catch (BindException e2) {
                e = e2;
                LogUtil.w(e, "Binding error, sleep 1000 ms...");
                if (th != null) {
                    e = th;
                }
                Util.sleepUninterruptibly(1000);
                r1 = i - 1;
                if (i <= 0) {
                    throw e;
                }
                int i2;
                i = i2;
                th = e;
            }
        }
        if (LogUtil.isLoggable(3)) {
            LogUtil.d("Trying to bind to @" + str);
        }
        return new LocalServerSocket(str);
    }
}
