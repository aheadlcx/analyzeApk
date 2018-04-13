package com.huawei.hms.api.internal;

import android.os.Bundle;
import com.huawei.hms.api.HuaweiApiClientImpl;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.RequestHeader;
import com.huawei.hms.core.aidl.b;
import com.huawei.hms.core.aidl.d;
import com.huawei.hms.core.aidl.f;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.entity.core.CommonCode.ErrorCode;
import com.huawei.hms.support.api.transport.DatagramTransport;
import com.huawei.hms.support.api.transport.DatagramTransport.a;

public class IPCTransport implements DatagramTransport {
    private final String a;
    private final IMessageEntity b;
    private final Class<? extends IMessageEntity> c;

    public IPCTransport(String str, IMessageEntity iMessageEntity, Class<? extends IMessageEntity> cls) {
        this.a = str;
        this.b = iMessageEntity;
        this.c = cls;
    }

    public final void a(ApiClient apiClient, a aVar) {
        int a = a(apiClient, new f(this.c, aVar));
        if (a != 0) {
            aVar.a(a, null);
        }
    }

    public final void b(ApiClient apiClient, a aVar) {
        a(apiClient, aVar);
    }

    private int a(ApiClient apiClient, d dVar) {
        b bVar = new b(this.a, g.a().b());
        f a = com.huawei.hms.core.aidl.a.a(bVar.c());
        bVar.a(a.a(this.b, new Bundle()));
        IMessageEntity requestHeader = new RequestHeader();
        requestHeader.setAppID(apiClient.getAppID());
        requestHeader.setPackageName(apiClient.getPackageName());
        requestHeader.setSdkVersion(20502300);
        if (apiClient instanceof HuaweiApiClientImpl) {
            requestHeader.setSessionId(apiClient.getSessionId());
        }
        bVar.b = a.a(requestHeader, new Bundle());
        try {
            ((HuaweiApiClientImpl) apiClient).getService().a(bVar, dVar);
            return 0;
        } catch (Exception e) {
            return ErrorCode.INTERNAL_ERROR;
        }
    }
}
