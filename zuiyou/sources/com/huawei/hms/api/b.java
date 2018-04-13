package com.huawei.hms.api;

import android.os.Handler.Callback;
import android.os.Message;
import com.huawei.hms.support.log.a;

class b implements Callback {
    final /* synthetic */ HuaweiApiClientImpl a;

    b(HuaweiApiClientImpl huaweiApiClientImpl) {
        this.a = huaweiApiClientImpl;
    }

    public boolean handleMessage(Message message) {
        if (message == null || message.what != 2) {
            return false;
        }
        a.d("HuaweiApiClientImpl", "In connect, bind core service time out");
        if (this.a.f.get() != 5) {
            return true;
        }
        this.a.a(1);
        if (this.a.l == null) {
            return true;
        }
        this.a.l.onConnectionFailed(new ConnectionResult(6));
        return true;
    }
}
