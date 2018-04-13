package cn.v6.sixrooms.widgets.phone;

import android.os.CountDownTimer;

final class r extends CountDownTimer {
    final /* synthetic */ GetVerificationCodeView a;

    r(GetVerificationCodeView getVerificationCodeView) {
        this.a = getVerificationCodeView;
        super(60000, 1000);
    }

    public final void onTick(long j) {
        GetVerificationCodeView.a(this.a).setText(String.valueOf(j / 1000) + "s");
    }

    public final void onFinish() {
        GetVerificationCodeView.b(this.a);
        GetVerificationCodeView.c(this.a);
    }
}
