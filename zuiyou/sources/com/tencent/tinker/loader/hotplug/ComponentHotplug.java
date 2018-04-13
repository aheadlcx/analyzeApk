package com.tencent.tinker.loader.hotplug;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.hotplug.handler.AMSInterceptHandler;
import com.tencent.tinker.loader.hotplug.handler.MHMessageHandler;
import com.tencent.tinker.loader.hotplug.handler.PMSInterceptHandler;
import com.tencent.tinker.loader.hotplug.interceptor.HandlerMessageInterceptor;
import com.tencent.tinker.loader.hotplug.interceptor.ServiceBinderInterceptor;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;

public final class ComponentHotplug {
    private static final String TAG = "Tinker.ComponentHotplug";
    private static ServiceBinderInterceptor sAMSInterceptor;
    private static volatile boolean sInstalled = false;
    private static HandlerMessageInterceptor sMHMessageInterceptor;
    private static ServiceBinderInterceptor sPMSInterceptor;

    public static synchronized void install(TinkerApplication tinkerApplication, ShareSecurityCheck shareSecurityCheck) throws UnsupportedEnvironmentException {
        synchronized (ComponentHotplug.class) {
            if (!sInstalled) {
                try {
                    if (IncrementComponentManager.init(tinkerApplication, shareSecurityCheck)) {
                        sAMSInterceptor = new ServiceBinderInterceptor(tinkerApplication, "activity", new AMSInterceptHandler(tinkerApplication));
                        sPMSInterceptor = new ServiceBinderInterceptor(tinkerApplication, EnvConsts.PACKAGE_MANAGER_SRVNAME, new PMSInterceptHandler());
                        sMHMessageInterceptor = new HandlerMessageInterceptor(fetchMHInstance(tinkerApplication), new MHMessageHandler(tinkerApplication));
                        sAMSInterceptor.install();
                        sPMSInterceptor.install();
                        sMHMessageInterceptor.install();
                        sInstalled = true;
                        Log.i(TAG, "installed successfully.");
                    }
                } catch (Throwable th) {
                    uninstall();
                    UnsupportedEnvironmentException unsupportedEnvironmentException = new UnsupportedEnvironmentException(th);
                }
            }
        }
    }

    public static synchronized void ensureComponentHotplugInstalled(TinkerApplication tinkerApplication) throws UnsupportedEnvironmentException {
        synchronized (ComponentHotplug.class) {
            if (sInstalled) {
                try {
                    sAMSInterceptor.install();
                    sPMSInterceptor.install();
                    sMHMessageInterceptor.install();
                } catch (Throwable th) {
                    uninstall();
                    UnsupportedEnvironmentException unsupportedEnvironmentException = new UnsupportedEnvironmentException(th);
                }
            } else {
                Log.i(TAG, "method install() is not invoked, ignore ensuring operations.");
            }
        }
    }

    private static Handler fetchMHInstance(Context context) {
        Object activityThread = ShareReflectUtil.getActivityThread(context, null);
        if (activityThread == null) {
            throw new IllegalStateException("failed to fetch instance of ActivityThread.");
        }
        try {
            return (Handler) ShareReflectUtil.findField(activityThread, "mH").get(activityThread);
        } catch (Throwable th) {
            IllegalStateException illegalStateException = new IllegalStateException(th);
        }
    }

    public static synchronized void uninstall() {
        synchronized (ComponentHotplug.class) {
            if (sInstalled) {
                try {
                    sAMSInterceptor.uninstall();
                    sPMSInterceptor.uninstall();
                    sMHMessageInterceptor.uninstall();
                } catch (Throwable th) {
                    Log.e(TAG, "exception when uninstall.", th);
                }
                sInstalled = false;
            }
        }
    }

    private ComponentHotplug() {
        throw new UnsupportedOperationException();
    }
}
