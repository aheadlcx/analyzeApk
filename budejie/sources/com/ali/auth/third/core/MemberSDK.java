package com.ali.auth.third.core;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.ali.auth.third.core.callback.InitResultCallback;
import com.ali.auth.third.core.callback.ResultCallback;
import com.ali.auth.third.core.config.AuthOption;
import com.ali.auth.third.core.config.ConfigManager;
import com.ali.auth.third.core.config.Environment;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.model.Constants;
import com.ali.auth.third.core.task.InitTask;
import com.ali.auth.third.core.util.CommonUtils;
import java.util.Collections;
import java.util.Map;

public class MemberSDK {
    private static final Map<String, String> USER_SERVICE_FILTER = Collections.singletonMap(Constants.ISV_SCOPE_FLAG, Constants.SERVICE_SCOPE_FLAG_VALUE);
    private static Environment env;
    public static String ttid;

    public static void setTtid(String str) {
        ttid = str;
    }

    public static void setEnvironment(Environment environment) {
        env = environment;
    }

    public static void init(Context context, InitResultCallback initResultCallback) {
        internalAsyncInit(context, initResultCallback);
    }

    private static InitTask internalAsyncInit(Context context, InitResultCallback initResultCallback) {
        KernelContext.context = context.getApplicationContext();
        if (env == null) {
            env = Environment.ONLINE;
        }
        Object initTask = new InitTask(initResultCallback, Integer.valueOf(env.ordinal()));
        KernelContext.executorService.postHandlerTask(initTask);
        return initTask;
    }

    public static void turnOnDebug() {
        Log.w("LoginSDK", "************************************\nDebug is enabled, make sure to turn it off in the production environment\n************************************");
        ConfigManager.DEBUG = true;
    }

    @Deprecated
    public static void turnOffDebug() {
    }

    public static <T> T getService(Class<T> cls) {
        if (cls == null) {
            return null;
        }
        return KernelContext.serviceRegistry.getService(cls, USER_SERVICE_FILTER);
    }

    public static <T> void getService(Activity activity, Class<T> cls, ResultCallback<T> resultCallback) {
        CommonUtils.startInitWaitTask(activity, resultCallback, new MemberSDK$1(cls, resultCallback), "");
    }

    public static void setUUID(String str) {
        KernelContext.UUID = str;
    }

    public static void setAuthOption(AuthOption authOption) {
        KernelContext.authOption = authOption;
    }

    public static void setPackageName(String str) {
        KernelContext.packageName = str;
    }
}
