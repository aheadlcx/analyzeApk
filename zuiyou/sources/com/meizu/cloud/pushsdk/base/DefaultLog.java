package com.meizu.cloud.pushsdk.base;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.support.media.ExifInterface;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

class DefaultLog implements ICacheLog {
    private int mCacheCounter = 10;
    private long mCacheDuration = 60;
    private List<LogInfo> mCachedList = Collections.synchronizedList(new ArrayList());
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
    private boolean mDebugMode = false;
    private Handler mDelayHandler = new Handler(Looper.getMainLooper());
    private String mPath = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/pushSdk/defaultLog");
    private String mPid = String.valueOf(Process.myPid());
    private EncryptionWriter mWriter = new EncryptionWriter();

    class LogInfo {
        String header;
        String msg;
        String tag;

        public LogInfo(String str, String str2, String str3) {
            StringBuffer stringBuffer = new StringBuffer(DefaultLog.this.mDateFormat.format(new Date()));
            stringBuffer.append(" ");
            stringBuffer.append(DefaultLog.this.mPid);
            stringBuffer.append("-");
            stringBuffer.append(String.valueOf(Thread.currentThread().getId()));
            stringBuffer.append(" ");
            stringBuffer.append(str);
            stringBuffer.append("/");
            this.header = stringBuffer.toString();
            this.tag = str2;
            this.msg = str3;
        }
    }

    private void startDelayTimer() {
        if (this.mCachedList.size() == 0) {
            this.mDelayHandler.postDelayed(new Runnable() {
                public void run() {
                    DefaultLog.this.flush(true);
                }
            }, this.mCacheDuration * 1000);
        }
    }

    private void checkLogCount() {
        if (this.mCachedList.size() == this.mCacheCounter) {
            flush(true);
        }
    }

    public void d(String str, String str2) {
        if (this.mDebugMode) {
            Log.d(str, str2);
        }
        synchronized (this.mCachedList) {
            startDelayTimer();
            addLogInfo(new LogInfo("D", str, str2));
            checkLogCount();
        }
    }

    public void i(String str, String str2) {
        if (this.mDebugMode) {
            Log.i(str, str2);
        }
        synchronized (this.mCachedList) {
            startDelayTimer();
            addLogInfo(new LogInfo("I", str, str2));
            checkLogCount();
        }
    }

    public void w(String str, String str2) {
        if (this.mDebugMode) {
            Log.w(str, str2);
        }
        synchronized (this.mCachedList) {
            startDelayTimer();
            addLogInfo(new LogInfo(ExifInterface.LONGITUDE_WEST, str, str2));
            checkLogCount();
        }
    }

    public void e(String str, String str2) {
        if (this.mDebugMode) {
            Log.e(str, str2);
        }
        synchronized (this.mCachedList) {
            startDelayTimer();
            addLogInfo(new LogInfo(ExifInterface.LONGITUDE_EAST, str, str2));
            checkLogCount();
        }
    }

    public void e(String str, String str2, Throwable th) {
        if (this.mDebugMode) {
            Log.e(str, str2, th);
        }
        synchronized (this.mCachedList) {
            startDelayTimer();
            addLogInfo(new LogInfo(ExifInterface.LONGITUDE_EAST, str, str2 + "\n" + Log.getStackTraceString(th)));
            checkLogCount();
        }
    }

    public void setCacheDuration(long j) {
        this.mCacheDuration = j;
    }

    public void setCacheCount(int i) {
        this.mCacheCounter = i;
    }

    public void setFilePath(String str) {
        this.mPath = str;
    }

    public void flush(boolean z) {
        Runnable anonymousClass2 = new Runnable() {
            public void run() {
                List<LogInfo> arrayList = new ArrayList();
                synchronized (DefaultLog.this.mCachedList) {
                    try {
                        DefaultLog.this.mDelayHandler.removeCallbacksAndMessages(null);
                        arrayList.addAll(DefaultLog.this.mCachedList);
                        DefaultLog.this.mCachedList.clear();
                    } catch (Throwable th) {
                        while (true) {
                            throw th;
                        }
                    }
                }
                try {
                    DefaultLog.this.mWriter.open(DefaultLog.this.mPath);
                    for (LogInfo logInfo : arrayList) {
                        DefaultLog.this.mWriter.write(logInfo.header, logInfo.tag, logInfo.msg);
                    }
                    try {
                        DefaultLog.this.mWriter.close();
                    } catch (Exception e) {
                    }
                } catch (Exception e2) {
                    try {
                        DefaultLog.this.mWriter.close();
                    } catch (Exception e3) {
                    }
                } catch (Throwable th2) {
                    try {
                        DefaultLog.this.mWriter.close();
                    } catch (Exception e4) {
                    }
                    throw th2;
                }
            }
        };
        if (z) {
            ExecutorProxy.get().execute(anonymousClass2);
        } else {
            anonymousClass2.run();
        }
    }

    public void setDebugMode(boolean z) {
        this.mDebugMode = z;
    }

    public boolean isDebugMode() {
        return this.mDebugMode;
    }

    private void addLogInfo(LogInfo logInfo) {
        try {
            this.mCachedList.add(logInfo);
        } catch (Exception e) {
            Log.e("Logger", "add logInfo error " + e.getMessage());
        }
    }
}
