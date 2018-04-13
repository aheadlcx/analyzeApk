package qsbk.app.activity;

import qsbk.app.R;
import qsbk.app.widget.CaptchaButton;
import qsbk.app.widget.CaptchaButton.OnTickListener;

class zs implements OnTickListener {
    final /* synthetic */ PayPwdResetActivity a;

    zs(PayPwdResetActivity payPwdResetActivity) {
        this.a = payPwdResetActivity;
    }

    public void onTick(CaptchaButton captchaButton, long j) {
        captchaButton.setText((j / 1000) + "秒");
    }

    public void onFinish(CaptchaButton captchaButton) {
        captchaButton.setEnabled(true);
        captchaButton.setText(this.a.getString(R.string.get_verify_code));
    }
}
