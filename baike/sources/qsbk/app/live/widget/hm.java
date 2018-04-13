package qsbk.app.live.widget;

import android.os.Handler;
import android.os.Message;

class hm extends Handler {
    final /* synthetic */ LiveRocketBackground a;

    hm(LiveRocketBackground liveRocketBackground) {
        this.a = liveRocketBackground;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        this.a.f.removeMessages(1);
        switch (message.what) {
            case 1:
                this.a.setVisibility(0);
                this.a.d = this.a.d + this.a.j;
                if (this.a.d > this.a.h) {
                    this.a.d = -this.a.h;
                }
                this.a.e = this.a.d + this.a.h;
                if (this.a.e > this.a.h) {
                    this.a.e = this.a.d - this.a.h;
                }
                if (this.a.getVisibility() == 0) {
                    this.a.f.sendEmptyMessageDelayed(1, 25);
                    this.a.postInvalidate();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
