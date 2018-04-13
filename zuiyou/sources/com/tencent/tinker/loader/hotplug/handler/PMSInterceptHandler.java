package com.tencent.tinker.loader.hotplug.handler;

import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import com.tencent.tinker.loader.hotplug.IncrementComponentManager;
import com.tencent.tinker.loader.hotplug.interceptor.ServiceBinderInterceptor.BinderInvocationHandler;
import java.lang.reflect.Method;

public class PMSInterceptHandler implements BinderInvocationHandler {
    private static final String TAG = "Tinker.PMSIntrcptHndlr";

    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        String name = method.getName();
        if ("getActivityInfo".equals(name)) {
            return handleGetActivityInfo(obj, method, objArr);
        }
        if ("resolveIntent".equals(name)) {
            return handleResolveIntent(obj, method, objArr);
        }
        return method.invoke(obj, objArr);
    }

    private Object handleGetActivityInfo(Object obj, Method method, Object[] objArr) throws Throwable {
        Throwable targetException;
        Class[] exceptionTypes = method.getExceptionTypes();
        try {
            Object invoke = method.invoke(obj, objArr);
            if (invoke != null) {
                return invoke;
            }
            ComponentName componentName;
            for (int i = 0; i < objArr.length; i++) {
                if (objArr[i] instanceof ComponentName) {
                    Log.i(TAG, "locate componentName field of " + method.getName() + " done at idx: " + i);
                    componentName = (ComponentName) objArr[i];
                    break;
                }
            }
            componentName = null;
            if (componentName != null) {
                return IncrementComponentManager.queryActivityInfo(componentName.getClassName());
            }
            Log.w(TAG, "failed to locate componentName field of " + method.getName() + ", notice any crashes or mistakes after resolve works.");
            return null;
        } catch (Throwable e) {
            targetException = e.getTargetException();
            if (exceptionTypes == null || exceptionTypes.length <= 0) {
                String str = TAG;
                String str2 = "unexpected exception.";
                if (targetException == null) {
                    targetException = e;
                }
                Log.e(str, str2, targetException);
                return null;
            }
            if (targetException == null) {
                targetException = e;
            }
            throw targetException;
        } catch (Throwable targetException2) {
            Log.e(TAG, "unexpected exception.", targetException2);
            return null;
        }
    }

    private Object handleResolveIntent(Object obj, Method method, Object[] objArr) throws Throwable {
        Throwable targetException;
        Class[] exceptionTypes = method.getExceptionTypes();
        try {
            Object invoke = method.invoke(obj, objArr);
            if (invoke != null) {
                return invoke;
            }
            Intent intent;
            Log.w(TAG, "failed to resolve activity in base package, try again in patch package.");
            for (int i = 0; i < objArr.length; i++) {
                if (objArr[i] instanceof Intent) {
                    Log.i(TAG, "locate intent field of " + method.getName() + " done at idx: " + i);
                    intent = (Intent) objArr[i];
                    break;
                }
            }
            intent = null;
            if (intent != null) {
                return IncrementComponentManager.resolveIntent(intent);
            }
            Log.w(TAG, "failed to locate intent field of " + method.getName() + ", notice any crashes or mistakes after resolve works.");
            return null;
        } catch (Throwable e) {
            targetException = e.getTargetException();
            if (exceptionTypes == null || exceptionTypes.length <= 0) {
                String str = TAG;
                String str2 = "unexpected exception.";
                if (targetException == null) {
                    targetException = e;
                }
                Log.e(str, str2, targetException);
                return null;
            }
            if (targetException == null) {
                targetException = e;
            }
            throw targetException;
        } catch (Throwable targetException2) {
            Log.e(TAG, "unexpected exception.", targetException2);
            return null;
        }
    }
}
