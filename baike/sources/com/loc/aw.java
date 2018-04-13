package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public final class aw {
    private static final aw a = new aw();
    private final Map<String, az> b = new HashMap();

    private aw() {
    }

    public static aw a() {
        return a;
    }

    final synchronized az a(Context context, s sVar) throws Exception {
        az azVar;
        Object obj;
        if (sVar != null) {
            if (!(TextUtils.isEmpty(sVar.b()) || TextUtils.isEmpty(sVar.a()))) {
                obj = 1;
                if (obj != null || context == null) {
                    throw new Exception("sdkInfo or context referance is null");
                }
                String a = sVar.a();
                azVar = (az) this.b.get(a);
                if (azVar == null) {
                    try {
                        az diVar = new di(context.getApplicationContext(), sVar);
                        try {
                            this.b.put(a, diVar);
                            ba.a(context, sVar);
                            azVar = diVar;
                        } catch (Throwable th) {
                            azVar = diVar;
                        }
                    } catch (Throwable th2) {
                    }
                }
            }
        }
        obj = null;
        if (obj != null) {
        }
        throw new Exception("sdkInfo or context referance is null");
        return azVar;
    }
}
