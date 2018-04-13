package com.umeng.update.net;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.umeng.update.UpdateConfig;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import u.upd.a;
import u.upd.i;
import u.upd.j;
import u.upd.k;
import u.upd.m;

class c {
    static final int a = 0;
    static final int b = 1;
    static final int c = 1;
    static final int d = 2;
    private static final String e = c.class.getName();
    private SparseArray<b> f;
    private Map<a, Messenger> g;
    private e h;

    static class b {
        b a;
        c$a b;
        int c;
        int d;
        a e;
        long[] f = new long[3];

        public b(a aVar, int i) {
            this.c = i;
            this.e = aVar;
        }

        public void a(SparseArray<b> sparseArray) {
            sparseArray.put(this.c, this);
        }

        public void b(SparseArray<b> sparseArray) {
            if (sparseArray.indexOfKey(this.c) >= 0) {
                sparseArray.remove(this.c);
            }
        }
    }

    public c(SparseArray<b> sparseArray, Map<a, Messenger> map, e eVar) {
        this.f = sparseArray;
        this.g = map;
        this.h = eVar;
    }

    int a(a aVar) {
        return Math.abs((int) (((long) ((aVar.b.hashCode() >> 2) + (aVar.c.hashCode() >> 3))) + System.currentTimeMillis()));
    }

    c$a a(Context context, a aVar, int i, int i2) {
        int dimensionPixelSize;
        int dimensionPixelSize2;
        Field declaredField;
        Context applicationContext = context.getApplicationContext();
        c$a c_a = new c$a(applicationContext);
        c_a.d(applicationContext.getString(k.j(applicationContext))).a(17301633).a(PendingIntent.getActivity(applicationContext, 0, new Intent(), 134217728)).a(System.currentTimeMillis());
        RemoteViews remoteViews = new RemoteViews(applicationContext.getPackageName(), j.a(applicationContext));
        if (VERSION.SDK_INT >= 14) {
            dimensionPixelSize = applicationContext.getResources().getDimensionPixelSize(17104901);
            dimensionPixelSize2 = applicationContext.getResources().getDimensionPixelSize(17104902);
            remoteViews.setInt(i.b(applicationContext), "setWidth", dimensionPixelSize);
            remoteViews.setInt(i.b(applicationContext), "setHeight", dimensionPixelSize2);
            try {
                declaredField = Class.forName("com.android.internal.R$drawable").getDeclaredField("notify_panel_notification_icon_bg_tile");
                declaredField.setAccessible(true);
                remoteViews.setInt(i.b(applicationContext), "setBackgroundResource", declaredField.getInt(null));
            } catch (Exception e) {
                u.upd.b.a(e, "No notification icon background found:", e);
            }
        } else {
            try {
                declaredField = Class.forName("com.android.internal.R$drawable").getDeclaredField("status_bar_notification_icon_bg");
                declaredField.setAccessible(true);
                remoteViews.setInt(i.b(applicationContext), "setBackgroundResource", declaredField.getInt(null));
            } catch (Exception e2) {
                try {
                    Class cls = Class.forName("com.android.internal.R$dimen");
                    Field declaredField2 = cls.getDeclaredField("status_bar_edge_ignore");
                    declaredField2.setAccessible(true);
                    dimensionPixelSize2 = applicationContext.getResources().getDimensionPixelSize(declaredField2.getInt(null)) + 0;
                    declaredField = cls.getDeclaredField("status_bar_height");
                    declaredField.setAccessible(true);
                    dimensionPixelSize = declaredField.getInt(null);
                    remoteViews.setInt(i.b(applicationContext), "setWidth", applicationContext.getResources().getDimensionPixelSize(dimensionPixelSize) + (dimensionPixelSize2 + applicationContext.getResources().getDimensionPixelSize(dimensionPixelSize)));
                } catch (Exception e3) {
                    u.upd.b.a(e, "No notification size found:", e3);
                }
            }
        }
        c_a.a(remoteViews);
        c_a.b(new StringBuilder(String.valueOf(applicationContext.getResources().getString(k.g(applicationContext)))).append(aVar.b).toString()).a(new StringBuilder(String.valueOf(i2)).append("%").toString()).a(100, i2, false);
        if (aVar.g) {
            c_a.b(remoteViews);
            c_a.e();
            PendingIntent b = f.b(applicationContext, f.a(i, f.b));
            PendingIntent b2 = f.b(applicationContext, f.a(i, f.c));
            a(applicationContext, c_a, i, 2);
            c_a.a(b, b2).c().a(true).b(false);
        } else {
            c_a.a().a(true).b(false);
        }
        return c_a;
    }

    void a(Context context, c$a c_a, int i, int i2) {
        if (VERSION.SDK_INT >= 16) {
            PendingIntent b = f.b(context, f.a(i, f.b));
            PendingIntent b2 = f.b(context, f.a(i, f.c));
            switch (i2) {
                case 1:
                    c_a.a(17301540, context.getResources().getString(k.e(context)), b);
                    break;
                case 2:
                    c_a.a(17301539, context.getResources().getString(k.d(context)), b);
                    break;
            }
            c_a.a(17301560, context.getResources().getString(k.f(context)), b2);
        }
    }

    boolean a(Context context) {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        String packageName = context.getPackageName();
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    boolean a(a aVar, boolean z, Messenger messenger) {
        if (z) {
            int nextInt = new Random().nextInt(1000);
            if (this.g != null) {
                for (a aVar2 : this.g.keySet()) {
                    u.upd.b.c(e, "_" + nextInt + " downling  " + aVar2.b + "   " + aVar2.c);
                }
            } else {
                u.upd.b.c(e, "_" + nextInt + "downling  null");
            }
        }
        if (this.g == null) {
            return false;
        }
        for (a aVar22 : this.g.keySet()) {
            if (aVar.e != null && aVar.e.equals(aVar22.e)) {
                this.g.put(aVar22, messenger);
                return true;
            } else if (aVar22.c.equals(aVar.c)) {
                this.g.put(aVar22, messenger);
                return true;
            }
        }
        return false;
    }

    int b(a aVar) {
        for (int i = 0; i < this.f.size(); i++) {
            int keyAt = this.f.keyAt(i);
            if (aVar.e != null && aVar.e.equals(((b) this.f.get(keyAt)).e.e)) {
                return ((b) this.f.get(keyAt)).c;
            }
            if (((b) this.f.get(keyAt)).e.c.equals(aVar.c)) {
                return ((b) this.f.get(keyAt)).c;
            }
        }
        return -1;
    }

    void a(Context context, int i) {
        Context applicationContext = context.getApplicationContext();
        NotificationManager notificationManager = (NotificationManager) applicationContext.getSystemService("notification");
        b bVar = (b) this.f.get(i);
        bVar.b.e();
        a(applicationContext, bVar.b, i, 1);
        bVar.b.b(new StringBuilder(String.valueOf(applicationContext.getResources().getString(k.h(applicationContext)))).append(bVar.e.b).toString()).b().a(false).b(true);
        notificationManager.notify(i, bVar.b.d());
    }

    void b(Context context, int i) {
        NotificationManager notificationManager = (NotificationManager) context.getApplicationContext().getSystemService("notification");
        b bVar = (b) this.f.get(i);
        if (bVar != null) {
            u.upd.b.c(e, "download service clear cache " + bVar.e.b);
            if (bVar.a != null) {
                bVar.a.a(2);
            }
            notificationManager.cancel(bVar.c);
            if (this.g.containsKey(bVar.e)) {
                this.g.remove(bVar.e);
            }
            bVar.b(this.f);
            this.h.b(i);
        }
    }

    void a(a aVar, long j, long j2, long j3) {
        if (aVar.f != null) {
            Map hashMap = new HashMap();
            hashMap.put("dsize", String.valueOf(j));
            hashMap.put("dtime", m.a().split(" ")[1]);
            float f = 0.0f;
            if (j2 > 0) {
                f = ((float) j) / ((float) j2);
            }
            hashMap.put("dpcent", String.valueOf((int) (f * 100.0f)));
            hashMap.put("ptimes", String.valueOf(j3));
            a(hashMap, false, aVar.f);
        }
    }

    final void a(Map<String, String> map, boolean z, String[] strArr) {
        new Thread(new c$1(this, strArr, z, map)).start();
    }

    boolean a(DownloadingService downloadingService, Intent intent) {
        Context applicationContext;
        int parseInt;
        b bVar;
        Message obtain;
        try {
            applicationContext = downloadingService.getApplicationContext();
            String[] split = intent.getExtras().getString(f.e).split(":");
            parseInt = Integer.parseInt(split[0]);
            CharSequence trim = split[1].trim();
            if (!(parseInt == 0 || TextUtils.isEmpty(trim) || this.f.indexOfKey(parseInt) < 0)) {
                bVar = (b) this.f.get(parseInt);
                b bVar2 = bVar.a;
                if (f.b.equals(trim)) {
                    if (bVar2 == null) {
                        u.upd.b.c(e, "Receive action do play click.");
                        if (!a.a(applicationContext, UpdateConfig.g) || a.e(applicationContext)) {
                            downloadingService.getClass();
                            bVar2 = new b(downloadingService, applicationContext, bVar.e, parseInt, bVar.d, downloadingService.q);
                            bVar.a = bVar2;
                            bVar2.start();
                            obtain = Message.obtain();
                            obtain.what = 2;
                            obtain.arg1 = 7;
                            obtain.arg2 = parseInt;
                            try {
                                if (this.g.get(bVar.e) != null) {
                                    ((Messenger) this.g.get(bVar.e)).send(obtain);
                                }
                            } catch (Exception e) {
                                u.upd.b.b(e, "", e);
                            }
                            return true;
                        }
                        Toast.makeText(applicationContext, applicationContext.getResources().getString(k.a(applicationContext.getApplicationContext())), 1).show();
                        return false;
                    }
                    u.upd.b.c(e, "Receive action do play click.");
                    bVar2.a(1);
                    bVar.a = null;
                    a(applicationContext, parseInt);
                    obtain = Message.obtain();
                    obtain.what = 2;
                    obtain.arg1 = 6;
                    obtain.arg2 = parseInt;
                    try {
                        if (this.g.get(bVar.e) != null) {
                            ((Messenger) this.g.get(bVar.e)).send(obtain);
                        }
                    } catch (Exception e2) {
                        u.upd.b.b(e, "", e2);
                    }
                    return true;
                } else if (f.c.equals(trim)) {
                    u.upd.b.c(e, "Receive action do stop click.");
                    if (bVar2 != null) {
                        bVar2.a(2);
                    } else {
                        a(bVar.e, bVar.f[0], bVar.f[1], bVar.f[2]);
                    }
                    obtain = Message.obtain();
                    obtain.what = 5;
                    obtain.arg1 = 5;
                    obtain.arg2 = parseInt;
                    try {
                        if (this.g.get(bVar.e) != null) {
                            ((Messenger) this.g.get(bVar.e)).send(obtain);
                        }
                        b(applicationContext, parseInt);
                    } catch (RemoteException e3) {
                        b(applicationContext, parseInt);
                    }
                    return true;
                }
            }
        } catch (Exception e4) {
            obtain = Message.obtain();
            obtain.what = 5;
            obtain.arg1 = 5;
            obtain.arg2 = parseInt;
            try {
                if (this.g.get(bVar.e) != null) {
                    ((Messenger) this.g.get(bVar.e)).send(obtain);
                }
                b(applicationContext, parseInt);
            } catch (RemoteException e5) {
                b(applicationContext, parseInt);
            }
        } catch (Exception e22) {
            e22.printStackTrace();
        } catch (Throwable th) {
            Throwable th2 = th;
            Message obtain2 = Message.obtain();
            obtain2.what = 5;
            obtain2.arg1 = 5;
            obtain2.arg2 = parseInt;
            try {
                if (this.g.get(bVar.e) != null) {
                    ((Messenger) this.g.get(bVar.e)).send(obtain2);
                }
                b(applicationContext, parseInt);
            } catch (RemoteException e6) {
                b(applicationContext, parseInt);
            }
        }
        return false;
    }
}
