package qsbk.app.activity;

import qsbk.app.R;
import qsbk.app.widget.CaptchaButton;
import qsbk.app.widget.CaptchaButton.OnTickListener;

class dp implements OnTickListener {
    final /* synthetic */ BindPhoneActivity a;

    dp(BindPhoneActivity bindPhoneActivity) {
        this.a = bindPhoneActivity;
    }

    public void onTick(CaptchaButton captchaButton, long j) {
        captchaButton.setText((j / 1000) + "秒");
    }

    public void onFinish(CaptchaButton captchaButton) {
        captchaButton.setEnabled(true);
        captchaButton.setText(this.a.getString(R.string.get_verify_code));
    }
}
