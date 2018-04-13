package qsbk.app.live.widget;

import android.os.CountDownTimer;
import qsbk.app.live.R;

class fa extends CountDownTimer {
    final /* synthetic */ GuessBigOrSmallGameView a;

    fa(GuessBigOrSmallGameView guessBigOrSmallGameView, long j, long j2) {
        this.a = guessBigOrSmallGameView;
        super(j, j2);
    }

    public void onTick(long j) {
        this.a.M.setVisibility(0);
        this.a.M.setProgress((int) (((long) this.a.M.getMax()) - (j / 1000)));
    }

    public void onFinish() {
        this.a.M.setProgress(this.a.M.getMax());
        this.a.a(R.string.live_game_result, true);
    }
}
