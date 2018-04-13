package com.tencent.bugly.crashreport.crash.jni;

import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;

final class c implements Runnable {
    private /* synthetic */ NativeCrashHandler a;

    c(NativeCrashHandler nativeCrashHandler) {
        this.a = nativeCrashHandler;
    }

    public final void run() {
        if (z.a(this.a.b, "native_record_lock", 10000)) {
            try {
                this.a.setNativeAppVersion(this.a.c.j);
                this.a.setNativeAppChannel(this.a.c.l);
                this.a.setNativeAppPackage(this.a.c.c);
                this.a.setNativeUserId(this.a.c.g());
                this.a.setNativeIsAppForeground(this.a.c.a());
                this.a.setNativeLaunchTime(this.a.c.a);
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
            CrashDetailBean a = b.a(this.a.b, this.a.f, this.a.e);
            if (a != null) {
                x.a("[Native] Get crash from native record.", new Object[0]);
                if (!this.a.n.a(a)) {
                    this.a.n.a(a, 3000, false);
                }
                b.a(false, this.a.f);
            }
            this.a.a();
            z.b(this.a.b, "native_record_lock");
            return;
        }
        x.a("[Native] Failed to lock file for handling native crash record.", new Object[0]);
    }
}
