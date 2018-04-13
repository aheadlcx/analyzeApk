package qsbk.app.utils.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerHelper {
    private ITimerProcessor a;
    private long b;
    private long c;
    private Timer d;
    private TimerTask e;
    private boolean f = false;

    public TimerHelper(ITimerProcessor iTimerProcessor, long j) {
        this.a = iTimerProcessor;
        this.b = j;
    }

    public TimerHelper(ITimerProcessor iTimerProcessor, long j, long j2) {
        this.a = iTimerProcessor;
        this.b = j;
        this.c = j2;
    }

    public synchronized void startTimer() {
        stopTimer();
        this.d = new Timer(true);
        this.e = new a(this);
        if (this.c > 0) {
            this.d.schedule(this.e, this.b, this.c);
        } else {
            this.d.schedule(this.e, this.b);
        }
        this.f = true;
    }

    public synchronized void stopTimer() {
        if (this.d != null) {
            this.d.cancel();
            this.d = null;
        }
        if (this.e != null) {
            this.e.cancel();
            this.e = null;
        }
        this.f = false;
    }

    public synchronized boolean isRunning() {
        return this.f;
    }
}
