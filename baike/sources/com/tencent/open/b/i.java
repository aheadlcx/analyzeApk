package com.tencent.open.b;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Message;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.f;
import com.tencent.open.utils.d;
import com.tencent.open.utils.e;
import com.umeng.commonsdk.proguard.g;
import java.util.TimeZone;

class i implements Runnable {
    final /* synthetic */ Bundle a;
    final /* synthetic */ boolean b;
    final /* synthetic */ g c;

    i(g gVar, Bundle bundle, boolean z) {
        this.c = gVar;
        this.a = bundle;
        this.b = z;
    }

    public void run() {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("uin", Constants.DEFAULT_UIN);
            bundle.putString("imei", c.b(d.a()));
            bundle.putString("imsi", c.c(d.a()));
            bundle.putString("android_id", c.d(d.a()));
            bundle.putString("mac", c.a());
            bundle.putString("platform", "1");
            bundle.putString("os_ver", VERSION.RELEASE);
            bundle.putString("position", com.tencent.open.utils.i.c(d.a()));
            bundle.putString("network", a.a(d.a()));
            bundle.putString(g.M, c.b());
            bundle.putString(g.y, c.a(d.a()));
            bundle.putString("apn", a.b(d.a()));
            bundle.putString("model_name", Build.MODEL);
            bundle.putString(g.L, TimeZone.getDefault().getID());
            bundle.putString("sdk_ver", Constants.SDK_VERSION);
            bundle.putString("qz_ver", com.tencent.open.utils.i.d(d.a(), Constants.PACKAGE_QZONE));
            bundle.putString("qq_ver", com.tencent.open.utils.i.c(d.a(), "com.tencent.mobileqq"));
            bundle.putString("qua", com.tencent.open.utils.i.e(d.a(), d.b()));
            bundle.putString("packagename", d.b());
            bundle.putString("app_ver", com.tencent.open.utils.i.d(d.a(), d.b()));
            if (this.a != null) {
                bundle.putAll(this.a);
            }
            this.c.d.add(new b(bundle));
            int size = this.c.d.size();
            int a = e.a(d.a(), null).a("Agent_ReportTimeInterval");
            if (a == 0) {
                a = 10000;
            }
            if (this.c.a("report_via", size) || this.b) {
                this.c.e();
                this.c.f.removeMessages(1001);
            } else if (!this.c.f.hasMessages(1001)) {
                Message obtain = Message.obtain();
                obtain.what = 1001;
                this.c.f.sendMessageDelayed(obtain, (long) a);
            }
        } catch (Throwable e) {
            f.b("openSDK_LOG.ReportManager", "--> reporVia, exception in sub thread.", e);
        }
    }
}
