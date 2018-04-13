package com.tencent.tinker.loader.hotplug.interceptor;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.tencent.tinker.loader.hotplug.EnvConsts;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServiceBinderInterceptor extends Interceptor<IBinder> {
    private static final ClassLoader MY_CLASSLOADER = ServiceBinderInterceptor.class.getClassLoader();
    private static final String TAG = "Tinker.SvcBndrIntrcptr";
    private static Method sGetServiceMethod;
    private static Field sSCacheField;
    private static Class<?> sServiceManagerClazz;
    private final Context mBaseContext;
    private final BinderInvocationHandler mBinderInvocationHandler;
    private final String mServiceName;

    public interface BinderInvocationHandler {
        Object invoke(Object obj, Method method, Object[] objArr) throws Throwable;
    }

    private static class FakeClientBinderHandler implements InvocationHandler {
        private final BinderInvocationHandler a;
        private final IBinder b;

        FakeClientBinderHandler(IBinder iBinder, BinderInvocationHandler binderInvocationHandler) {
            this.b = iBinder;
            this.a = binderInvocationHandler;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (!"queryLocalInterface".equals(method.getName())) {
                return method.invoke(this.b, objArr);
            }
            String interfaceDescriptor = this.b.getInterfaceDescriptor();
            if (interfaceDescriptor.equals("android.app.IActivityManager")) {
                interfaceDescriptor = "android.app.ActivityManagerNative";
            } else {
                interfaceDescriptor = interfaceDescriptor + "$Stub";
            }
            IInterface iInterface = (IInterface) ShareReflectUtil.findMethod(Class.forName(interfaceDescriptor), "asInterface", IBinder.class).invoke(null, new Object[]{this.b});
            return ServiceBinderInterceptor.createProxy(iInterface.getClass().getClassLoader(), ServiceBinderInterceptor.getAllInterfacesThroughDeriveChain(iInterface.getClass()), new FakeInterfaceHandler(iInterface, (IBinder) obj, this.a));
        }
    }

    private static class FakeInterfaceHandler implements InvocationHandler {
        private final BinderInvocationHandler a;
        private final IBinder b;
        private final IInterface c;

        FakeInterfaceHandler(IInterface iInterface, IBinder iBinder, BinderInvocationHandler binderInvocationHandler) {
            this.c = iInterface;
            this.b = iBinder;
            this.a = binderInvocationHandler;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if ("asBinder".equals(method.getName())) {
                return this.b;
            }
            return this.a.invoke(this.c, method, objArr);
        }
    }

    static {
        sServiceManagerClazz = null;
        sSCacheField = null;
        sGetServiceMethod = null;
        synchronized (ServiceBinderInterceptor.class) {
            if (sServiceManagerClazz == null) {
                try {
                    sServiceManagerClazz = Class.forName("android.os.ServiceManager");
                    sSCacheField = ShareReflectUtil.findField(sServiceManagerClazz, "sCache");
                    sGetServiceMethod = ShareReflectUtil.findMethod(sServiceManagerClazz, "getService", String.class);
                } catch (Throwable th) {
                    Log.e(TAG, "unexpected exception.", th);
                }
            }
        }
    }

    public ServiceBinderInterceptor(Context context, String str, BinderInvocationHandler binderInvocationHandler) {
        Context context2 = context;
        while (context2 != null && (context2 instanceof ContextWrapper)) {
            context2 = ((ContextWrapper) context2).getBaseContext();
        }
        this.mBaseContext = context2;
        this.mServiceName = str;
        this.mBinderInvocationHandler = binderInvocationHandler;
    }

    @Nullable
    protected IBinder fetchTarget() throws Throwable {
        return (IBinder) sGetServiceMethod.invoke(null, new Object[]{this.mServiceName});
    }

    @NonNull
    protected IBinder decorate(@Nullable IBinder iBinder) throws Throwable {
        if (iBinder != null) {
            return ITinkerHotplugProxy.class.isAssignableFrom(iBinder.getClass()) ? iBinder : (IBinder) createProxy(iBinder.getClass().getClassLoader(), getAllInterfacesThroughDeriveChain(iBinder.getClass()), new FakeClientBinderHandler(iBinder, this.mBinderInvocationHandler));
        } else {
            throw new IllegalStateException("target is null.");
        }
    }

    protected void inject(@Nullable IBinder iBinder) throws Throwable {
        ((Map) sSCacheField.get(null)).put(this.mServiceName, iBinder);
        if ("activity".equals(this.mServiceName)) {
            fixAMSBinderCache(iBinder);
        } else if (EnvConsts.PACKAGE_MANAGER_SRVNAME.equals(this.mServiceName)) {
            fixPMSBinderCache(this.mBaseContext, iBinder);
        }
    }

    private static void fixAMSBinderCache(IBinder iBinder) throws Throwable {
        Object obj;
        try {
            obj = ShareReflectUtil.findField(Class.forName("android.app.ActivityManagerNative"), "gDefault").get(null);
        } catch (Throwable th) {
            obj = ShareReflectUtil.findField(Class.forName("android.app.ActivityManager"), "IActivityManagerSingleton").get(null);
        }
        Field findField = ShareReflectUtil.findField(obj, "mInstance");
        IInterface iInterface = (IInterface) findField.get(obj);
        if (iInterface != null && !ITinkerHotplugProxy.class.isAssignableFrom(iInterface.getClass())) {
            iInterface = iBinder.queryLocalInterface(iBinder.getInterfaceDescriptor());
            if (iInterface == null || !ITinkerHotplugProxy.class.isAssignableFrom(iInterface.getClass())) {
                throw new IllegalStateException("fakeBinder does not return fakeInterface, binder: " + iBinder + ", itf: " + iInterface);
            }
            findField.set(obj, iInterface);
        }
    }

    private static void fixPMSBinderCache(Context context, IBinder iBinder) throws Throwable {
        Field findField = ShareReflectUtil.findField(Class.forName("android.app.ActivityThread"), "sPackageManager");
        IInterface iInterface = (IInterface) findField.get(null);
        if (!(iInterface == null || ITinkerHotplugProxy.class.isAssignableFrom(iInterface.getClass()))) {
            iInterface = iBinder.queryLocalInterface(iBinder.getInterfaceDescriptor());
            if (iInterface == null || !ITinkerHotplugProxy.class.isAssignableFrom(iInterface.getClass())) {
                throw new IllegalStateException("fakeBinder does not return fakeInterface, binder: " + iBinder + ", itf: " + iInterface);
            }
            findField.set(null, iInterface);
        }
        findField = ShareReflectUtil.findField(Class.forName("android.app.ApplicationPackageManager"), "mPM");
        PackageManager packageManager = context.getPackageManager();
        iInterface = (IInterface) findField.get(packageManager);
        if (iInterface != null && !ITinkerHotplugProxy.class.isAssignableFrom(iInterface.getClass())) {
            iInterface = iBinder.queryLocalInterface(iBinder.getInterfaceDescriptor());
            if (iInterface == null || !ITinkerHotplugProxy.class.isAssignableFrom(iInterface.getClass())) {
                throw new IllegalStateException("fakeBinder does not return fakeInterface, binder: " + iBinder + ", itf: " + iInterface);
            }
            findField.set(packageManager, iInterface);
        }
    }

    private static <T> T createProxy(ClassLoader classLoader, Class<?>[] clsArr, InvocationHandler invocationHandler) {
        T newProxyInstance;
        RuntimeException runtimeException;
        Object obj = new Class[(clsArr.length + 1)];
        System.arraycopy(clsArr, 0, obj, 0, clsArr.length);
        obj[clsArr.length] = ITinkerHotplugProxy.class;
        try {
            newProxyInstance = Proxy.newProxyInstance(MY_CLASSLOADER, obj, invocationHandler);
        } catch (Throwable th) {
            runtimeException = new RuntimeException("cl: " + classLoader, th);
        }
        return newProxyInstance;
    }

    private static Class<?>[] getAllInterfacesThroughDeriveChain(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        Set hashSet = new HashSet(10);
        while (!Object.class.equals(cls)) {
            hashSet.addAll(Arrays.asList(cls.getInterfaces()));
            cls = cls.getSuperclass();
        }
        return (Class[]) hashSet.toArray(new Class[hashSet.size()]);
    }
}
