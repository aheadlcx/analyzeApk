package qsbk.app.live.ui;

import android.os.CountDownTimer;

class dq extends CountDownTimer {
    final /* synthetic */ LivePullActivity a;

    dq(LivePullActivity livePullActivity, long j, long j2) {
        this.a = livePullActivity;
        super(j, j2);
    }

    public void onTick(long j) {
        this.a.bG.setPercent(((float) j) / 3000.0f);
    }

    public void onFinish() {
        this.a.az();
    }
}
