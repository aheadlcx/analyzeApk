package com.ali.auth.third.ut;

import android.app.Application;
import com.ali.auth.third.core.config.ConfigManager;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.service.UserTrackerService;
import com.alipay.sdk.app.statistic.c;
import com.tencent.open.GameAppOperation;
import com.ut.mini.UTAnalytics;
import com.ut.mini.UTHitBuilders.UTCustomHitBuilder;
import com.ut.mini.core.sign.UTSecuritySDKRequestAuthentication;
import java.util.HashMap;
import java.util.Map;

public class UserTrackerImpl implements UserTrackerService {
    public UserTrackerImpl() {
        try {
            UTAnalytics.getInstance().turnOnDebug();
            UTAnalytics.getInstance().setContext(KernelContext.context);
            UTAnalytics.getInstance().setAppApplicationInstance((Application) KernelContext.context.getApplicationContext());
            UTAnalytics.getInstance().setRequestAuthentication(new UTSecuritySDKRequestAuthentication(KernelContext.getAppKey(), ""));
            baichuanReport();
        } catch (Exception e) {
        } catch (Throwable th) {
        }
    }

    private void baichuanReport() {
        UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("80001");
        Map hashMap = new HashMap();
        hashMap.put("model", c.d);
        hashMap.put(GameAppOperation.QQFAV_DATALINE_VERSION, ConfigManager.SDK_VERSION.toString());
        uTCustomHitBuilder.setProperties(hashMap);
        UTAnalytics.getInstance().getTracker("25").send(uTCustomHitBuilder.build());
    }

    public void send(String str, Map<String, String> map) {
        UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder(str);
        Map appInfo = getAppInfo();
        if (map != null) {
            appInfo.putAll(map);
        }
        uTCustomHitBuilder.setProperties(appInfo);
        UTAnalytics.getInstance().getTracker("onesdk").send(uTCustomHitBuilder.build());
    }

    private static Map<String, String> getAppInfo() {
        return new HashMap();
    }
}
