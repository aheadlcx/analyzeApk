package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.widgets.phone.GetVerificationCodeView$RunCountdownCallback;
import cn.v6.sixrooms.widgets.phone.GetVerificationCodeView.GetVerificationCodeListener;

final class bo implements GetVerificationCodeListener {
    final /* synthetic */ RegisterActivity a;

    bo(RegisterActivity registerActivity) {
        this.a = registerActivity;
    }

    public final void clickGetVerificationCodeCallback(GetVerificationCodeView$RunCountdownCallback getVerificationCodeView$RunCountdownCallback) {
        if (this.a.f()) {
            this.a.f.getGtParams(this.a.d(), getVerificationCodeView$RunCountdownCallback);
        }
    }
}
