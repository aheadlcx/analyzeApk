package com.sina.weibo.sdk;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.sina.weibo.sdk.a.a;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.c;
import java.util.List;

public class b {
    private static boolean a = false;
    private static AuthInfo b;

    public static void a(Context context, AuthInfo authInfo) {
        if (!a) {
            if (authInfo == null || TextUtils.isEmpty(authInfo.getAppKey()) || TextUtils.isEmpty(authInfo.getRedirectUrl())) {
                throw new RuntimeException("please set right app info (appKey,redirect");
            }
            b = authInfo;
            a.a(context).a(b.getAppKey());
            a = true;
        }
    }

    public static void a() {
        if (!a) {
            throw new RuntimeException("weibo sdk was not initall! please use: WbSdk.install() in your app Application or your main Activity. when you want to use weibo sdk function, make sure call WbSdk.install() before this function");
        }
    }

    public static AuthInfo b() {
        a();
        return b;
    }

    public static boolean a(Context context) {
        Intent intent = new Intent("com.sina.weibo.action.sdkidentity");
        intent.addCategory("android.intent.category.DEFAULT");
        List queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean b(Context context) {
        if (!a(context)) {
            return false;
        }
        c a = c.a(context).a();
        if (a == null || a.c() < 10772) {
            return false;
        }
        return true;
    }
}
