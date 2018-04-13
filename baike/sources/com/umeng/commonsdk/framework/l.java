package com.umeng.commonsdk.framework;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class l extends Handler {
    l(Looper looper) {
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 768:
                e.b(message);
                return;
            case 769:
                e.d();
                return;
            case 770:
                e.g();
                return;
            default:
                return;
        }
    }
}
