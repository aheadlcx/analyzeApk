package antistatic.spinnerwheel;

import android.os.Handler;
import android.os.Message;

class e extends Handler {
    final /* synthetic */ WheelScroller a;

    e(WheelScroller wheelScroller) {
        this.a = wheelScroller;
    }

    public void handleMessage(Message message) {
        this.a.a.computeScrollOffset();
        int a = this.a.a();
        int a2 = this.a.g - a;
        this.a.g = a;
        if (a2 != 0) {
            this.a.d.onScroll(a2);
        }
        if (Math.abs(a - this.a.b()) < 1) {
            this.a.a.forceFinished(true);
        }
        if (!this.a.a.isFinished()) {
            this.a.j.sendEmptyMessage(message.what);
        } else if (message.what == 0) {
            this.a.e();
        } else {
            this.a.c();
        }
    }
}
