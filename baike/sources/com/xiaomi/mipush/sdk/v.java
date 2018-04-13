package com.xiaomi.mipush.sdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class v extends Handler {
    final /* synthetic */ u a;

    v(u uVar, Looper looper) {
        this.a = uVar;
        super(looper);
    }

    public void dispatchMessage(Message message) {
        String str = (String) message.obj;
        int i = message.arg1;
        synchronized (p.class) {
            if (p.a(this.a.c).e(str)) {
                if (p.a(this.a.c).c(str) < 10) {
                    if (1 == i && "disable_syncing".equals(p.a(this.a.c).a())) {
                        this.a.a(str, true);
                    } else if (i == 0 && "enable_syncing".equals(p.a(this.a.c).a())) {
                        this.a.a(str, false);
                    }
                    p.a(this.a.c).b(str);
                } else {
                    p.a(this.a.c).d(str);
                }
            }
        }
    }
}
