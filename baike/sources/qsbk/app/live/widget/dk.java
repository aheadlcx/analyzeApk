package qsbk.app.live.widget;

import android.os.CountDownTimer;

class dk extends CountDownTimer {
    final /* synthetic */ GameWinDialog a;

    dk(GameWinDialog gameWinDialog, long j, long j2) {
        this.a = gameWinDialog;
        super(j, j2);
    }

    public void onTick(long j) {
        if (this.a.t != null) {
            this.a.t.setText(String.format("(%ds)", new Object[]{Long.valueOf((j / 1000) + 1)}));
        }
        if (this.a.u != null) {
            this.a.u.setText(String.format("(%ds)", new Object[]{Long.valueOf((j / 1000) + 1)}));
        }
    }

    public void onFinish() {
        this.a.dismiss();
    }
}
