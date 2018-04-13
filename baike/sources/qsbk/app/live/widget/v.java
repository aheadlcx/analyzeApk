package qsbk.app.live.widget;

import android.os.CountDownTimer;
import java.util.Locale;

class v extends CountDownTimer {
    final /* synthetic */ DollResultDialog a;

    v(DollResultDialog dollResultDialog, long j, long j2) {
        this.a = dollResultDialog;
        super(j, j2);
    }

    public void onTick(long j) {
        this.a.j.setText(String.format(Locale.getDefault(), "(%ds)", new Object[]{Long.valueOf(j / 1000)}));
    }

    public void onFinish() {
        this.a.dismiss();
    }
}
