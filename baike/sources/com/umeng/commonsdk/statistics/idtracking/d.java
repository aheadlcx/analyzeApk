package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.proguard.g;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

public class d extends a {
    private Context a;

    public d(Context context) {
        super(g.u);
        this.a = context;
    }

    public String f() {
        return DeviceConfig.getDeviceIdUmengMD5(this.a);
    }
}
