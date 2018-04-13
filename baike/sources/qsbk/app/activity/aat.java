package qsbk.app.activity;

import qsbk.app.R;
import qsbk.app.widget.CaptchaButton;
import qsbk.app.widget.CaptchaButton.OnTickListener;

class aat implements OnTickListener {
    final /* synthetic */ ReBindPhoneActivity a;

    aat(ReBindPhoneActivity reBindPhoneActivity) {
        this.a = reBindPhoneActivity;
    }

    public void onTick(CaptchaButton captchaButton, long j) {
        captchaButton.setEnabled(false);
        captchaButton.setText((j / 1000) + "秒");
    }

    public void onFinish(CaptchaButton captchaButton) {
        captchaButton.setEnabled(true);
        captchaButton.setText(R.string.get_verify_code);
    }
}
