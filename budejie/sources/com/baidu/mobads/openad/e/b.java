package com.baidu.mobads.openad.e;

import java.util.TimerTask;

class b extends TimerTask {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void run() {
        if (this.a.h.get() == 0) {
            if (this.a.b != null) {
                this.a.f = this.a.d - this.a.e;
                this.a.b.onTimer(this.a.f);
            }
            if (this.a.e > 0) {
                this.a.e = this.a.e - 1;
                return;
            }
            this.a.stop();
            if (this.a.b != null) {
                this.a.b.onTimerComplete();
            }
        }
    }
}
