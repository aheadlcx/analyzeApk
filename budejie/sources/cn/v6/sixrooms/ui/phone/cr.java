package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.widgets.phone.GetVerificationCodeView$RunCountdownCallback;
import cn.v6.sixrooms.widgets.phone.GetVerificationCodeView.GetVerificationCodeListener;

final class cr implements GetVerificationCodeListener {
    final /* synthetic */ RetrieveNameOrPasswordActivity a;

    cr(RetrieveNameOrPasswordActivity retrieveNameOrPasswordActivity) {
        this.a = retrieveNameOrPasswordActivity;
    }

    public final void clickGetVerificationCodeCallback(GetVerificationCodeView$RunCountdownCallback getVerificationCodeView$RunCountdownCallback) {
        if (this.a.isRetrieveName()) {
            if (!this.a.a()) {
                return;
            }
        } else if (!(this.a.a() && RetrieveNameOrPasswordActivity.e(this.a))) {
            return;
        }
        this.a.j.getVerificationCode();
        if (getVerificationCodeView$RunCountdownCallback != null) {
            getVerificationCodeView$RunCountdownCallback.startRunCountdown();
        }
    }
}
