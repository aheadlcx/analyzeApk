package com.huawei.hms.api.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.ResponseHeader;
import com.huawei.hms.core.aidl.b;
import com.huawei.hms.core.aidl.d.a;
import com.huawei.hms.support.api.transport.DatagramTransport;

public class f extends a {
    private final Class<? extends IMessageEntity> a;
    private final DatagramTransport.a b;

    public f(Class<? extends IMessageEntity> cls, DatagramTransport.a aVar) {
        this.a = cls;
        this.b = aVar;
    }

    public void a(b bVar) throws RemoteException {
        if (bVar == null || TextUtils.isEmpty(bVar.a)) {
            com.huawei.hms.support.log.a.d("IPCCallback", "In call, URI cannot be empty.");
            throw new RemoteException();
        }
        com.huawei.hms.core.aidl.f a = com.huawei.hms.core.aidl.a.a(bVar.c());
        IMessageEntity responseHeader = new ResponseHeader();
        a.a(bVar.b, responseHeader);
        IMessageEntity iMessageEntity = null;
        if (bVar.b() > 0) {
            iMessageEntity = a();
            if (iMessageEntity != null) {
                a.a(bVar.a(), iMessageEntity);
            }
        }
        this.b.a(responseHeader.getStatusCode(), iMessageEntity);
    }

    protected IMessageEntity a() {
        ReflectiveOperationException e;
        if (this.a != null) {
            try {
                return (IMessageEntity) this.a.newInstance();
            } catch (IllegalAccessException e2) {
                e = e2;
            } catch (InstantiationException e3) {
                e = e3;
            }
        }
        return null;
        com.huawei.hms.support.log.a.d("IPCCallback", "In newResponseInstance, instancing exception." + e.getMessage());
        return null;
    }
}
