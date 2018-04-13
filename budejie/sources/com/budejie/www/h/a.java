package com.budejie.www.h;

import android.content.Context;
import android.util.Log;
import com.budejie.www.util.an;
import com.ixintui.pushsdk.PushSdkApi;

public class a {
    private static a g;
    private final int a = 1356376913;
    private final String b = "2882303761517131207";
    private final String c = "5291713112207";
    private final String d = "baidupush_switch";
    private final String e = "mipush_switch";
    private final String f = "com.xiaomi.mipushdemo";

    private a() {
    }

    public static a a() {
        if (g == null) {
            g = new a();
        }
        return g;
    }

    public void a(Context context) {
        if (!an.m(context, "mipush_switch")) {
            Log.d("PushReceiverOfIxin", "注册爱心推");
            PushSdkApi.register(context, 1356376913, "xiaomi", "6.9.2");
            PushSdkApi.isSuspended(context);
        }
    }
}
