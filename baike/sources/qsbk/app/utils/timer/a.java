package qsbk.app.utils.timer;

import java.util.TimerTask;

class a extends TimerTask {
    final /* synthetic */ TimerHelper a;

    a(TimerHelper timerHelper) {
        this.a = timerHelper;
    }

    public void run() {
        if (this.a.a != null && this.a.isRunning()) {
            this.a.a.process();
        }
    }
}
