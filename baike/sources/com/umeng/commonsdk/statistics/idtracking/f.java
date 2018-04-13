package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

public class f extends a {
    private Context a;

    public f(Context context) {
        super("imei");
        this.a = context;
    }

    public String f() {
        return DeviceConfig.getImeiNew(this.a);
    }
}
