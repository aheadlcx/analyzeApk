package qsbk.app.live.widget;

import android.os.CountDownTimer;

class il extends CountDownTimer {
    final /* synthetic */ RollTableView a;

    il(RollTableView rollTableView, long j, long j2) {
        this.a = rollTableView;
        super(j, j2);
    }

    public void onTick(long j) {
        this.a.h.setVisibility(0);
        this.a.h.setText(String.valueOf(j / 1000) + "s");
    }

    public void onFinish() {
    }
}
