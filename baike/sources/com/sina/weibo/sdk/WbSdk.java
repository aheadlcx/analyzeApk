package com.sina.weibo.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.utils.AidTask;

public class WbSdk {
    private static boolean a = false;
    private static AuthInfo b;

    public static void install(Context context, AuthInfo authInfo) {
        if (!a) {
            if (authInfo == null || TextUtils.isEmpty(authInfo.getAppKey()) || TextUtils.isEmpty(authInfo.getScope()) || TextUtils.isEmpty(authInfo.getRedirectUrl())) {
                throw new RuntimeException("please set right app info (appKey,Scope,redirect");
            }
            b = authInfo;
            AidTask.getInstance(context).aidTaskInit(b.getAppKey());
            a = true;
        }
    }

    public static void checkInit() {
        if (!a) {
            throw new RuntimeException("weibo sdk was not initall! please use: WbSdk.install() in your app Application or your main Activity. when you want to use weibo sdk function, make sure call WbSdk.install() before this function");
        }
    }

    public static AuthInfo getAuthInfo() {
        checkInit();
        return b;
    }
}
