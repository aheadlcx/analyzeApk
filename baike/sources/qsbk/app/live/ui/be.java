package qsbk.app.live.ui;

import android.os.CountDownTimer;
import qsbk.app.core.utils.TimeUtils;

class be extends CountDownTimer {
    final /* synthetic */ LiveBaseActivity a;

    be(LiveBaseActivity liveBaseActivity, long j, long j2) {
        this.a = liveBaseActivity;
        super(j, j2);
    }

    public void onTick(long j) {
        this.a.bp.setText("距开始还有 " + TimeUtils.secToHour(j));
    }

    public void onFinish() {
        this.a.br.setVisibility(8);
    }
}
