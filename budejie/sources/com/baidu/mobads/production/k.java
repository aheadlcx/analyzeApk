package com.baidu.mobads.production;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.utils.l;

class k extends Handler {
    final /* synthetic */ IXAdInstanceInfo a;
    final /* synthetic */ a b;

    k(a aVar, Looper looper, IXAdInstanceInfo iXAdInstanceInfo) {
        this.b = aVar;
        this.a = iXAdInstanceInfo;
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                try {
                    this.b.a(message, this.a);
                    return;
                } catch (Throwable e) {
                    l.a().e(e);
                    return;
                }
            default:
                return;
        }
    }
}
