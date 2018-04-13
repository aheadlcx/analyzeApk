package com.ixintui.pushsdk;

import android.content.Context;
import com.ixintui.plugin.IPushSdkApi;
import com.ixintui.smartlink.a.a;
import java.security.InvalidParameterException;
import java.util.List;

public class PushSdkApi {
    private static IPushSdkApi api;

    private static boolean check(Context context) {
        if (api == null) {
            api = (IPushSdkApi) a.a(context, "com.ixintui.pushsdk.PushSdkApiImpl");
        }
        return api != null;
    }

    public static void onResume(Context context) {
        if (check(context)) {
            api.onResume(context);
        }
    }

    public static void onPause(Context context) {
        if (check(context)) {
            api.onPause(context);
        }
    }

    public static void register(Context context, int i, String str, String str2) {
        if (check(context)) {
            api.register(context, i, str, str2);
        }
    }

    public static void register(Context context, int i, String str, String str2, String str3, String str4) {
        if (check(context)) {
            api.register(context, i, str, str2, str3, str4);
        }
    }

    public static void resumePush(Context context) {
        if (check(context)) {
            api.resumePush(context);
        }
    }

    public static void suspendPush(Context context) {
        if (check(context)) {
            api.suspendPush(context);
        }
    }

    public static void addTags(Context context, List list) {
        if (check(context)) {
            api.addTags(context, list);
        }
    }

    public static void deleteTags(Context context, List list) {
        if (check(context)) {
            api.deleteTags(context, list);
        }
    }

    public static void bindAlias(Context context, String str) {
        if (!check(context)) {
            return;
        }
        if (str.getBytes().length > 40) {
            throw new InvalidParameterException("alias too long, it should be limited in 40 bytes");
        }
        api.bindAlias(context, str);
    }

    public static void unbindAlias(Context context, String str) {
        if (!check(context)) {
            return;
        }
        if (str.getBytes().length > 40) {
            throw new InvalidParameterException("alias too long, it should be limited in 40 bytes");
        }
        api.unbindAlias(context, str);
    }

    public static void listTags(Context context) {
        if (check(context)) {
            api.listTags(context);
        }
    }

    public static void isSuspended(Context context) {
        if (check(context)) {
            api.isSuspended(context);
        }
    }

    public static void enableStat(Context context, boolean z) {
        if (check(context)) {
            api.enableStat(context, z);
        }
    }

    public static String getSdkIntegrationInfo(Context context) {
        return a.d(context);
    }

    public static void configure(Context context, String str) {
        if (check(context)) {
            api.configure(context, str);
        }
    }

    public static void setBadgeModel(Context context, PushModel pushModel) {
        if (check(context)) {
            api.setBadgeModel(context, pushModel);
        }
    }

    public static void setBadgeNum(Context context, int i) {
        if (check(context)) {
            api.setBadgeNum(context, i);
        }
    }

    public static void incrBadge(Context context, int i) {
        if (check(context)) {
            api.incrBadge(context, i);
        }
    }

    public static void clearBadge(Context context) {
        if (check(context)) {
            api.clearBadge(context);
        }
    }

    public static void openMIBadge(Context context, boolean z) {
        if (check(context)) {
            api.openMIBadge(context, z);
        }
    }

    public static PushModel getBadgeModel(Context context) {
        if (check(context)) {
            return api.getBadgeModel(context);
        }
        return null;
    }

    public static boolean onAppEaseEvent(Context context, String str, int i, int i2) {
        if (check(context)) {
            return api.onAppEaseEvent(context, str, i, i2);
        }
        return false;
    }
}
