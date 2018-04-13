package com.elves.update;

import android.os.Handler;
import android.os.Message;

class b$1 extends Handler {
    final /* synthetic */ c a;

    b$1(c cVar) {
        this.a = cVar;
    }

    public void handleMessage(Message message) {
        this.a.a((String) message.obj);
    }
}
