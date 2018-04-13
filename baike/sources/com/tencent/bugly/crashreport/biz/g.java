package com.tencent.bugly.crashreport.biz;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;

final class g implements ActivityLifecycleCallbacks {
    g() {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityResumed(Activity activity) {
        String str = "unknown";
        if (activity != null) {
            str = activity.getClass().getName();
        }
        if (b.l == null || b.l.getName().equals(str)) {
            x.c(">>> %s onResumed <<<", str);
            a b = a.b();
            if (b != null) {
                b.C.add(b.a(str, "onResumed"));
                b.a(true);
                b.p = str;
                b.q = System.currentTimeMillis();
                b.t = b.q - b.i;
                if (b.q - b.h > (b.f > 0 ? b.f : b.e)) {
                    b.d();
                    b.g();
                    x.a("[session] launch app one times (app in background %d seconds and over %d seconds)", Long.valueOf(r4 / 1000), Long.valueOf(b.e / 1000));
                    if (b.g % b.c == 0) {
                        b.a.a(4, b.m, 0);
                        return;
                    }
                    b.a.a(4, false, 0);
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - b.j > b.d) {
                        b.j = currentTimeMillis;
                        x.a("add a timer to upload hot start user info", new Object[0]);
                        if (b.m) {
                            w.a().a(new a(b.a, null, true), b.d);
                        }
                    }
                }
            }
        }
    }

    public final void onActivityPaused(Activity activity) {
        String str = "unknown";
        if (activity != null) {
            str = activity.getClass().getName();
        }
        if (b.l == null || b.l.getName().equals(str)) {
            x.c(">>> %s onPaused <<<", str);
            a b = a.b();
            if (b != null) {
                b.C.add(b.a(str, "onPaused"));
                b.a(false);
                b.r = System.currentTimeMillis();
                b.s = b.r - b.q;
                b.h = b.r;
                if (b.s < 0) {
                    b.s = 0;
                }
                if (activity != null) {
                    b.p = "background";
                } else {
                    b.p = "unknown";
                }
            }
        }
    }

    public final void onActivityDestroyed(Activity activity) {
        String str = "unknown";
        if (activity != null) {
            str = activity.getClass().getName();
        }
        if (b.l == null || b.l.getName().equals(str)) {
            x.c(">>> %s onDestroyed <<<", str);
            a b = a.b();
            if (b != null) {
                b.C.add(b.a(str, "onDestroyed"));
            }
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        String str = "unknown";
        if (activity != null) {
            str = activity.getClass().getName();
        }
        if (b.l == null || b.l.getName().equals(str)) {
            x.c(">>> %s onCreated <<<", str);
            a b = a.b();
            if (b != null) {
                b.C.add(b.a(str, "onCreated"));
            }
        }
    }
}
