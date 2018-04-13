package qsbk.app.live.widget;

import android.graphics.Color;
import android.os.CountDownTimer;

class am extends CountDownTimer {
    final /* synthetic */ FanfanleGameView a;

    am(FanfanleGameView fanfanleGameView, long j, long j2) {
        this.a = fanfanleGameView;
        super(j, j2);
    }

    public void onTick(long j) {
        this.a.k.setVisibility(0);
        if (j / 1000 < 10) {
            this.a.k.setText("0" + String.valueOf(j / 1000));
            if ((j / 1000) % 2 == 0) {
                this.a.k.setTextColor(Color.parseColor("#FF4848"));
                return;
            } else {
                this.a.k.setTextColor(Color.parseColor("#437504"));
                return;
            }
        }
        if (j / 1000 == 10) {
            this.a.k.setTextColor(Color.parseColor("#FF4848"));
        } else {
            this.a.k.setTextColor(Color.parseColor("#437504"));
        }
        this.a.k.setText(String.valueOf(j / 1000));
    }

    public void onFinish() {
    }
}
