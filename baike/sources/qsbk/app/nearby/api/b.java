package qsbk.app.nearby.api;

import java.util.TimerTask;

class b extends TimerTask {
    final /* synthetic */ BDLocationManger a;

    b(BDLocationManger bDLocationManger) {
        this.a = bDLocationManger;
    }

    public void run() {
        this.a.f.post(new c(this));
    }
}
