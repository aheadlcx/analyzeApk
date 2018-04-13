package com.tencent.open.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class h extends Handler {
    final /* synthetic */ g a;

    h(g gVar, Looper looper) {
        this.a = gVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1000:
                this.a.b();
                break;
            case 1001:
                this.a.e();
                break;
        }
        super.handleMessage(message);
    }
}
