package com.crashlytics.android.internal;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import java.util.concurrent.ScheduledExecutorService;

@TargetApi(14)
final class g extends ac {
    private final Application b;
    private final ActivityLifecycleCallbacks c;

    public g(Application application, String str, String str2, String str3, String str4, String str5, String str6, String str7, o oVar, C0011av c0011av) {
        this(application, str, str2, str3, str4, str5, str6, str7, oVar, ah.b("Crashlytics Trace Manager"), c0011av);
    }

    private g(Application application, String str, String str2, String str3, String str4, String str5, String str6, String str7, o oVar, ScheduledExecutorService scheduledExecutorService, C0011av c0011av) {
        super(str, str2, str3, str4, str5, str6, str7, oVar, scheduledExecutorService, c0011av);
        this.c = new h(this);
        this.b = application;
        C0003ab.c("Registering activity lifecycle callbacks for session analytics.");
        application.registerActivityLifecycleCallbacks(this.c);
    }

    final void a() {
        C0003ab.c("Unregistering activity lifecycle callbacks for session analytics");
        this.b.unregisterActivityLifecycleCallbacks(this.c);
        super.a();
    }
}
