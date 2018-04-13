package com.huawei.hms.update.e;

import android.app.Activity;
import android.content.Context;
import com.huawei.hms.c.d;
import com.huawei.hms.c.g;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.meizu.cloud.pushsdk.platform.pushstrategy.Strategy;
import com.tencent.tinker.loader.hotplug.EnvConsts;
import java.util.HashMap;
import java.util.Map;

public abstract class a {
    abstract void a(b bVar);

    abstract void b(b bVar);

    abstract Activity c();

    protected void a(int i, int i2) {
        Context c = c();
        if (c != null && !c.isFinishing()) {
            Map hashMap = new HashMap();
            hashMap.put(EnvConsts.PACKAGE_MANAGER_SRVNAME, c.getPackageName());
            hashMap.put("sdk_ver", String.valueOf(20502300));
            hashMap.put(Strategy.APP_ID, g.a(c));
            hashMap.put("trigger_api", com.huawei.hms.update.c.a.b());
            hashMap.put("hms_ver", String.valueOf(com.huawei.hms.update.c.a.a()));
            hashMap.put("update_type", String.valueOf(i2));
            hashMap.put(SpeechConstant.NET_TYPE, String.valueOf(d.a(c)));
            hashMap.put(SpeechUtility.TAG_RESOURCE_RESULT, String.valueOf(i));
            com.huawei.hms.support.b.a.a().a(c, "HMS_SDK_UPDATE", hashMap);
        }
    }
}
