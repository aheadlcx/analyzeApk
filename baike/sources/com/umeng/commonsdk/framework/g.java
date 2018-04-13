package com.umeng.commonsdk.framework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.FileObserver;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.umeng.commonsdk.proguard.b;
import com.umeng.commonsdk.statistics.c;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.e;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.noise.ImLatent;
import java.io.File;

class g {
    private static HandlerThread a = null;
    private static Handler b = null;
    private static Handler c = null;
    private static a d;
    private static ConnectivityManager e;
    private static NetworkInfo f;
    private static IntentFilter g = null;
    private static StatTracer h = null;
    private static ImLatent i = null;
    private static boolean j = false;
    private static BroadcastReceiver k = new h();

    static class a extends FileObserver {
        public a(String str) {
            super(str);
        }

        public void onEvent(int i, String str) {
            switch (i & 8) {
                case 8:
                    e.b("--->>> envelope file created >>> " + str);
                    g.c(273);
                    return;
                default:
                    return;
            }
        }
    }

    public g(Context context, Handler handler) {
        c = handler;
        try {
            if (a == null) {
                a = new HandlerThread("NetWorkSender");
                a.start();
                if (d == null) {
                    d = new a(b.h(context));
                    d.startWatching();
                    e.b("--->>> FileMonitor has already started!");
                }
                Context a = c.a();
                if (DeviceConfig.checkPermission(a, "android.permission.ACCESS_NETWORK_STATE") && g == null) {
                    g = new IntentFilter();
                    g.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                    if (k != null) {
                        a.registerReceiver(k, g);
                    }
                }
                if (h == null) {
                    h = StatTracer.getInstance(context);
                    i = ImLatent.getService(context, h);
                }
                if (b == null) {
                    b = new k(this, a.getLooper());
                }
            }
        } catch (Throwable th) {
            b.a(context, th);
        }
    }

    private static void h() {
        if (a != null) {
            a = null;
        }
        if (b != null) {
            b = null;
        }
        if (c != null) {
            c = null;
        }
        if (i != null) {
            i = null;
        }
        if (h != null) {
            h = null;
        }
    }

    private static void i() {
        if (d != null) {
            d.stopWatching();
            d = null;
        }
        if (g != null) {
            if (k != null) {
                c.a().unregisterReceiver(k);
                k = null;
            }
            g = null;
        }
        e.b("--->>> handleQuit: Quit sender thread.");
        if (a != null) {
            a.quit();
            h();
        }
    }

    public static void a() {
        c(512);
    }

    private static void b(int i) {
        if (j && b != null && !b.hasMessages(i)) {
            Message obtainMessage = b.obtainMessage();
            obtainMessage.what = i;
            b.sendMessage(obtainMessage);
        }
    }

    private static void c(int i) {
        if (j && b != null) {
            Message obtainMessage = b.obtainMessage();
            obtainMessage.what = i;
            b.sendMessage(obtainMessage);
        }
    }

    public static void b() {
        b(273);
    }

    private static void a(int i, int i2) {
        if (j && c != null) {
            c.removeMessages(i);
            Message obtainMessage = c.obtainMessage();
            obtainMessage.what = i;
            c.sendMessageDelayed(obtainMessage, (long) i2);
        }
    }

    public static void c() {
        a(769, 3000);
    }

    private static void j() {
        e.b("--->>> handleProcessNext: Enter...");
        if (j) {
            Context a = c.a();
            try {
                if (b.c(a) > 0) {
                    e.b("--->>> The envelope file exists.");
                    if (b.c(a) > 100) {
                        e.b("--->>> Number of envelope files is greater than 100, remove old files first.");
                        b.d(a);
                    }
                    File e = b.e(a);
                    if (e != null) {
                        e.b("--->>> Ready to send envelope file [" + e.getPath() + "].");
                        c cVar = new c(a);
                        if (i != null && i.isLatentActivite()) {
                            i.latentDeactivite();
                            long delayTime = i.getDelayTime();
                            if (delayTime > 0) {
                                e.c("start lacency policy, wait [" + delayTime + "] milliseconds .");
                                Thread.sleep(delayTime * 1000);
                            }
                        }
                        if (cVar.a(e)) {
                            e.b("--->>> Send envelope file success, delete it.");
                            if (!b.a(e)) {
                                e.b("--->>> Failed to delete already processed file. We try again after delete failed.");
                                b.a(e);
                            }
                            c(273);
                            return;
                        }
                        e.b("--->>> Send envelope file failed, abandon and wait next trigger!");
                        return;
                    }
                }
                e.b("--->>> The envelope file not exists, start auto process for module cache data.");
                c();
            } catch (Throwable th) {
                b.a(a, th);
            }
        }
    }
}
