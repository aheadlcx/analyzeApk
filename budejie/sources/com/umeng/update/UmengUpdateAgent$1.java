package com.umeng.update;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class UmengUpdateAgent$1 extends Handler {
    UmengUpdateAgent$1(Looper looper) {
        super(looper);
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 0 && UpdateConfig.isUpdateAutoPopup()) {
            UmengUpdateAgent.a(UmengUpdateAgent.a(), (UpdateResponse) message.obj, UpdateConfig.getStyle());
        }
        UmengUpdateAgent.a(null);
        if (UmengUpdateAgent.b() != null) {
            UmengUpdateAgent.b().onUpdateReturned(message.what, (UpdateResponse) message.obj);
        }
    }
}
