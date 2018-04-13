package com.tencent.bugly.beta.ui;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.os.Build.VERSION;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.tencent.bugly.beta.global.d;
import com.tencent.bugly.beta.global.e;
import com.tencent.bugly.beta.upgrade.c;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class g {
    public static final Map<Integer, b> a = new ConcurrentHashMap();
    public static final Map<Integer, d> b = new ConcurrentHashMap();
    public static final Map<Integer, d> c = new ConcurrentHashMap();
    private static d d;

    public static synchronized void a(b bVar, boolean z) {
        synchronized (g.class) {
            if (VERSION.SDK_INT >= 14) {
                a(bVar, z, false, 5000);
            } else {
                try {
                    ActivityManager activityManager = (ActivityManager) e.E.s.getSystemService("activity");
                    if (activityManager != null) {
                        activityManager.getRunningTasks(1);
                        a(bVar, z, false, 5000);
                    }
                } catch (SecurityException e) {
                    if (z) {
                        a(bVar, z, true, 0);
                    } else {
                        an.e("无法获取GET_TASK权限，将在通知栏提醒升级，如需弹窗提醒，请在AndroidManifest.xml中添加GET_TASKS权限：\n<uses-permission android:name=\"android.permission.GET_TASKS\" />\n", new Object[0]);
                        if (!(c.a.b == null || c.a.b.a == null)) {
                            c.a.a(c.a.b.a, bVar);
                        }
                    }
                }
            }
        }
    }

    public static synchronized void a(b bVar, boolean z, boolean z2, long j) {
        synchronized (g.class) {
            if (bVar != null) {
                if (!bVar.b()) {
                    Runnable runnable;
                    int hashCode = bVar.hashCode();
                    if (bVar instanceof h) {
                        com.tencent.bugly.beta.utils.e.b(d);
                        if (((h) bVar).p.g == (byte) 2) {
                            d = new d(15, new Object[]{bVar, Boolean.valueOf(z), Boolean.valueOf(z2), Long.valueOf(j)});
                            com.tencent.bugly.beta.utils.e.a(d, 3000);
                        }
                        if (z || b()) {
                            com.tencent.bugly.beta.utils.e.b((Runnable) b.remove(Integer.valueOf(hashCode)));
                        } else {
                            runnable = (d) b.get(Integer.valueOf(hashCode));
                            if (runnable == null) {
                                runnable = new d(11, new Object[]{bVar, Boolean.valueOf(z), Boolean.valueOf(z2), Long.valueOf(j)});
                                b.put(Integer.valueOf(hashCode), runnable);
                            }
                            com.tencent.bugly.beta.utils.e.b(runnable);
                            com.tencent.bugly.beta.utils.e.a(runnable, j);
                        }
                    }
                    if (z2 || ap.b(e.E.s)) {
                        com.tencent.bugly.beta.utils.e.b((Runnable) c.remove(Integer.valueOf(hashCode)));
                        Runnable dVar = new d(17, new Object[]{a, Integer.valueOf(hashCode), bVar});
                        FragmentActivity activity = bVar.getActivity();
                        if (activity != null) {
                            if (activity instanceof BetaActivity) {
                                ((BetaActivity) activity).onDestroyRunnable = dVar;
                            } else {
                                com.tencent.bugly.beta.utils.e.a(dVar, 400);
                            }
                            activity.finish();
                        } else {
                            dVar.run();
                        }
                    } else {
                        runnable = (d) c.get(Integer.valueOf(hashCode));
                        if (runnable == null) {
                            runnable = new d(11, new Object[]{bVar, Boolean.valueOf(z), Boolean.valueOf(z2), Long.valueOf(j)});
                            c.put(Integer.valueOf(hashCode), runnable);
                        }
                        com.tencent.bugly.beta.utils.e.b(runnable);
                        com.tencent.bugly.beta.utils.e.a(runnable, j);
                    }
                }
            }
        }
    }

    public static String a() {
        try {
            if (VERSION.SDK_INT >= 14) {
                return a.b().u;
            }
            ActivityManager activityManager = (ActivityManager) e.E.s.getSystemService("activity");
            if (activityManager != null) {
                List runningTasks = activityManager.getRunningTasks(1);
                if (!(runningTasks == null || runningTasks.isEmpty())) {
                    return ((RunningTaskInfo) runningTasks.get(0)).topActivity.getClassName();
                }
            }
            return null;
        } catch (SecurityException e) {
            an.e("无法获取Activity信息，请在AndroidManifest.xml中添加GET_TASKS权限：\n<uses-permission android:name=\"android.permission.GET_TASKS\" />\n", new Object[0]);
        } catch (Throwable e2) {
            if (!an.b(e2)) {
                e2.printStackTrace();
            }
        }
    }

    public static synchronized boolean b() {
        boolean z;
        Class cls;
        synchronized (g.class) {
            CharSequence a = a();
            if (a == null || a.equals("background") || a.equals("unknown")) {
                z = false;
            } else {
                Class cls2 = null;
                try {
                    cls = Class.forName(a);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    cls = cls2;
                }
                if (!e.E.m.isEmpty()) {
                    for (Class cls22 : e.E.m) {
                        if (TextUtils.equals(cls22.getName(), a) || (r3 != null && cls22.isAssignableFrom(r3))) {
                            z = true;
                            break;
                        }
                    }
                    z = false;
                } else if (e.E.n.isEmpty()) {
                    z = true;
                } else {
                    for (Class cls222 : e.E.n) {
                        if (TextUtils.equals(cls222.getName(), a) || (r3 != null && cls222.isAssignableFrom(r3))) {
                            z = false;
                            break;
                        }
                    }
                    z = true;
                }
            }
        }
        return z;
    }
}
