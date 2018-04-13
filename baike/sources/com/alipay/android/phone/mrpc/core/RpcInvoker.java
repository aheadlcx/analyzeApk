package com.alipay.android.phone.mrpc.core;

import com.alipay.android.phone.mrpc.core.gwprotocol.Deserializer;
import com.alipay.android.phone.mrpc.core.gwprotocol.JsonDeserializer;
import com.alipay.android.phone.mrpc.core.gwprotocol.JsonSerializer;
import com.alipay.android.phone.mrpc.core.gwprotocol.Serializer;
import com.alipay.mobile.framework.service.annotation.OperationType;
import com.alipay.mobile.framework.service.annotation.ResetCookie;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class RpcInvoker {
    private static final ThreadLocal<Object> a = new ThreadLocal();
    private static final ThreadLocal<Map<String, Object>> b = new ThreadLocal();
    private byte c = (byte) 0;
    private AtomicInteger d;
    private RpcFactory e;

    public RpcInvoker(RpcFactory rpcFactory) {
        this.e = rpcFactory;
        this.d = new AtomicInteger();
    }

    private void a(Object obj, Class<?> cls, Method method, Object[] objArr, Annotation[] annotationArr) {
    }

    private void a(Object obj, byte[] bArr, Class<?> cls, Method method, Object[] objArr, Annotation[] annotationArr) {
    }

    private void a(Object obj, byte[] bArr, Class<?> cls, Method method, Object[] objArr, Annotation[] annotationArr, RpcException rpcException) {
        throw rpcException;
    }

    private byte[] a(Method method, Object[] objArr, String str, int i, boolean z) {
        Serializer serializer = getSerializer(i, str, objArr);
        if (b.get() != null) {
            serializer.setExtParam(b.get());
        }
        byte[] bArr = (byte[]) getTransport(method, i, str, serializer.packet(), z).call();
        b.set(null);
        return bArr;
    }

    public static void addProtocolArgs(String str, Object obj) {
        if (b.get() == null) {
            b.set(new HashMap());
        }
        ((Map) b.get()).put(str, obj);
    }

    public void batchBegin() {
        this.c = (byte) 1;
    }

    public FutureTask<?> batchCommit() {
        this.c = (byte) 0;
        return null;
    }

    public Deserializer getDeserializer(Type type, byte[] bArr) {
        return new JsonDeserializer(type, bArr);
    }

    public Serializer getSerializer(int i, String str, Object[] objArr) {
        return new JsonSerializer(i, str, objArr);
    }

    public RpcCaller getTransport(Method method, int i, String str, byte[] bArr, boolean z) {
        return new HttpCaller(this.e.getConfig(), method, i, str, bArr, z);
    }

    public Object invoke(Object obj, Class<?> cls, Method method, Object[] objArr) {
        RpcException e;
        if (ThreadUtil.checkMainThread()) {
            throw new IllegalThreadStateException("can't in main thread call rpc .");
        }
        OperationType operationType = (OperationType) method.getAnnotation(OperationType.class);
        boolean z = method.getAnnotation(ResetCookie.class) != null;
        Type genericReturnType = method.getGenericReturnType();
        Annotation[] annotations = method.getAnnotations();
        a.set(null);
        b.set(null);
        if (operationType == null) {
            throw new IllegalStateException("OperationType must be set.");
        }
        byte[] bArr;
        String value = operationType.value();
        int incrementAndGet = this.d.incrementAndGet();
        a(obj, (Class) cls, method, objArr, annotations);
        byte[] bArr2 = null;
        try {
            if (this.c == (byte) 0) {
                bArr2 = a(method, objArr, value, incrementAndGet, z);
                try {
                    Object parser = getDeserializer(genericReturnType, bArr2).parser();
                    if (genericReturnType != Void.TYPE) {
                        a.set(parser);
                    }
                } catch (RpcException e2) {
                    e = e2;
                    bArr = bArr2;
                    e.setOperationType(value);
                    a(obj, bArr, cls, method, objArr, annotations, e);
                    a(obj, bArr, cls, method, objArr, annotations);
                    return a.get();
                }
            }
            bArr = bArr2;
        } catch (RpcException e3) {
            e = e3;
            bArr = null;
            e.setOperationType(value);
            a(obj, bArr, cls, method, objArr, annotations, e);
            a(obj, bArr, cls, method, objArr, annotations);
            return a.get();
        }
        a(obj, bArr, cls, method, objArr, annotations);
        return a.get();
    }
}
