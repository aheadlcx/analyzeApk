package cn.v6.sixrooms.room.view;

import android.os.CountDownTimer;
import android.widget.TextView;
import cn.v6.sixrooms.utils.DateUtil;

final class g extends CountDownTimer {
    final /* synthetic */ int a;
    final /* synthetic */ TextView b;
    final /* synthetic */ PigPkDuckView c;

    g(PigPkDuckView pigPkDuckView, long j, int i, TextView textView) {
        this.c = pigPkDuckView;
        this.a = i;
        this.b = textView;
        super(j, 1000);
    }

    public final void onTick(long j) {
        switch (this.a) {
            case 0:
                this.b.setText(DateUtil.getMinuteFromMillisecond(j));
                return;
            default:
                return;
        }
    }

    public final void onFinish() {
        switch (this.a) {
            case 0:
                this.b.setText("00:00");
                return;
            case 1:
                if (this.c.k != null) {
                    this.c.k.cancel();
                }
                this.c.i.onGameOver();
                return;
            default:
                return;
        }
    }
}
