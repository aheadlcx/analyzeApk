package com.ak.android.bridge;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Process;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.facebook.cache.disk.DefaultDiskStorage.FileType;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class c {
    private static Context a = null;
    private static Context b = null;
    private static IBridge c;
    private static String d;
    private static String e;
    private static String f;
    private static File g;
    private static File h;
    private static File i;
    private static boolean j = false;

    public static IBridge a(Context context) {
        synchronized (c.class) {
            if (c != null) {
                IBridge iBridge = c;
                return iBridge;
            }
            b = context;
            Context applicationContext = context.getApplicationContext();
            a = applicationContext;
            Editor edit = applicationContext.getSharedPreferences(b.g, 0).edit();
            edit.putString(b.h, b.i);
            edit.commit();
            d = a.getFilesDir().getAbsolutePath() + File.separator + b.a;
            e = a.getFilesDir().getAbsolutePath() + File.separator + b.b;
            f = e + Process.myPid() + File.separator;
            e.a(d);
            e.a(f);
            i = new File(f + b.d);
            e.c("defaultJar:" + i);
            h = new File(d + b.d);
            e.c("finalJar:" + h);
            g = new File(d + b.c);
            e.c("newJar:" + g);
            try {
                int available;
                String absolutePath;
                String str;
                int i = a.getSharedPreferences(b.g, 0).getInt(b.j, 0);
                e.c("lastTimeLoadedDefaultCoreSize:" + i);
                if (i != 0) {
                    available = b.getAssets().open(b.e).available();
                    e.c("currentDefaultCoreSize:" + available);
                    if (i == available) {
                        boolean booleanValue = Boolean.valueOf(a.getSharedPreferences(b.g, 0).getBoolean(b.k, false)).booleanValue();
                        e.c("isLoadDefaultCore:" + booleanValue);
                        if (!booleanValue) {
                            if (g.exists()) {
                                e.c("newJar exists");
                                if (h.exists()) {
                                    h.delete();
                                }
                                if (g.renameTo(h)) {
                                    e.c("newJar has been renameed to finalJar");
                                    absolutePath = h.getAbsolutePath();
                                    if (j) {
                                        absolutePath = i.getAbsolutePath();
                                    }
                                    str = f;
                                    e.b("dexPath:" + absolutePath);
                                    e.b("optimizedDirectory:" + str);
                                    c = new a((DynamicObject) new DexClassLoader(absolutePath, str, null, b.getClassLoader()).loadClass(b.f).newInstance());
                                    new Thread() {
                                        public final void run() {
                                            try {
                                                Collection arrayList = new ArrayList();
                                                for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) c.a.getSystemService("activity")).getRunningAppProcesses()) {
                                                    if (runningAppProcessInfo != null) {
                                                        arrayList.add(String.valueOf(runningAppProcessInfo.pid));
                                                    }
                                                }
                                                ArrayList arrayList2 = new ArrayList();
                                                File file = new File(c.e);
                                                if (file.exists() && file.isDirectory()) {
                                                    for (CharSequence charSequence : file.list()) {
                                                        if (!TextUtils.isEmpty(charSequence)) {
                                                            arrayList2.add(charSequence);
                                                        }
                                                    }
                                                }
                                                arrayList.retainAll(arrayList2);
                                                arrayList2.removeAll(arrayList);
                                                Iterator it = arrayList2.iterator();
                                                while (it.hasNext()) {
                                                    e.a(new File(c.e + ((String) it.next()) + File.separator));
                                                }
                                            } catch (Throwable e) {
                                                e.a("clear opt dir exception", e);
                                            }
                                        }
                                    }.start();
                                    iBridge = c;
                                    return iBridge;
                                }
                                e.c("Rename newJar failed");
                            } else {
                                e.c("newJar does not exist");
                                if (h.exists()) {
                                    e.c("finalJar exists");
                                    absolutePath = h.getAbsolutePath();
                                    if (j) {
                                        absolutePath = i.getAbsolutePath();
                                    }
                                    str = f;
                                    e.b("dexPath:" + absolutePath);
                                    e.b("optimizedDirectory:" + str);
                                    c = new a((DynamicObject) new DexClassLoader(absolutePath, str, null, b.getClassLoader()).loadClass(b.f).newInstance());
                                    /* anonymous class already generated */.start();
                                    iBridge = c;
                                    return iBridge;
                                }
                                e.c("finalJar does not exist");
                            }
                        }
                    }
                }
                j = true;
                i.createNewFile();
                e.a(b, b.e, i);
                e.c("DefaultCore has been loaded");
                File file = new File(i.getAbsolutePath() + FileType.TEMP);
                try {
                    if (i()) {
                        available = e.a(b, b.e, file);
                        if (i()) {
                            e.c("updateFinalJar");
                            a(false);
                            if (file.renameTo(h)) {
                                a(available);
                            }
                        }
                    }
                } catch (Throwable e) {
                    e.a("updateFinalJar Exception", e);
                }
                absolutePath = h.getAbsolutePath();
                if (j) {
                    absolutePath = i.getAbsolutePath();
                }
                str = f;
                e.b("dexPath:" + absolutePath);
                e.b("optimizedDirectory:" + str);
                c = new a((DynamicObject) new DexClassLoader(absolutePath, str, null, b.getClassLoader()).loadClass(b.f).newInstance());
                /* anonymous class already generated */.start();
                iBridge = c;
                return iBridge;
            } catch (Throwable e2) {
                e.a("getCoreBridge ClassNotFoundException", e2);
                a(true);
                return null;
            } catch (Throwable e22) {
                e.a("getCoreBridge Exception", e22);
                a(true);
                return null;
            }
        }
    }

    private static a a(String str, String str2) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        e.b("dexPath:" + str);
        e.b("optimizedDirectory:" + str2);
        return new a((DynamicObject) new DexClassLoader(str, str2, null, b.getClassLoader()).loadClass(b.f).newInstance());
    }

    private static void c() {
        d = a.getFilesDir().getAbsolutePath() + File.separator + b.a;
        e = a.getFilesDir().getAbsolutePath() + File.separator + b.b;
        f = e + Process.myPid() + File.separator;
        e.a(d);
        e.a(f);
    }

    private static void d() {
        i = new File(f + b.d);
        e.c("defaultJar:" + i);
        h = new File(d + b.d);
        e.c("finalJar:" + h);
        g = new File(d + b.c);
        e.c("newJar:" + g);
    }

    private static void a(boolean z) {
        if (g.exists()) {
            g.delete();
        }
        if (z) {
            a(0);
        }
        if (h.exists()) {
            h.delete();
        }
    }

    private static Boolean e() {
        return Boolean.valueOf(a.getSharedPreferences(b.g, 0).getBoolean(b.k, false));
    }

    private static int f() throws IOException {
        return b.getAssets().open(b.e).available();
    }

    private static void g() throws IOException {
        j = true;
        i.createNewFile();
        e.a(b, b.e, i);
        e.c("DefaultCore has been loaded");
        File file = new File(i.getAbsolutePath() + FileType.TEMP);
        try {
            if (i()) {
                int a = e.a(b, b.e, file);
                if (i()) {
                    e.c("updateFinalJar");
                    a(false);
                    if (file.renameTo(h)) {
                        a(a);
                    }
                }
            }
        } catch (Throwable e) {
            e.a("updateFinalJar Exception", e);
        }
    }

    private static void h() {
        File file = new File(i.getAbsolutePath() + FileType.TEMP);
        try {
            if (i()) {
                int a = e.a(b, b.e, file);
                if (i()) {
                    e.c("updateFinalJar");
                    a(false);
                    if (file.renameTo(h)) {
                        a(a);
                    }
                }
            }
        } catch (Throwable e) {
            e.a("updateFinalJar Exception", e);
        }
    }

    private static boolean i() {
        return !h.exists() || (h.exists() && System.currentTimeMillis() - h.lastModified() > Config.BPLUS_DELAY_TIME);
    }

    private static void j() {
        /* anonymous class already generated */.start();
    }

    private static void k() {
        Editor edit = a.getSharedPreferences(b.g, 0).edit();
        edit.putString(b.h, b.i);
        edit.commit();
    }

    private static void a(int i) {
        Editor edit = a.getSharedPreferences(b.g, 0).edit();
        edit.putInt(b.j, i);
        edit.commit();
    }

    private static int l() {
        return a.getSharedPreferences(b.g, 0).getInt(b.j, 0);
    }
}
