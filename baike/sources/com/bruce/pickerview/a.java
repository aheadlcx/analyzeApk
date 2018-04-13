package com.bruce.pickerview;

import android.os.Handler.Callback;
import android.os.Message;

class a implements Callback {
    final /* synthetic */ LoopView a;

    a(LoopView loopView) {
        this.a = loopView;
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1000) {
            this.a.invalidate();
        }
        if (message.what == 2000) {
            this.a.f();
        } else if (message.what == 3000) {
        }
        this.a.d();
        return false;
    }
}
