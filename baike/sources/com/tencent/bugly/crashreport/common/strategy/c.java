package com.tencent.bugly.crashreport.common.strategy;

import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.x;
import java.util.Map;

final class c extends Thread {
    private /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        try {
            Map a = p.a().a(a.a, null, true);
            if (a != null) {
                byte[] bArr = (byte[]) a.get("key_imei");
                byte[] bArr2 = (byte[]) a.get("key_ip");
                if (bArr != null) {
                    a.a(this.a.g).e(new String(bArr));
                }
                if (bArr2 != null) {
                    a.a(this.a.g).d(new String(bArr2));
                }
            }
            a aVar = this.a;
            this.a.f = a.d();
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
        this.a.a(this.a.f, false);
    }
}
