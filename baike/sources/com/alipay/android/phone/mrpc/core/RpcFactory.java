package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Proxy;

public class RpcFactory {
    private Config a;
    private RpcInvoker b = new RpcInvoker(this);

    public RpcFactory(Config config) {
        this.a = config;
    }

    public Config getConfig() {
        return this.a;
    }

    public <T> T getRpcProxy(Class<T> cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new RpcInvocationHandler(this.a, cls, this.b));
    }
}
