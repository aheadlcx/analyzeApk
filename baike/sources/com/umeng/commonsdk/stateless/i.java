package com.umeng.commonsdk.stateless;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class i extends Handler {
    final /* synthetic */ d a;

    i(d dVar, Looper looper) {
        this.a = dVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 273:
                d.e();
                return;
            case 512:
                d.f();
                return;
            default:
                return;
        }
    }
}
