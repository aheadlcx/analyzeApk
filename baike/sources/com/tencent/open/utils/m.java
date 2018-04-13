package com.tencent.open.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.open.a.f;

class m extends Handler {
    final /* synthetic */ b a;

    m(b bVar, Looper looper) {
        this.a = bVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        f.a("AsynLoadImg", "handleMessage:" + message.arg1);
        if (message.arg1 == 0) {
            this.a.b.a(message.arg1, (String) message.obj);
        } else {
            this.a.b.a(message.arg1, null);
        }
    }
}
