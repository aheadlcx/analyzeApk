package com.baidu.mobads.g;

import android.text.TextUtils;
import com.baidu.mobads.c.a;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;

class r implements Runnable {
    final /* synthetic */ q a;

    r(q qVar) {
        this.a = qVar;
    }

    public void run() {
        try {
            String a = this.a.a("key_crash_trace");
            String a2 = this.a.a("key_crash_ad");
            if (!TextUtils.isEmpty(a)) {
                a.a().a(this.a.a("key_crash_source"), a, a2);
                this.a.e();
            }
        } catch (Throwable e) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().e(e);
        }
    }
}
