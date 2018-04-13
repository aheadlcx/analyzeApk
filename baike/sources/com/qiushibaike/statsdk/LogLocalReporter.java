package com.qiushibaike.statsdk;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import java.lang.ref.WeakReference;
import java.util.Date;

public class LogLocalReporter {
    private static HandlerThread a;
    private static Handler b;
    private static LogLocalReporter c;

    private class a implements Runnable {
        String a;
        String b;
        int c;
        long d;
        long e;
        String f;
        String g;
        String h;
        WeakReference<Context> i;
        final /* synthetic */ LogLocalReporter j;

        public a(LogLocalReporter logLocalReporter, Context context, String str, String str2, int i, long j, long j2) {
            this(logLocalReporter, context, str, str2, i, j, j2, null, null, null);
        }

        public a(LogLocalReporter logLocalReporter, Context context, String str, String str2, int i, long j, long j2, String str3, String str4, String str5) {
            this.j = logLocalReporter;
            this.c = i;
            this.e = j2;
            this.a = str;
            this.b = str2;
            this.d = j;
            this.f = str3;
            this.g = str4;
            this.h = str5;
            this.i = new WeakReference(context);
        }

        public void run() {
            CacheLoader.getInstance().checkLoadFinished((Context) this.i.get());
            DataObjConstructor.getInstance().putEvent(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
            DataObjConstructor.getInstance().flush((Context) this.i.get());
        }
    }

    private LogLocalReporter() {
        a = new HandlerThread(L.TAG);
        a.setPriority(10);
        a.start();
        b = new Handler(a.getLooper());
    }

    public static LogLocalReporter getInstance() {
        LogLocalReporter logLocalReporter;
        synchronized (LogLocalReporter.class) {
            if (c == null) {
                c = new LogLocalReporter();
            }
            logLocalReporter = c;
        }
        return logLocalReporter;
    }

    public void putEvent(Context context, String str, String str2, int i) {
        b.post(new a(this, context, str, str2, i, new Date().getTime(), 0));
    }

    public void putEventWithExtra(Context context, String str, String str2, int i, String str3, String str4, String str5) {
        long j = (a(str3) && a(str4) && a(str5)) ? 0 : 1;
        b.post(new a(this, context, str, str2, i, new Date().getTime(), j, str3, str4, str5));
    }

    static boolean a(String str) {
        return str == null || "".equals(str);
    }

    public void putEventDuration(Context context, String str, String str2, long j) {
        b.post(new a(this, context, str, str2, 0, new Date().getTime(), j));
    }

    public void putEventDurationWithExtra(Context context, String str, String str2, long j, String str3, String str4, String str5) {
        b.post(new a(this, context, str, str2, 0, new Date().getTime(), j, str3, str4, str5));
    }
}
