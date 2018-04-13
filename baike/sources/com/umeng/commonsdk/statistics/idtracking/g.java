package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.proguard.b;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

public class g extends a {
    private Context a;

    public g(Context context) {
        super("mac");
        this.a = context;
    }

    public String f() {
        String str = null;
        try {
            str = DeviceConfig.getMac(this.a);
        } catch (Throwable e) {
            if (AnalyticsConstants.UM_DEBUG) {
                e.printStackTrace();
            }
            b.a(this.a, e);
        }
        return str;
    }
}
