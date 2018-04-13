package com.ali.auth.third.core.context;

import android.content.Context;
import com.ali.auth.third.core.WebViewProxy;
import com.ali.auth.third.core.config.AuthOption;
import com.ali.auth.third.core.config.ConfigManager;
import com.ali.auth.third.core.config.Environment;
import com.ali.auth.third.core.registry.ServiceRegistration;
import com.ali.auth.third.core.registry.ServiceRegistry;
import com.ali.auth.third.core.registry.impl.DefaultServiceRegistry;
import com.ali.auth.third.core.registry.impl.ProxyEnabledServiceRegistryDelegator;
import com.ali.auth.third.core.service.CredentialService;
import com.ali.auth.third.core.service.MemberExecutorService;
import com.ali.auth.third.core.service.RpcService;
import com.ali.auth.third.core.service.StorageService;
import com.ali.auth.third.core.service.UserTrackerService;
import com.ali.auth.third.core.service.impl.ExecutorServiceImpl;
import com.ali.auth.third.core.util.SystemUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class KernelContext {
    public static final String SDK_VERSION_MINI = ("a_" + ConfigManager.SDK_VERSION.toString() + "-mini");
    public static final String SDK_VERSION_STD = ("a_" + ConfigManager.SDK_VERSION.toString() + "-std");
    public static final String TAG = "kernel";
    public static String UUID;
    public static AuthOption authOption = AuthOption.NORMAL;
    public static volatile Context context;
    public static CredentialService credentialService;
    public static MemberExecutorService executorService = new ExecutorServiceImpl();
    public static final ReentrantLock initLock = new ReentrantLock();
    public static volatile Boolean isInitOk;
    public static boolean isMini = true;
    public static WebViewProxy mWebViewProxy;
    public static String packageName;
    public static String sdkVersion = SDK_VERSION_MINI;
    public static volatile ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
    public static StorageService storageService;
    public static volatile boolean syncInitialized;
    public static long timestamp;

    public static Boolean checkInitStatus() {
        try {
            initLock.lock();
            Boolean bool = isInitOk;
            return bool;
        } finally {
            initLock.unlock();
        }
    }

    public static void wrapServiceRegistry() {
        if (!(serviceRegistry instanceof ProxyEnabledServiceRegistryDelegator)) {
            serviceRegistry = new ProxyEnabledServiceRegistryDelegator(serviceRegistry);
        }
    }

    public static String getAppKey() {
        return storageService.getAppKey();
    }

    public static ServiceRegistration registerService(Class<?>[] clsArr, Object obj, Map<String, String> map) {
        return serviceRegistry.registerService(clsArr, obj, map == null ? new HashMap() : new HashMap(map));
    }

    public static <T> T getService(Class<T> cls, Map<String, String> map) {
        return serviceRegistry.getService(cls, map);
    }

    public static Environment getEnvironment() {
        return ConfigManager.getInstance().getEnvironment();
    }

    public static <T> T getService(Class<T> cls) {
        return serviceRegistry.getService(cls, null);
    }

    public static <T> T[] getServices(Class<T> cls) {
        return serviceRegistry.getServices(cls, null);
    }

    public static synchronized Context getApplicationContext() {
        Context context;
        synchronized (KernelContext.class) {
            if (context != null) {
                context = context;
            } else {
                context = SystemUtils.getSystemApp();
            }
        }
        return context;
    }

    public static boolean checkServiceValid() {
        if (context == null || serviceRegistry == null || getServices(RpcService.class) == null || getServices(StorageService.class) == null || getServices(UserTrackerService.class) == null || getServices(CredentialService.class) == null || storageService == null || credentialService == null) {
            return false;
        }
        return true;
    }
}
