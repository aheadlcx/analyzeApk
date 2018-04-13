package com.crashlytics.android.internal;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import com.crashlytics.android.Crashlytics;
import java.io.File;
import qsbk.app.im.TimeUtils;

public class D extends u {
    private String a;
    private String b;
    private String c;
    private ao d;
    private aJ e;
    private long f;
    private C0011av g;
    private ac h;

    static /* synthetic */ void a(D d) {
        aX b;
        Context context = d.getContext();
        try {
            Object obj;
            o oVar = new o(new bh(), new ah(), new C0008aj(v.a().h(), "session_analytics.tap", "session_analytics_to_send"));
            String b2 = d.d.b();
            String g = d.d.g();
            String c = d.d.c();
            String d2 = d.d.d();
            Application d3 = v.a().d();
            if (d3 == null || VERSION.SDK_INT < 14) {
                d.h = new ac(context.getPackageName(), b2, g, c, d2, d.b, d.c, oVar, d.g);
            } else {
                d.h = new g(d3, context.getPackageName(), b2, g, c, d2, d.b, d.c, oVar, d.g);
            }
            long j = d.f;
            if (!d.e.a().getBoolean("analytics_launched", false)) {
                if ((System.currentTimeMillis() - j < TimeUtils.HOUR ? 1 : null) != null) {
                    obj = 1;
                    if (obj != null) {
                        v.a().b().a(Crashlytics.TAG, "First launch");
                        if (d.h != null) {
                            d.h.b();
                            d.e.a(d.e.b().putBoolean("analytics_launched", true));
                        }
                    }
                    aS.a().a(context, d.g, d.b, d.c, d.b()).c();
                    b = aS.a().b();
                    if (b == null) {
                    }
                    if (b.d.c) {
                        C0003ab.c("Disabling analytics collection based on settings flag value.");
                        d.h.a();
                    }
                    d.h.a(b.e, d.b());
                    return;
                }
            }
            obj = null;
            if (obj != null) {
                v.a().b().a(Crashlytics.TAG, "First launch");
                if (d.h != null) {
                    d.h.b();
                    d.e.a(d.e.b().putBoolean("analytics_launched", true));
                }
            }
        } catch (Exception e) {
            C0003ab.d("Crashlytics failed to initialize session analytics.");
        }
        try {
            aS.a().a(context, d.g, d.b, d.c, d.b()).c();
            b = aS.a().b();
            if (b == null) {
                if (b.d.c) {
                    d.h.a(b.e, d.b());
                    return;
                }
                C0003ab.c("Disabling analytics collection based on settings flag value.");
                d.h.a();
            }
        } catch (Throwable e2) {
            v.a().b().a(Crashlytics.TAG, "Error dealing with settings", e2);
        }
    }

    public static D a() {
        return (D) v.a().a(D.class);
    }

    protected final void c() {
        try {
            this.g = new C0011av(v.a().b());
            this.e = new aJ(v.a().a(D.class));
            Context context = getContext();
            PackageManager packageManager = context.getPackageManager();
            this.d = new ao(context);
            this.a = context.getPackageName();
            PackageInfo packageInfo = packageManager.getPackageInfo(this.a, 0);
            this.b = Integer.toString(packageInfo.versionCode);
            this.c = packageInfo.versionName == null ? "0.0" : packageInfo.versionName;
            if (VERSION.SDK_INT >= 9) {
                this.f = packageInfo.firstInstallTime;
            } else {
                this.f = new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir).lastModified();
            }
        } catch (Throwable e) {
            v.a().b().a(Crashlytics.TAG, "Error setting up app properties", e);
        }
        new Thread(new e(this), "Crashlytics Initializer").start();
    }

    public String getVersion() {
        return v.a().getVersion();
    }

    private String b() {
        return C0003ab.a(getContext(), "com.crashlytics.ApiEndpoint");
    }

    public final void a(C0007ag c0007ag) {
        if (this.h != null) {
            this.h.b(c0007ag.a());
        }
    }

    public final void a(C0006af c0006af) {
        if (this.h != null) {
            this.h.a(c0006af.a());
        }
    }
}
