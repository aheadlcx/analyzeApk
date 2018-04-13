package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

public class b extends a {
    private Context a;

    public b(Context context) {
        super("android_id");
        this.a = context;
    }

    public String f() {
        return DeviceConfig.getAndroidId(this.a);
    }
}
