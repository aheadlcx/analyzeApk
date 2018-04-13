package com.taobao.tao.remotebusiness.listener;

import com.taobao.tao.remotebusiness.MtopBusiness;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import mtopsdk.mtop.common.k;

final class a implements InvocationHandler {
    private MtopFinishListenerImpl a;
    private MtopProgressListenerImpl b;
    private MtopBusiness c;
    private k d;

    public a(MtopBusiness mtopBusiness, k kVar) {
        this.a = new MtopFinishListenerImpl(mtopBusiness, kVar);
        this.c = mtopBusiness;
        this.d = kVar;
    }

    public final Object invoke(Object obj, Method method, Object[] objArr) {
        if (method.getName().equals("onFinished")) {
            return method.invoke(this.a, objArr);
        }
        if (!method.getName().equals("onDataReceived") && !method.getName().equals("onHeader")) {
            return null;
        }
        if (this.b == null) {
            this.b = new MtopProgressListenerImpl(this.c, this.d);
        }
        return method.invoke(this.b, objArr);
    }
}
