package com.baidu.mobstat;

import android.content.Context;

class bz implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ by b;

    bz(by byVar, Context context) {
        this.b = byVar;
        this.a = context;
    }

    public void run() {
        if (this.b.f != null) {
            this.b.f.cancel();
            this.b.f = null;
        }
        this.b.e = SendStrategyEnum.values()[bj.a().b(this.a)];
        this.b.d = bj.a().c(this.a);
        this.b.b = bj.a().d(this.a);
        if (this.b.e.equals(SendStrategyEnum.SET_TIME_INTERVAL)) {
            this.b.b(this.a);
        } else if (this.b.e.equals(SendStrategyEnum.ONCE_A_DAY)) {
            this.b.b(this.a);
        }
        this.b.g.postDelayed(new ca(this), (long) (this.b.c * 1000));
    }
}
