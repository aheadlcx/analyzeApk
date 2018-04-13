package com.tencent.tinker.loader.hotplug.handler;

import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import com.iflytek.aiui.AIUIConstant;
import com.tencent.tinker.loader.hotplug.EnvConsts;
import com.tencent.tinker.loader.hotplug.IncrementComponentManager;
import com.tencent.tinker.loader.hotplug.interceptor.HandlerMessageInterceptor.MessageHandler;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class MHMessageHandler implements MessageHandler {
    private static final int LAUNCH_ACTIVITY;
    private static final String TAG = "Tinker.MHMsgHndlr";
    private final Context mContext;

    static {
        int i;
        try {
            i = ShareReflectUtil.findField(Class.forName("android.app.ActivityThread$H"), "LAUNCH_ACTIVITY").getInt(null);
        } catch (Throwable th) {
            i = 100;
        }
        LAUNCH_ACTIVITY = i;
    }

    public MHMessageHandler(Context context) {
        Context context2 = context;
        while (context2 instanceof ContextWrapper) {
            context = ((ContextWrapper) context2).getBaseContext();
            if (context == null) {
                break;
            }
            context2 = context;
        }
        this.mContext = context2;
    }

    public boolean handleMessage(Message message) {
        if (message.what == LAUNCH_ACTIVITY) {
            try {
                Object obj = message.obj;
                if (obj == null) {
                    Log.w(TAG, "msg: [" + message.what + "] has no 'obj' value.");
                } else {
                    Intent intent = (Intent) ShareReflectUtil.findField(obj, AIUIConstant.WORK_MODE_INTENT).get(obj);
                    if (intent == null) {
                        Log.w(TAG, "cannot fetch intent from message received by mH.");
                    } else {
                        ShareIntentUtil.fixIntentClassLoader(intent, this.mContext.getClassLoader());
                        ComponentName componentName = (ComponentName) intent.getParcelableExtra(EnvConsts.INTENT_EXTRA_OLD_COMPONENT);
                        if (componentName == null) {
                            Log.w(TAG, "oldComponent was null, start " + intent.getComponent() + " next.");
                        } else {
                            ActivityInfo activityInfo = (ActivityInfo) ShareReflectUtil.findField(obj, "activityInfo").get(obj);
                            if (activityInfo != null) {
                                ActivityInfo queryActivityInfo = IncrementComponentManager.queryActivityInfo(componentName.getClassName());
                                if (queryActivityInfo == null) {
                                    Log.e(TAG, "Failed to query target activity's info, perhaps the target is not hotpluged component. Target: " + componentName.getClassName());
                                } else {
                                    fixActivityScreenOrientation(obj, queryActivityInfo.screenOrientation);
                                    fixStubActivityInfo(activityInfo, queryActivityInfo);
                                    intent.setComponent(componentName);
                                    intent.removeExtra(EnvConsts.INTENT_EXTRA_OLD_COMPONENT);
                                }
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                Log.e(TAG, "exception in handleMessage.", th);
            }
        }
        return false;
    }

    private void fixStubActivityInfo(ActivityInfo activityInfo, ActivityInfo activityInfo2) {
        copyInstanceFields(activityInfo2, activityInfo);
    }

    private <T> void copyInstanceFields(T t, T t2) {
        if (t != null && t2 != null) {
            for (Class cls = t.getClass(); !cls.equals(Object.class); cls = cls.getSuperclass()) {
                for (Field field : cls.getDeclaredFields()) {
                    if (!(field.isSynthetic() || Modifier.isStatic(field.getModifiers()))) {
                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                        }
                        try {
                            field.set(t2, field.get(t));
                        } catch (Throwable th) {
                        }
                    }
                }
            }
        }
    }

    private void fixActivityScreenOrientation(Object obj, int i) {
        if (i == -1) {
            i = 2;
        }
        try {
            Object obj2 = ShareReflectUtil.findField(obj, "token").get(obj);
            Object invoke = ShareReflectUtil.findMethod(Class.forName("android.app.ActivityManagerNative"), "getDefault", new Class[0]).invoke(null, new Object[0]);
            ShareReflectUtil.findMethod(invoke, "setRequestedOrientation", IBinder.class, Integer.TYPE).invoke(invoke, new Object[]{obj2, Integer.valueOf(i)});
        } catch (Throwable th) {
            Log.e(TAG, "Failed to fix screen orientation.", th);
        }
    }
}
