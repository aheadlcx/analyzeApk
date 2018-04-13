package com.huawei.hms.api.internal;

import android.os.Handler.Callback;
import android.os.Message;
import com.huawei.hms.support.log.a;

class b implements Callback {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public boolean handleMessage(Message message) {
        if (message == null || message.what != 2) {
            return false;
        }
        a.d("BindingFailedResolution", "In connect, bind core try timeout");
        this.a.b(false);
        return true;
    }
}
