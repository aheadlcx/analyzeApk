package com.xiaomi.channel.commonutils.misc;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.channel.commonutils.misc.h.b;

class i extends Handler {
    final /* synthetic */ h a;

    i(h hVar, Looper looper) {
        this.a = hVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        b bVar = (b) message.obj;
        if (message.what == 0) {
            bVar.a();
        } else if (message.what == 1) {
            bVar.c();
        }
        super.handleMessage(message);
    }
}
