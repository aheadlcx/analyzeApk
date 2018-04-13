package com.xiaomi.metoknlp.devicediscover;

import android.os.Handler;
import android.os.Message;
import java.util.HashMap;

class b extends Handler {
    final /* synthetic */ n a;

    b(n nVar) {
        this.a = nVar;
    }

    public void handleMessage(Message message) {
        synchronized (this.a.b) {
            switch (message.what) {
                case 0:
                    this.a.a((HashMap) message.obj);
                    break;
                case 1:
                    this.a.d = 0;
                    if (this.a.g != null) {
                        this.a.g.cancel(true);
                    }
                    this.a.c();
                    break;
                case 3:
                    if (message.obj != null) {
                        String str = (String) message.obj;
                        if (this.a.e != null) {
                            this.a.e.d(str);
                        }
                    }
                    this.a.c();
                    break;
            }
        }
    }
}
