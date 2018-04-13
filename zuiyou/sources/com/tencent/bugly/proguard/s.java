package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.text.TextUtils;
import com.tencent.bugly.beta.download.DownloadTask;
import com.tencent.bugly.beta.download.b;
import com.tencent.bugly.beta.global.e;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class s implements b {
    public static s a = new s();
    public ConcurrentHashMap<String, DownloadTask> b = new ConcurrentHashMap(3);
    private ScheduledExecutorService c = null;

    public s() {
        try {
            this.c = Executors.newScheduledThreadPool(3, new s$1(this));
            if (this.c.isShutdown()) {
                throw new IllegalArgumentException("ScheduledExecutorService is not available!");
            }
        } catch (Throwable e) {
            an.a(e);
        }
    }

    public DownloadTask a(String str, String str2, String str3, String str4) {
        DownloadTask downloadTask = null;
        if (TextUtils.isEmpty(str)) {
            an.e("downloadUrl is null!", new Object[0]);
            return null;
        } else if (TextUtils.isEmpty(str2)) {
            an.e("saveDir is null!", new Object[0]);
            return null;
        } else if (this.b.get(str) != null) {
            return (DownloadTask) this.b.get(str);
        } else {
            ContentValues a = p.a.a(str);
            if (!(a == null || a.get("_dUrl") == null || a.getAsString("_sFile") == null || a.getAsLong("_sLen") == null || a.getAsLong("_tLen") == null || a.getAsString("_MD5") == null)) {
                DownloadTask tVar = new t((String) a.get("_dUrl"), a.getAsString("_sFile"), a.getAsLong("_sLen").longValue(), a.getAsLong("_tLen").longValue(), a.getAsString("_MD5"));
                if (a.getAsLong("_DLTIME") != null) {
                    tVar.k = a.getAsLong("_DLTIME").longValue();
                }
                downloadTask = tVar;
            }
            if (downloadTask == null) {
                downloadTask = new t(str, str2, str3, str4);
            }
            downloadTask.setDownloadType(e.E.ac);
            return downloadTask;
        }
    }

    public synchronized boolean a(Runnable runnable) {
        boolean z = false;
        synchronized (this) {
            if (this.c == null || this.c.isShutdown()) {
                an.d("async handler was closed , should not post task!", new Object[0]);
            } else if (runnable == null) {
                an.d("async task = null", new Object[0]);
            } else {
                an.d("task start %s", runnable.getClass().getName());
                this.c.execute(runnable);
                z = true;
            }
        }
        return z;
    }
}
