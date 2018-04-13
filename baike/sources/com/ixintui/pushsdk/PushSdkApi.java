package com.ixintui.pushsdk;

import android.content.Context;
import com.ixintui.plugin.IPushSdkApi;
import com.ixintui.pushsdk.a.a;
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

    public static void register(Context context, int i, String str, String str2) {
        if (check(context)) {
            api.register(context, i, str, str2);
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
        return a.c(context);
    }

    public static void configure(Context context, String str) {
        if (check(context)) {
            api.configure(context, str);
        }
    }
}
