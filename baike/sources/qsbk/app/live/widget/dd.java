package qsbk.app.live.widget;

import android.os.CountDownTimer;
import qsbk.app.live.R;

class dd extends CountDownTimer {
    final /* synthetic */ GameView a;

    dd(GameView gameView, long j, long j2) {
        this.a = gameView;
        super(j, j2);
    }

    public void onTick(long j) {
        this.a.k.setVisibility(0);
        this.a.k.setText(String.valueOf(j / 1000));
    }

    public void onFinish() {
        this.a.setBetEnable(false);
        this.a.j();
        this.a.a(R.string.live_game_result, false);
    }
}
