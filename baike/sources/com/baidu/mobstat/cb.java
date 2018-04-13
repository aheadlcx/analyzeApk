package com.baidu.mobstat;

import android.content.Context;
import java.util.TimerTask;

class cb extends TimerTask {
    final /* synthetic */ Context a;
    final /* synthetic */ by b;

    cb(by byVar, Context context) {
        this.b = byVar;
        this.a = context;
    }

    public void run() {
        if (!DataCore.instance().isPartEmpty()) {
            this.b.c(this.a);
        }
    }
}
