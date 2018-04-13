package com.huawei.hms.api.internal;

import android.content.Context;
import android.os.Build.VERSION;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.c.a;

public abstract class e {
    public static int a(Context context) {
        a.a(context, "context must not be null.");
        if (VERSION.SDK_INT < 15) {
            return 21;
        }
        com.huawei.hms.c.e eVar = new com.huawei.hms.c.e(context);
        com.huawei.hms.c.e.a a = eVar.a(HuaweiApiAvailability.SERVICES_PACKAGE);
        if (com.huawei.hms.c.e.a.NOT_INSTALLED.equals(a)) {
            return 1;
        }
        if (com.huawei.hms.c.e.a.DISABLED.equals(a)) {
            return 3;
        }
        if (!HuaweiApiAvailability.SERVICES_SIGNATURE.equalsIgnoreCase(eVar.d(HuaweiApiAvailability.SERVICES_PACKAGE))) {
            return 9;
        }
        if (eVar.b(HuaweiApiAvailability.SERVICES_PACKAGE) < 20502300) {
            return 2;
        }
        return 0;
    }
}
