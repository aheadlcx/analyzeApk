package qsbk.app.widget;

import android.os.CountDownTimer;

class an extends CountDownTimer {
    final /* synthetic */ CaptchaButton a;

    an(CaptchaButton captchaButton, long j, long j2) {
        this.a = captchaButton;
        super(j, j2);
    }

    public void onTick(long j) {
        if (this.a.b != null) {
            this.a.b.onTick(this.a, j);
        }
    }

    public void onFinish() {
        this.a.c = false;
        if (this.a.b != null) {
            this.a.b.onFinish(this.a);
        }
    }
}
