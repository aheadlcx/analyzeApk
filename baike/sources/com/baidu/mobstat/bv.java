package com.baidu.mobstat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

class bv {
    private static final bv a = new bv();
    private Context b;
    private volatile boolean c = false;
    private volatile boolean d = false;
    private volatile boolean e = false;
    private HandlerThread f;
    private Handler g;

    public static bv a() {
        return a;
    }

    private bv() {
    }

    public void a(Context context, boolean z) {
        try {
            a().b(context.getApplicationContext());
        } catch (Throwable th) {
        }
        b(context.getApplicationContext(), z);
    }

    public void a(Context context) {
        a(context, false);
    }

    public void b(Context context) {
        if (!this.e && context != null) {
            if (this.f == null || !this.f.isAlive()) {
                this.f = new HandlerThread("dataAnalyzeThread");
                this.f.start();
                Looper looper = this.f.getLooper();
                if (looper != null) {
                    this.g = new Handler(looper);
                }
            }
            if (this.g != null) {
                this.g.postDelayed(new bw(this, context), Config.BPLUS_DELAY_TIME);
                this.e = true;
            }
        }
    }

    private void b(Context context, boolean z) {
        if (context != null && !this.c) {
            this.b = context.getApplicationContext();
            a(z);
            this.c = true;
        }
    }

    public synchronized boolean b() {
        return this.c;
    }

    public synchronized boolean c() {
        return this.d;
    }

    private synchronized void a(boolean z) {
        bx bxVar = new bx(this, z);
        bxVar.setPriority(10);
        bxVar.start();
    }

    public void d() {
        if (!this.d) {
            synchronized (this) {
                while (!this.d) {
                    try {
                        wait(50);
                    } catch (InterruptedException e) {
                        db.b(e.getMessage());
                    }
                }
            }
        }
    }

    public void a(Context context, boolean z, boolean z2) {
        if (!this.d) {
            PrefOperate.loadMetaDataConfig(context);
            DataCore.instance().loadStatData(context);
            DataCore.instance().loadLastSession(context);
            DataCore.instance().installHeader(context);
            if (z) {
                DataCore.instance().saveLogDataToSend(context, true, z2);
            }
            this.d = true;
        }
    }
}
