package qsbk.app.live.widget;

import android.os.CountDownTimer;
import java.util.Locale;

class gq extends CountDownTimer {
    final /* synthetic */ LevelGiftDialog a;

    gq(LevelGiftDialog levelGiftDialog, long j, long j2) {
        this.a = levelGiftDialog;
        super(j, j2);
    }

    public void onTick(long j) {
        long j2 = j / 1000;
        long j3 = j2 % 60;
        j2 /= 60;
        long j4 = j2 % 60;
        j2 /= 60;
        this.a.g.setText(String.format(Locale.getDefault(), "特惠礼包剩余时间：%d时%d分%d秒", new Object[]{Long.valueOf(j2), Long.valueOf(j4), Long.valueOf(j3)}));
    }

    public void onFinish() {
        this.a.dismiss();
    }
}
