package com.tencent.connect.share;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.open.utils.c;

final class g extends Handler {
    final /* synthetic */ c a;

    g(Looper looper, c cVar) {
        this.a = cVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 101:
                this.a.a(0, (String) message.obj);
                return;
            case 102:
                this.a.a(message.arg1, null);
                return;
            default:
                super.handleMessage(message);
                return;
        }
    }
}
