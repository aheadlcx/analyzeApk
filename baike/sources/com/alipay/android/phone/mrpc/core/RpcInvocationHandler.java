package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RpcInvocationHandler implements InvocationHandler {
    protected Config a;
    protected Class<?> b;
    protected RpcInvoker c;

    public RpcInvocationHandler(Config config, Class<?> cls, RpcInvoker rpcInvoker) {
        this.a = config;
        this.b = cls;
        this.c = rpcInvoker;
    }

    public Object invoke(Object obj, Method method, Object[] objArr) {
        return this.c.invoke(obj, this.b, method, objArr);
    }
}
