package com.qiushibaike.statsdk;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerThread;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LogServerReporter {
    private static HandlerThread a = new HandlerThread(LogServerReporter.class.getSimpleName());
    private static Handler b;
    private static LogServerReporter e;
    private Timer c;
    private WeakReference<Context> d;
    private boolean f;
    private ReportStrategyEnum g;
    private int h;
    private int i;
    private String j;

    private class a implements Runnable {
        WeakReference<Context> a;
        final /* synthetic */ LogServerReporter b;

        public a(LogServerReporter logServerReporter, Context context) {
            this.b = logServerReporter;
            this.a = new WeakReference(context);
        }

        public void run() {
            Context context = (Context) this.a.get();
            if (context != null) {
                this.b.c(StoreTool.getInstance().getReportInterval(context));
                this.b.b(StoreTool.getInstance().getReportInterval(context));
                this.b.a(StoreTool.getInstance().getReportStragety(context));
                this.b.a(StoreTool.getInstance().isWifiOnly(context));
                LogServerReporter.b.postDelayed(new c(this.b, context), (long) this.b.getReportDelay());
            }
        }
    }

    private class b extends TimerTask {
        final /* synthetic */ LogServerReporter a;

        private b(LogServerReporter logServerReporter) {
            this.a = logServerReporter;
        }

        public void run() {
            Context context = (Context) this.a.d.get();
            if (context != null) {
                LogServerReporter.b.post(new b(this, context));
            }
        }
    }

    private final class c implements Runnable {
        WeakReference<Context> a;
        final /* synthetic */ LogServerReporter b;

        public c(LogServerReporter logServerReporter, Context context) {
            this.b = logServerReporter;
            this.a = new WeakReference(context);
        }

        public void run() {
            Context context = (Context) this.a.get();
            if (context != null) {
                CacheLoader.getInstance().checkLoadFinished(context);
                if (this.b.a(context, this.b.isWifiOnly())) {
                    DataObjConstructor.getInstance().flush(context);
                }
                this.b.b(context);
            }
        }
    }

    private LogServerReporter() {
        a.start();
        b = new Handler(a.getLooper());
    }

    public static LogServerReporter getInstance() {
        LogServerReporter logServerReporter;
        synchronized (LogServerReporter.class) {
            if (e == null) {
                e = new LogServerReporter();
            }
            logServerReporter = e;
        }
        return logServerReporter;
    }

    private void a(Context context) {
        if (context == null) {
            L.d(L.TAG, "param context is null.");
        } else if (this.d == null && context != null) {
            this.d = new WeakReference(context);
        }
    }

    public void init(Context context, String str) {
        if (this.j == null) {
            this.j = str;
            a(context);
            if (this.d.get() != null) {
                b.post(new a(this, (Context) this.d.get()));
            }
        }
    }

    public void setReportStrategy(ReportStrategyEnum reportStrategyEnum, Context context) {
        if (reportStrategyEnum != this.g) {
            a(reportStrategyEnum);
            StoreTool.getInstance().setReportStragety(context, reportStrategyEnum.ordinal());
        }
    }

    private void a(ReportStrategyEnum reportStrategyEnum) {
        this.g = reportStrategyEnum;
    }

    private void a(int i) {
        try {
            a(ReportStrategyEnum.values()[i]);
        } catch (Throwable e) {
            L.d(e);
        }
    }

    public void setReportInterval(Context context, int i) {
        if (this.h != i) {
            b(i);
            StoreTool.getInstance().setReportInterval(context, i);
        }
    }

    private void b(int i) {
        this.h = i;
    }

    public int getReportInterval() {
        return this.h;
    }

    public void setReportDelay(Context context, int i) {
        if (i != this.i) {
            c(i);
            StoreTool.getInstance().setReportDelay(context, i);
        }
    }

    private void c(int i) {
        this.i = i;
    }

    public int getReportDelay() {
        return this.i;
    }

    public boolean isWifiOnly() {
        return this.f;
    }

    public void setWifiOnly(Context context, boolean z) {
        if (this.f != z) {
            a(z);
            StoreTool.getInstance().setWifiOnly(context, z);
        }
    }

    private void a(boolean z) {
        this.f = z;
    }

    public void setLastSendTime(Context context) {
        StoreTool.getInstance().setLastReportTime(context, new Date().getTime());
    }

    private void b(Context context) {
        schedule(context, (long) (getReportDelay() * 1000), (long) (getReportInterval() * 1000));
    }

    public void schedule(Context context, long j, long j2) {
        if (this.g == ReportStrategyEnum.SET_TIME_INTERVAL) {
            if (this.c == null) {
                this.c = new Timer();
            } else {
                this.c.cancel();
            }
            this.c.schedule(new b(), j, j2);
        }
    }

    public void forceReportLog(Context context) {
        b.postDelayed(new a(this, context), 100);
    }

    boolean a(Context context, boolean z) {
        if (z) {
            try {
                if (!((WifiManager) context.getSystemService("wifi")).isWifiEnabled()) {
                    L.d(L.TAG, "report log failed because of wifi not enble.");
                    return false;
                }
            } catch (Throwable e) {
                L.d(L.TAG, e);
            }
        }
        return DataObjConstructor.getInstance().sendReportData(context, this.j);
    }
}
