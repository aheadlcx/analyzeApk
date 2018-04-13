package com.huawei.hms.support.api;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Pair;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.client.SubAppInfo;
import com.huawei.hms.support.api.entity.core.CommonCode.ErrorCode;
import com.huawei.hms.support.api.transport.DatagramTransport;
import com.iflytek.cloud.SpeechUtility;
import com.meizu.cloud.pushsdk.platform.pushstrategy.Strategy;
import com.tencent.tinker.loader.hotplug.EnvConsts;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class a<R extends Result, T extends IMessageEntity> extends PendingResult<R> {
    private CountDownLatch a;
    private R b = null;
    private WeakReference<ApiClient> c;
    private String d = null;
    private long e = 0;
    protected DatagramTransport transport = null;

    protected static class a<R extends Result> extends Handler {
        public a() {
            this(Looper.getMainLooper());
        }

        public a(Looper looper) {
            super(looper);
        }

        public void a(ResultCallback<? super R> resultCallback, R r) {
            sendMessage(obtainMessage(1, new Pair(resultCallback, r)));
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Pair pair = (Pair) message.obj;
                    b((ResultCallback) pair.first, (Result) pair.second);
                    return;
                default:
                    return;
            }
        }

        protected void b(ResultCallback<? super R> resultCallback, R r) {
            resultCallback.onResult(r);
        }
    }

    public abstract R onComplete(T t);

    public a(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
        this.d = str;
        a(apiClient, str, iMessageEntity, getResponseType());
    }

    public a(ApiClient apiClient, String str, IMessageEntity iMessageEntity, Class<T> cls) {
        a(apiClient, str, iMessageEntity, cls);
    }

    private void a(ApiClient apiClient, String str, IMessageEntity iMessageEntity, Class<T> cls) {
        Throwable e;
        if (apiClient == null) {
            throw new IllegalArgumentException("apiClient cannot be null.");
        }
        this.c = new WeakReference(apiClient);
        this.a = new CountDownLatch(1);
        try {
            this.transport = (DatagramTransport) Class.forName(apiClient.getTransportName()).getConstructor(new Class[]{String.class, IMessageEntity.class, Class.class}).newInstance(new Object[]{str, iMessageEntity, cls});
        } catch (InstantiationException e2) {
            e = e2;
            throw new IllegalStateException("Instancing transport exception, " + e.getMessage(), e);
        } catch (IllegalAccessException e3) {
            e = e3;
            throw new IllegalStateException("Instancing transport exception, " + e.getMessage(), e);
        } catch (IllegalArgumentException e4) {
            e = e4;
            throw new IllegalStateException("Instancing transport exception, " + e.getMessage(), e);
        } catch (InvocationTargetException e5) {
            e = e5;
            throw new IllegalStateException("Instancing transport exception, " + e.getMessage(), e);
        } catch (NoSuchMethodException e6) {
            e = e6;
            throw new IllegalStateException("Instancing transport exception, " + e.getMessage(), e);
        } catch (ClassNotFoundException e7) {
            e = e7;
            throw new IllegalStateException("Instancing transport exception, " + e.getMessage(), e);
        }
    }

    protected Class<T> getResponseType() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass != null) {
            genericSuperclass = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1];
            if (genericSuperclass != null) {
                return (Class) genericSuperclass;
            }
        }
        return null;
    }

    public final R await() {
        this.e = System.currentTimeMillis();
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("await must not be called on the UI thread");
        }
        ApiClient apiClient = (ApiClient) this.c.get();
        if (checkApiClient(apiClient)) {
            this.transport.a(apiClient, new b(this));
            try {
                this.a.await();
            } catch (InterruptedException e) {
                a(ErrorCode.INTERNAL_ERROR, null);
            }
            return this.b;
        }
        a(ErrorCode.CLIENT_API_INVALID, null);
        return this.b;
    }

    public R await(long j, TimeUnit timeUnit) {
        this.e = System.currentTimeMillis();
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("await must not be called on the UI thread");
        }
        ApiClient apiClient = (ApiClient) this.c.get();
        if (checkApiClient(apiClient)) {
            AtomicBoolean atomicBoolean = new AtomicBoolean();
            this.transport.b(apiClient, new c(this, atomicBoolean));
            try {
                if (!this.a.await(j, timeUnit)) {
                    atomicBoolean.set(true);
                    a(ErrorCode.EXECUTE_TIMEOUT, null);
                }
            } catch (InterruptedException e) {
                a(ErrorCode.INTERNAL_ERROR, null);
            }
            return this.b;
        }
        a(ErrorCode.CLIENT_API_INVALID, null);
        return this.b;
    }

    public final void setResultCallback(ResultCallback<R> resultCallback) {
        setResultCallback(Looper.getMainLooper(), resultCallback);
    }

    public final void setResultCallback(Looper looper, ResultCallback<R> resultCallback) {
        this.e = System.currentTimeMillis();
        if (looper == null) {
            looper = Looper.myLooper();
        }
        a aVar = new a(looper);
        ApiClient apiClient = (ApiClient) this.c.get();
        if (checkApiClient(apiClient)) {
            this.transport.b(apiClient, new d(this, aVar, resultCallback));
            return;
        }
        a(ErrorCode.CLIENT_API_INVALID, null);
        aVar.a(resultCallback, this.b);
    }

    private void a(int i, IMessageEntity iMessageEntity) {
        a(i);
        if (i <= 0) {
            this.b = onComplete(iMessageEntity);
        } else {
            this.b = onError(i);
        }
    }

    protected R onError(int i) {
        Class a;
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass != null) {
            genericSuperclass = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        } else {
            genericSuperclass = null;
        }
        if (genericSuperclass != null) {
            a = com.huawei.hms.support.a.a.a(genericSuperclass);
        } else {
            a = null;
        }
        if (a != null) {
            try {
                this.b = (Result) a.newInstance();
                this.b.setStatus(new Status(i));
            } catch (Exception e) {
                return null;
            }
        }
        return this.b;
    }

    protected boolean checkApiClient(ApiClient apiClient) {
        return apiClient != null && apiClient.isConnected();
    }

    private void a(int i) {
        ApiClient apiClient = (ApiClient) this.c.get();
        if (apiClient != null && this.d != null && this.e != 0) {
            Map hashMap = new HashMap();
            hashMap.put(EnvConsts.PACKAGE_MANAGER_SRVNAME, apiClient.getPackageName());
            hashMap.put("sdk_ver", String.valueOf(20502300));
            Object obj = null;
            SubAppInfo subAppInfo = apiClient.getSubAppInfo();
            if (subAppInfo != null) {
                obj = subAppInfo.getSubAppID();
            }
            if (obj == null) {
                obj = apiClient.getAppID();
            }
            hashMap.put(Strategy.APP_ID, obj);
            String[] split = this.d.split("\\.");
            if (split.length == 2) {
                hashMap.put(NotificationCompat.CATEGORY_SERVICE, split[0]);
                hashMap.put("api_name", split[1]);
            }
            hashMap.put(SpeechUtility.TAG_RESOURCE_RESULT, String.valueOf(i));
            hashMap.put("cost_time", String.valueOf(System.currentTimeMillis() - this.e));
            com.huawei.hms.support.b.a.a().a(apiClient.getContext(), "HMS_SDK_API_CALL", hashMap);
        }
    }
}
