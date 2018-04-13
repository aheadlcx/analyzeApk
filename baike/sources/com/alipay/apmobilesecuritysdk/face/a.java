package com.alipay.apmobilesecuritysdk.face;

import com.alipay.apmobilesecuritysdk.face.APSecuritySdk.InitResultListener;
import java.util.Map;

class a implements Runnable {
    final /* synthetic */ Map a;
    final /* synthetic */ InitResultListener b;
    final /* synthetic */ APSecuritySdk c;

    a(APSecuritySdk aPSecuritySdk, Map map, InitResultListener initResultListener) {
        this.c = aPSecuritySdk;
        this.a = map;
        this.b = initResultListener;
    }

    public void run() {
        new com.alipay.apmobilesecuritysdk.a.a(this.c.b).a(this.a);
        if (this.b != null) {
            this.b.onResult(this.c.getTokenResult());
        }
    }
}
