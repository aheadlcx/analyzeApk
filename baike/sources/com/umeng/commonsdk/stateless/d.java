package com.umeng.commonsdk.stateless;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.proguard.b;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.e;
import java.io.File;

public class d {
    public static final int a = 273;
    private static Context b;
    private static HandlerThread c = null;
    private static Handler d = null;
    private static Object e = new Object();
    private static IntentFilter f;
    private static boolean g = false;
    private static BroadcastReceiver h = new h();

    public d(Context context) {
        synchronized (e) {
            if (context != null) {
                try {
                    b = context.getApplicationContext();
                    if (b != null && c == null) {
                        c = new HandlerThread("SL-NetWorkSender");
                        c.start();
                        if (d == null) {
                            d = new i(this, c.getLooper());
                        }
                        if (DeviceConfig.checkPermission(b, "android.permission.ACCESS_NETWORK_STATE")) {
                            e.a("walle", "[stateless] begin register receiver");
                            if (f == null) {
                                f = new IntentFilter();
                                f.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                                if (h != null) {
                                    e.a("walle", "[stateless] register receiver ok");
                                    b.registerReceiver(h, f);
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                    b.a(context, th);
                }
            }
        }
    }

    public static void a(int i) {
        if (g && d != null) {
            Message obtainMessage = d.obtainMessage();
            obtainMessage.what = i;
            d.sendMessage(obtainMessage);
        }
    }

    public static void b(int i) {
        try {
            if (g && d != null && !d.hasMessages(i)) {
                e.a("walle", "[stateless] sendMsgOnce !!!!");
                Message obtainMessage = d.obtainMessage();
                obtainMessage.what = i;
                d.sendMessage(obtainMessage);
            }
        } catch (Throwable th) {
            b.a(b, th);
        }
    }

    private static void e() {
        if (g && b != null) {
            try {
                File a = f.a(b);
                if (a != null && a.getParentFile() != null && !TextUtils.isEmpty(a.getParentFile().getName())) {
                    e eVar = new e(b);
                    if (eVar != null) {
                        e.a("walle", "[stateless] handleProcessNext, pathUrl is " + new String(Base64.decode(a.getParentFile().getName(), 0)));
                        byte[] bArr = null;
                        try {
                            bArr = f.a(a.getAbsolutePath());
                        } catch (Exception e) {
                        }
                        if (eVar.a(bArr, r3)) {
                            e.a("walle", "[stateless] Send envelope file success, delete it.");
                            File file = new File(a.getAbsolutePath());
                            if (!file.delete()) {
                                e.a("walle", "[stateless] Failed to delete already processed file. We try again after delete failed.");
                                file.delete();
                            }
                        } else {
                            e.a("walle", "[stateless] Send envelope file failed, abandon and wait next trigger!");
                            return;
                        }
                    }
                    b(273);
                }
            } catch (Throwable th) {
                b.a(b, th);
            }
        }
    }

    public static void a() {
        b(512);
    }

    private static void f() {
        if (f != null) {
            if (h != null) {
                if (b != null) {
                    b.unregisterReceiver(h);
                }
                h = null;
            }
            f = null;
        }
        if (c != null) {
            c.quit();
            if (c != null) {
                c = null;
            }
            if (d != null) {
                d = null;
            }
        }
    }
}
