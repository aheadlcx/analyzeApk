package com.budejie.www.activity.detail;

import android.os.Handler;
import android.os.Message;
import java.io.File;

class c$13 extends Handler {
    final /* synthetic */ c a;

    c$13(c cVar) {
        this.a = cVar;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.a.b();
                c.ag(this.a).setVisibility(0);
                return;
            case 3:
                c.a(this.a, (File) message.obj);
                c.c(this.a, message.arg1);
                c.ah(this.a);
                return;
            case 4:
                c.a(this.a, c$a.NORMAL);
                this.a.c();
                return;
            default:
                return;
        }
    }
}
