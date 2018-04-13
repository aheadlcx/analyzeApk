package com.meizu.cloud.pushsdk.base;

import android.content.Context;
import android.os.Environment;

public class Logger extends Proxy<ICacheLog> implements ICacheLog {
    private static Logger sInstance;
    private boolean mInitialized = false;

    protected Logger(ICacheLog iCacheLog) {
        super(iCacheLog);
    }

    public static Logger get() {
        if (sInstance == null) {
            synchronized (Logger.class) {
                if (sInstance == null) {
                    sInstance = new Logger(new DefaultLog());
                }
            }
        }
        return sInstance;
    }

    public void init(Context context) {
        if (!this.mInitialized) {
            this.mInitialized = true;
            if ((context.getApplicationInfo().flags & 2) != 0) {
                setDebugMode(true);
            } else {
                setDebugMode(false);
            }
            setFilePath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/pushSdk/" + context.getPackageName());
        }
    }

    public void d(String str, String str2) {
        ((ICacheLog) getImpl()).d(str, str2);
    }

    public void i(String str, String str2) {
        ((ICacheLog) getImpl()).i(str, str2);
    }

    public void w(String str, String str2) {
        ((ICacheLog) getImpl()).w(str, str2);
    }

    public void e(String str, String str2) {
        ((ICacheLog) getImpl()).e(str, str2);
    }

    public void e(String str, String str2, Throwable th) {
        ((ICacheLog) getImpl()).e(str, str2, th);
    }

    public void setCacheDuration(long j) {
        ((ICacheLog) getImpl()).setCacheDuration(j);
    }

    public void setCacheCount(int i) {
        ((ICacheLog) getImpl()).setCacheCount(i);
    }

    public void setFilePath(String str) {
        ((ICacheLog) getImpl()).setFilePath(str);
    }

    public void flush(boolean z) {
        ((ICacheLog) getImpl()).flush(z);
    }

    public void setDebugMode(boolean z) {
        ((ICacheLog) getImpl()).setDebugMode(z);
    }

    public boolean isDebugMode() {
        return ((ICacheLog) getImpl()).isDebugMode();
    }
}
