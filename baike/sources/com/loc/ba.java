package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.lang.Thread.UncaughtExceptionHandler;

public final class ba implements UncaughtExceptionHandler {
    private static ba a;
    private UncaughtExceptionHandler b = Thread.getDefaultUncaughtExceptionHandler();
    private Context c;
    private s d;

    private ba(Context context, s sVar) {
        this.c = context.getApplicationContext();
        this.d = sVar;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    static synchronized ba a(Context context, s sVar) {
        ba baVar;
        synchronized (ba.class) {
            if (a == null) {
                a = new ba(context, sVar);
            }
            baVar = a;
        }
        return baVar;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        String a = t.a(th);
        try {
            if (!TextUtils.isEmpty(a)) {
                if ((a.contains("amapdynamic") || a.contains("admic")) && a.contains("com.amap.api")) {
                    af afVar = new af(this.c, bb.b());
                    if (a.contains("loc")) {
                        ay.a(afVar, this.c, "loc");
                    }
                    if (a.contains("navi")) {
                        ay.a(afVar, this.c, "navi");
                    }
                    if (a.contains("sea")) {
                        ay.a(afVar, this.c, "sea");
                    }
                    if (a.contains("2dmap")) {
                        ay.a(afVar, this.c, "2dmap");
                    }
                    if (a.contains("3dmap")) {
                        ay.a(afVar, this.c, "3dmap");
                    }
                } else if (a.contains("com.autonavi.aps.amapapi.offline")) {
                    ay.a(new af(this.c, bb.b()), this.c, "OfflineLocation");
                } else if (a.contains("com.data.carrier_v4")) {
                    ay.a(new af(this.c, bb.b()), this.c, "Collection");
                } else if (a.contains("com.autonavi.aps.amapapi.httpdns") || a.contains("com.autonavi.httpdns")) {
                    ay.a(new af(this.c, bb.b()), this.c, "HttpDNS");
                }
            }
        } catch (Throwable th2) {
            w.a(th2, "DynamicExceptionHandler", "uncaughtException");
        }
        if (this.b != null) {
            this.b.uncaughtException(thread, th);
        }
    }
}
