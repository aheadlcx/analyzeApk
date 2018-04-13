package com.tencent.open.b;

import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.SystemClock;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.f;
import com.tencent.open.utils.d;
import com.tencent.open.utils.e;
import kotlin.text.Typography;

class j implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ int d;
    final /* synthetic */ long e;
    final /* synthetic */ long f;
    final /* synthetic */ boolean g;
    final /* synthetic */ g h;

    j(g gVar, long j, String str, String str2, int i, long j2, long j3, boolean z) {
        this.h = gVar;
        this.a = j;
        this.b = str;
        this.c = str2;
        this.d = i;
        this.e = j2;
        this.f = j3;
        this.g = z;
    }

    public void run() {
        int i = 1;
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.a;
            Bundle bundle = new Bundle();
            String a = a.a(d.a());
            bundle.putString("apn", a);
            bundle.putString("appid", "1000067");
            bundle.putString("commandid", this.b);
            bundle.putString("detail", this.c);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("network=").append(a).append(Typography.amp);
            stringBuilder.append("sdcard=").append(Environment.getExternalStorageState().equals("mounted") ? 1 : 0).append(Typography.amp);
            stringBuilder.append("wifi=").append(a.e(d.a()));
            bundle.putString("deviceInfo", stringBuilder.toString());
            int a2 = 100 / this.h.a(this.d);
            if (a2 > 0) {
                if (a2 > 100) {
                    i = 100;
                } else {
                    i = a2;
                }
            }
            bundle.putString("frequency", i + "");
            bundle.putString("reqSize", this.e + "");
            bundle.putString("resultCode", this.d + "");
            bundle.putString("rspSize", this.f + "");
            bundle.putString("timeCost", elapsedRealtime + "");
            bundle.putString("uin", Constants.DEFAULT_UIN);
            this.h.c.add(new b(bundle));
            int size = this.h.c.size();
            i = e.a(d.a(), null).a("Agent_ReportTimeInterval");
            if (i == 0) {
                i = 10000;
            }
            if (this.h.a("report_cgi", size) || this.g) {
                this.h.b();
                this.h.f.removeMessages(1000);
            } else if (!this.h.f.hasMessages(1000)) {
                Message obtain = Message.obtain();
                obtain.what = 1000;
                this.h.f.sendMessageDelayed(obtain, (long) i);
            }
        } catch (Throwable e) {
            f.b("openSDK_LOG.ReportManager", "--> reportCGI, exception in sub thread.", e);
        }
    }
}
