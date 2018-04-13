package com.ali.auth.third.core.registry.impl;

import com.ali.auth.third.core.message.Message;
import com.ali.auth.third.core.model.Constants;
import com.ali.auth.third.core.registry.ServiceRegistration;
import com.ali.auth.third.core.registry.ServiceRegistry;
import com.ali.auth.third.core.trace.SDKLogger;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class ProxyEnabledServiceRegistryDelegator implements ServiceRegistry {
    private ServiceRegistry delegator;

    public ProxyEnabledServiceRegistryDelegator(ServiceRegistry serviceRegistry) {
        this.delegator = serviceRegistry;
    }

    public ServiceRegistration registerService(Class<?>[] clsArr, Object obj, Map<String, String> map) {
        return this.delegator.registerService(clsArr, obj, map);
    }

    public <T> T getService(final Class<T> cls, final Map<String, String> map) {
        T service = this.delegator.getService(cls, map);
        if (service == null && map != null) {
            if (Constants.SERVICE_SCOPE_FLAG_VALUE.equals((String) map.get(Constants.ISV_SCOPE_FLAG)) && cls.isInterface()) {
                return cls.cast(Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{cls}, new InvocationHandler() {
                    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                        Object service = ProxyEnabledServiceRegistryDelegator.this.delegator.getService(cls, map);
                        if (service != null) {
                            return method.invoke(service, objArr);
                        }
                        Object[] objArr2 = new Object[2];
                        objArr2[0] = cls.getName();
                        objArr2[1] = map != null ? map.toString() : "";
                        SDKLogger.e("kernel", Message.create(17, objArr2).toString());
                        return null;
                    }
                }));
            }
        }
        return service;
    }

    public <T> T[] getServices(Class<T> cls, Map<String, String> map) {
        return this.delegator.getServices(cls, map);
    }

    public Object unregisterService(ServiceRegistration serviceRegistration) {
        return this.delegator.unregisterService(serviceRegistration);
    }
}
