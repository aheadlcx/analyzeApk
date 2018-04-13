package qsbk.app.live.widget;

import android.os.CountDownTimer;
import java.util.Locale;

class q extends CountDownTimer {
    final /* synthetic */ DollCatchView a;

    q(DollCatchView dollCatchView, long j, long j2) {
        this.a = dollCatchView;
        super(j, j2);
    }

    public void onTick(long j) {
        this.a.s.setText(String.format(Locale.getDefault(), "(%ds)", new Object[]{Long.valueOf(j / 1000)}));
    }

    public void onFinish() {
        this.a.s.setVisibility(8);
        this.a.setDollCatching(false);
        this.a.a.showDollCatchingTips();
    }
}
