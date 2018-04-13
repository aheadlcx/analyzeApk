package com.tencent.tinker.loader.hotplug.handler;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.os.Parcelable;
import com.tencent.tinker.loader.hotplug.ActivityStubManager;
import com.tencent.tinker.loader.hotplug.EnvConsts;
import com.tencent.tinker.loader.hotplug.IncrementComponentManager;
import com.tencent.tinker.loader.hotplug.interceptor.ServiceBinderInterceptor.BinderInvocationHandler;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.lang.reflect.Method;

public class AMSInterceptHandler implements BinderInvocationHandler {
    private static final int INTENT_SENDER_ACTIVITY;
    private static final String TAG = "Tinker.AMSIntrcptHndlr";
    private static final int[] TRANSLUCENT_ATTR_ID = new int[]{16842840};
    private final Context mContext;

    static {
        int intValue;
        try {
            intValue = ((Integer) ShareReflectUtil.findField(ActivityManager.class, "INTENT_SENDER_ACTIVITY").get(null)).intValue();
        } catch (Throwable th) {
            th.printStackTrace();
            intValue = 2;
        }
        INTENT_SENDER_ACTIVITY = intValue;
    }

    public AMSInterceptHandler(Context context) {
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

    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        String name = method.getName();
        if ("startActivity".equals(name)) {
            return handleStartActivity(obj, method, objArr);
        }
        if ("startActivities".equals(name)) {
            return handleStartActivities(obj, method, objArr);
        }
        if ("startActivityAndWait".equals(name)) {
            return handleStartActivity(obj, method, objArr);
        }
        if ("startActivityWithConfig".equals(name)) {
            return handleStartActivity(obj, method, objArr);
        }
        if ("startActivityAsUser".equals(name)) {
            return handleStartActivity(obj, method, objArr);
        }
        if ("getIntentSender".equals(name)) {
            return handleGetIntentSender(obj, method, objArr);
        }
        return method.invoke(obj, objArr);
    }

    private Object handleStartActivity(Object obj, Method method, Object[] objArr) throws Throwable {
        int i;
        for (int i2 = 0; i2 < objArr.length; i2++) {
            if (objArr[i2] instanceof Intent) {
                i = i2;
                break;
            }
        }
        i = -1;
        if (i != -1) {
            Intent intent = new Intent((Intent) objArr[i]);
            processActivityIntent(intent);
            objArr[i] = intent;
        }
        return method.invoke(obj, objArr);
    }

    private Object handleStartActivities(Object obj, Method method, Object[] objArr) throws Throwable {
        int i = 0;
        int i2 = 0;
        while (i2 < objArr.length) {
            if (objArr[i2] instanceof Intent[]) {
                break;
            }
            i2++;
        }
        i2 = -1;
        if (i2 != -1) {
            Intent[] intentArr = (Intent[]) objArr[i2];
            while (i < intentArr.length) {
                Intent intent = new Intent(intentArr[i]);
                processActivityIntent(intent);
                intentArr[i] = intent;
                i++;
            }
        }
        return method.invoke(obj, objArr);
    }

    private Object handleGetIntentSender(Object obj, Method method, Object[] objArr) throws Throwable {
        int i;
        int i2 = 0;
        for (int i3 = 0; i3 < objArr.length; i3++) {
            if (objArr[i3] instanceof Intent[]) {
                i = i3;
                break;
            }
        }
        i = -1;
        if (i != -1 && ((Integer) objArr[0]).intValue() == INTENT_SENDER_ACTIVITY) {
            Intent[] intentArr = (Intent[]) objArr[i];
            while (i2 < intentArr.length) {
                Intent intent = new Intent(intentArr[i2]);
                processActivityIntent(intent);
                intentArr[i2] = intent;
                i2++;
            }
        }
        return method.invoke(obj, objArr);
    }

    private void processActivityIntent(Intent intent) {
        String packageName;
        String str = null;
        if (intent.getComponent() != null) {
            packageName = intent.getComponent().getPackageName();
            str = intent.getComponent().getClassName();
        } else {
            ResolveInfo resolveIntent;
            ResolveInfo resolveActivity = this.mContext.getPackageManager().resolveActivity(intent, 0);
            if (resolveActivity == null) {
                resolveIntent = IncrementComponentManager.resolveIntent(intent);
            } else {
                resolveIntent = resolveActivity;
            }
            if (resolveIntent == null || resolveIntent.filter == null || !resolveIntent.filter.hasCategory("android.intent.category.DEFAULT")) {
                packageName = null;
            } else {
                packageName = resolveIntent.activityInfo.packageName;
                str = resolveIntent.activityInfo.name;
            }
        }
        if (IncrementComponentManager.isIncrementActivity(str)) {
            ActivityInfo queryActivityInfo = IncrementComponentManager.queryActivityInfo(str);
            storeAndReplaceOriginalComponentName(intent, packageName, str, ActivityStubManager.assignStub(str, queryActivityInfo.launchMode, hasTransparentTheme(queryActivityInfo)));
        }
    }

    private void storeAndReplaceOriginalComponentName(Intent intent, String str, String str2, String str3) {
        Parcelable componentName = new ComponentName(str, str2);
        ShareIntentUtil.fixIntentClassLoader(intent, this.mContext.getClassLoader());
        intent.putExtra(EnvConsts.INTENT_EXTRA_OLD_COMPONENT, componentName);
        intent.setComponent(new ComponentName(str, str3));
    }

    private boolean hasTransparentTheme(ActivityInfo activityInfo) {
        boolean z = false;
        int themeResource = activityInfo.getThemeResource();
        Theme newTheme = this.mContext.getResources().newTheme();
        newTheme.applyStyle(themeResource, true);
        TypedArray typedArray = null;
        try {
            typedArray = newTheme.obtainStyledAttributes(TRANSLUCENT_ATTR_ID);
            z = typedArray.getBoolean(0, false);
            if (typedArray != null) {
                typedArray.recycle();
            }
        } catch (Throwable th) {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
        return z;
    }
}
