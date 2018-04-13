package qsbk.app.activity;

import qsbk.app.R;
import qsbk.app.widget.CaptchaButton;
import qsbk.app.widget.CaptchaButton.OnTickListener;

class abc implements OnTickListener {
    final /* synthetic */ ResetPwdActivity a;

    abc(ResetPwdActivity resetPwdActivity) {
        this.a = resetPwdActivity;
    }

    public void onTick(CaptchaButton captchaButton, long j) {
        captchaButton.setText((j / 1000) + "秒");
    }

    public void onFinish(CaptchaButton captchaButton) {
        captchaButton.setEnabled(true);
        captchaButton.setText(this.a.getString(R.string.get_verify_code));
    }
}
