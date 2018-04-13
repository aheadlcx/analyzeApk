package com.umeng.commonsdk.framework;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.umeng.commonsdk.statistics.common.e;

class k extends Handler {
    final /* synthetic */ g a;

    k(g gVar, Looper looper) {
        this.a = gVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 273:
                e.b("--->>> handleMessage: recv MSG_PROCESS_NEXT msg.");
                g.j();
                return;
            case 512:
                g.i();
                return;
            default:
                return;
        }
    }
}
