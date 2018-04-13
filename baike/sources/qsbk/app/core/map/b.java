package qsbk.app.core.map;

import java.util.TimerTask;

class b extends TimerTask {
    final /* synthetic */ BDLocationManager a;

    b(BDLocationManager bDLocationManager) {
        this.a = bDLocationManager;
    }

    public void run() {
        this.a.f.post(new c(this));
    }
}
