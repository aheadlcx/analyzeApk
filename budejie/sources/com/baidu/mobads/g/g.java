package com.baidu.mobads.g;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.mobads.a.b;
import com.baidu.mobads.interfaces.IXAdContainerFactory;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.openad.d.a;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.utils.k;
import com.baidu.mobads.utils.l;
import java.io.File;
import java.io.FileInputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.jar.JarFile;

public class g {
    protected static UncaughtExceptionHandler a;
    protected static volatile a b = null;
    protected static volatile a c = null;
    protected static volatile Class d = null;
    protected static String e = null;
    protected static final Handler f = new h(Looper.getMainLooper());
    private static String i;
    protected Handler g = f;
    @SuppressLint({"HandlerLeak"})
    protected final Handler h = new i(this, Looper.getMainLooper());
    private a j;
    private e k;
    private final Context l;
    private IXAdLogger m = XAdSDKFoundationFacade.getInstance().getAdLogger();
    private boolean n = false;
    private g$c o;

    private void k() {
        try {
            File[] listFiles = this.l.getFilesDir().listFiles();
            int i = 0;
            while (listFiles != null && i < listFiles.length) {
                if (listFiles[i].getAbsolutePath().contains("__xadsdk__remote__final__") && listFiles[i].getAbsolutePath().endsWith("dex")) {
                    listFiles[i].delete();
                }
                i++;
            }
        } catch (Throwable e) {
            l.a().e(e);
        }
    }

    public final String a() {
        return "8.7018";
    }

    public g(Context context) {
        if (i == null) {
            i = XAdSDKFoundationFacade.getInstance().getURIUitls().replaceURLWithSupportProtocol("http://mobads.baidu.com/ads/pa/") + b.b() + "/__pasys_remote_banner.php";
        }
        this.l = context;
        c(context);
        if (a == null) {
            a = q.a(context);
            q.a(context).a(new j(this));
        }
        if (!(Thread.getDefaultUncaughtExceptionHandler() instanceof q)) {
            Thread.setDefaultUncaughtExceptionHandler(a);
        }
    }

    private static void c(Context context) {
        if (TextUtils.isEmpty(e)) {
            e = context.getDir("baidu_ad_sdk", 0).getAbsolutePath() + "/";
        }
    }

    private SharedPreferences l() {
        return this.l.getSharedPreferences("com.baidu.mobads.loader", 0);
    }

    protected void b() {
        new File(f()).delete();
    }

    @TargetApi(9)
    protected void a(String str) {
        if (b != null) {
            Editor edit = l().edit();
            edit.putFloat("__badApkVersion__8.7018", (float) b.a);
            if (VERSION.SDK_INT >= 9) {
                edit.apply();
            } else {
                edit.commit();
            }
        }
    }

    private boolean m() {
        String string = l().getString("previousProxyVersion", null);
        String a = a();
        if (string != null && string.equals(a)) {
            return false;
        }
        return true;
    }

    private void a(boolean z) {
        Message obtainMessage = this.g.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putBoolean("success", z);
        obtainMessage.setData(bundle);
        obtainMessage.what = 0;
        this.g.sendMessage(obtainMessage);
    }

    protected static String c() {
        if (TextUtils.isEmpty(e)) {
            return "";
        }
        return e + "__xadsdk__remote__final__builtin__.jar";
    }

    protected static String d() {
        if (TextUtils.isEmpty(e)) {
            return "";
        }
        return e + "__xadsdk__remote__final__builtinversion__.jar";
    }

    protected void e() {
        d(this.l);
        String c = c();
        b bVar = new b(c, this.l);
        if (!k.a((File) bVar)) {
            throw new g$b("loadBuiltInApk failed: " + c);
        } else if (c(bVar)) {
            b(true);
        }
    }

    private static synchronized void d(Context context) {
        synchronized (g.class) {
            try {
                String c = c();
                if (Double.valueOf("8.7018").doubleValue() > a(context, c)) {
                    b bVar = new b(c, context);
                    if (bVar.exists()) {
                        bVar.delete();
                    }
                    XAdSDKFoundationFacade.getInstance().getIoUtils().copyFileFromAssetsTo(context, "bdxadsdk.jar", c);
                }
            } catch (Exception e) {
                throw new g$b("loadBuiltInApk failed: " + e.toString());
            }
        }
    }

    private void a(b bVar) {
        Class b = bVar.b();
        synchronized (this) {
            c = new a(b, this.l, b.a(), b.a);
        }
    }

    private void b(b bVar) {
        Log.i("XAdApkLoader", "len=" + bVar.length() + ", path=" + bVar.getAbsolutePath());
        if (b == null) {
            String a = a(this.l);
            b bVar2 = new b(a, this.l);
            if (bVar2.exists()) {
                bVar2.delete();
            }
            try {
                XAdSDKFoundationFacade.getInstance().getIoUtils().copyFileInputStream(new FileInputStream(bVar), a);
            } catch (Throwable e) {
                this.m.e(e);
            }
            b = new a(bVar2.b(), this.l, b.a(), b.a);
            try {
                this.m.d("XAdApkLoader", "preloaded apk.version=" + b.a().getRemoteVersion());
                return;
            } catch (g$a e2) {
                this.m.w("XAdApkLoader", "preload local apk " + bVar.getAbsolutePath() + " failed, msg:" + e2.getMessage() + ", v=" + b.a);
                a(e2.getMessage());
                throw e2;
            }
        }
        this.m.w("XAdApkLoader", "mApkBuilder already initialized, version: " + b.a);
    }

    private boolean c(b bVar) {
        synchronized (this) {
            b(bVar);
            this.m.d("XAdApkLoader", "loaded: " + bVar.getPath());
        }
        return true;
    }

    private boolean n() {
        try {
            return k.b(c()) || k.b(f());
        } catch (Throwable e) {
            this.m.d(e);
            return false;
        }
    }

    private void b(boolean z) {
        long j;
        if (z || n()) {
            a(z, z ? "apk Successfully Loaded" : "apk Load Failed");
        } else {
            this.n = true;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable kVar = new k(this, z);
        if (this.n) {
            j = 0;
        } else {
            j = 5000;
        }
        handler.postDelayed(kVar, j);
    }

    private synchronized void o() {
        try {
            if (this.j != null) {
                this.j.removeAllListeners();
                this.j.a();
            }
            this.j = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(boolean z, String str) {
        q.a(this.l).b();
        if (this.o != null) {
            this.o.a(z);
            this.o = null;
        }
    }

    public static String a(Context context) {
        if (TextUtils.isEmpty(e)) {
            e = context.getDir("baidu_ad_sdk", 0).getAbsolutePath() + "/";
        }
        if (TextUtils.isEmpty(e)) {
            return "";
        }
        return e + "__xadsdk__remote__final__running__.jar";
    }

    protected static String f() {
        if (TextUtils.isEmpty(e)) {
            return "";
        }
        return e + "__xadsdk__remote__final__downloaded__.jar";
    }

    protected void g() {
        if (p()) {
            b(true);
            return;
        }
        this.m.d("XAdApkLoader", "no downloaded file yet, use built-in apk file");
        try {
            e();
        } catch (g$b e) {
            this.m.e("XAdApkLoader", "loadBuiltInApk failed: " + e.toString());
            throw new g$a("load built-in apk failed" + e.toString());
        }
    }

    private boolean p() {
        b bVar = new b(f(), this.l);
        if (k.a((File) bVar)) {
            try {
                if (m()) {
                    String str = "XAdApkLoader upgraded, drop stale downloaded file, use built-in instead";
                    throw new g$a("XAdApkLoader upgraded, drop stale downloaded file, use built-in instead");
                }
                synchronized (this) {
                    Log.i("XAdApkLoader", "loadDownloadedOrBuiltInApk len=" + bVar.length() + ", path=" + bVar.getAbsolutePath());
                    b(bVar);
                    double d = (double) l().getFloat("__badApkVersion__8.7018", -1.0f);
                    this.m.d("XAdApkLoader", "downloadedApkFile.getApkVersion(): " + bVar.c() + ", badApkVersion: " + d);
                    if (bVar.c() == d) {
                        throw new g$a("downloaded file marked bad, drop it and use built-in");
                    }
                    this.m.d("XAdApkLoader", "loaded: " + bVar.getPath());
                }
                return true;
            } catch (g$a e) {
                this.m.e("XAdApkLoader", "load downloaded apk failed: " + e.toString() + ", fallback to built-in");
                if (bVar != null && bVar.exists()) {
                    bVar.delete();
                }
                i();
            }
        }
        return false;
    }

    private void a(e eVar) {
        if (eVar.a().booleanValue()) {
            c a = c.a(this.l, eVar, e, this.h);
            if (a.isAlive()) {
                this.m.d("XAdApkLoader", "XApkDownloadThread already started");
                a.a(eVar.c());
                return;
            }
            this.m.d("XAdApkLoader", "XApkDownloadThread starting ...");
            a.start();
        }
    }

    private void b(g$c g_c, Handler handler) {
        this.o = g_c;
        this.g = handler;
        if (b == null) {
            g();
        } else {
            b(true);
        }
    }

    @TargetApi(9)
    public void a(g$c g_c, Handler handler) {
        new Thread(new n(this, g_c, handler)).start();
    }

    public void a(g$c g_c) {
        a(g_c, f);
    }

    public IXAdContainerFactory h() {
        return a(b);
    }

    private IXAdContainerFactory a(a aVar) {
        IXAdContainerFactory iXAdContainerFactory = null;
        if (aVar != null) {
            try {
                iXAdContainerFactory = aVar.a();
            } catch (Exception e) {
            }
        }
        return iXAdContainerFactory;
    }

    protected void i() {
        if (b != null) {
            b.b();
            b = null;
        }
    }

    public static double b(Context context) {
        try {
            c(context);
            double a = a(context, f());
            String d = d();
            if (Double.valueOf("8.7018").doubleValue() > a(context, d)) {
                b bVar = new b(d, context);
                if (bVar.exists()) {
                    bVar.delete();
                }
                XAdSDKFoundationFacade.getInstance().getIoUtils().copyFileFromAssetsTo(context, "bdxadsdk.jar", d);
            }
            return Math.max(a, a(context, d()));
        } catch (Exception e) {
            return 0.0d;
        }
    }

    public static double a(Context context, String str) {
        try {
            File file = new File(str);
            if (k.a(file)) {
                JarFile jarFile = new JarFile(file);
                double parseDouble = Double.parseDouble(jarFile.getManifest().getMainAttributes().getValue("Implementation-Version"));
                jarFile.close();
                if (parseDouble > 0.0d) {
                    return parseDouble;
                }
            }
        } catch (Exception e) {
        }
        return 0.0d;
    }
}
