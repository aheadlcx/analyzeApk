package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.Bugly;

public class PrefOperate {
    public static void loadMetaDataConfig(Context context) {
        SendStrategyEnum sendStrategyEnum = SendStrategyEnum.APP_START;
        try {
            CharSequence a = de.a(context, Config.EXCEPTION_LOG_META_NAME);
            if (!TextUtils.isEmpty(a) && "true".equals(a)) {
                bt.a().a(context, false);
            }
        } catch (Throwable e) {
            db.a(e);
        }
        try {
            String a2 = de.a(context, Config.SEND_STRATEGY_META_NAME);
            if (!TextUtils.isEmpty(a2)) {
                if (a2.equals(SendStrategyEnum.APP_START.name())) {
                    sendStrategyEnum = SendStrategyEnum.APP_START;
                    bj.a().a(context, sendStrategyEnum.ordinal());
                } else if (a2.equals(SendStrategyEnum.ONCE_A_DAY.name())) {
                    sendStrategyEnum = SendStrategyEnum.ONCE_A_DAY;
                    bj.a().a(context, sendStrategyEnum.ordinal());
                    bj.a().b(context, 24);
                } else if (a2.equals(SendStrategyEnum.SET_TIME_INTERVAL.name())) {
                    sendStrategyEnum = SendStrategyEnum.SET_TIME_INTERVAL;
                    bj.a().a(context, sendStrategyEnum.ordinal());
                }
            }
        } catch (Throwable e2) {
            Throwable th = e2;
            SendStrategyEnum sendStrategyEnum2 = sendStrategyEnum;
            db.a(th);
            sendStrategyEnum = sendStrategyEnum2;
        }
        try {
            Object a3 = de.a(context, Config.TIME_INTERVAL_META_NAME);
            if (!TextUtils.isEmpty(a3)) {
                int parseInt = Integer.parseInt(a3);
                if (sendStrategyEnum.ordinal() == SendStrategyEnum.SET_TIME_INTERVAL.ordinal() && parseInt > 0 && parseInt <= 24) {
                    bj.a().b(context, parseInt);
                }
            }
        } catch (Throwable e3) {
            db.a(e3);
        }
        try {
            CharSequence a4 = de.a(context, Config.ONLY_WIFI_META_NAME);
            if (!TextUtils.isEmpty(a4)) {
                if ("true".equals(a4)) {
                    bj.a().a(context, true);
                } else if (Bugly.SDK_IS_DEV.equals(a4)) {
                    bj.a().a(context, false);
                }
            }
        } catch (Throwable e32) {
            db.a(e32);
        }
    }

    public static void setAppKey(String str) {
        CooperService.a().getHeadObject().f = str;
    }

    public static String getAppKey(Context context) {
        return CooperService.a().getAppKey(context);
    }

    public static void setAppChannel(String str) {
        if (str == null || str.equals("")) {
            db.c("设置的渠道不能为空或者为null || The channel that you have been set is null or empty, please check it.");
        }
        CooperService.a().getHeadObject().m = str;
    }

    public static void setAppChannel(Context context, String str, boolean z) {
        if (str == null || str.equals("")) {
            db.c("设置的渠道不能为空或者为null || The channel that you have been set is null or empty, please check it.");
        }
        CooperService.a().getHeadObject().m = str;
        if (!(!z || str == null || str.equals(""))) {
            bj.a().c(context, str);
            bj.a().b(context, true);
        }
        if (!z) {
            bj.a().c(context, "");
            bj.a().b(context, false);
        }
    }
}
