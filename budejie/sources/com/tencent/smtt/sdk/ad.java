package com.tencent.smtt.sdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class ad extends Handler {
    final /* synthetic */ ac a;

    ad(ac acVar, Looper looper) {
        this.a = acVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        if (message.what == 150) {
            this.a.n();
        }
    }
}
