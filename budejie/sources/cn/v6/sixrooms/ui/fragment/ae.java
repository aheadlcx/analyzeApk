package cn.v6.sixrooms.ui.fragment;

import cn.v6.sixrooms.widgets.phone.GetVerificationCodeView$RunCountdownCallback;
import cn.v6.sixrooms.widgets.phone.GetVerificationCodeView.GetVerificationCodeListener;

final class ae implements GetVerificationCodeListener {
    final /* synthetic */ MsgVerifyFragment a;

    ae(MsgVerifyFragment msgVerifyFragment) {
        this.a = msgVerifyFragment;
    }

    public final void clickGetVerificationCodeCallback(GetVerificationCodeView$RunCountdownCallback getVerificationCodeView$RunCountdownCallback) {
        MsgVerifyFragment.k(this.a);
        if (getVerificationCodeView$RunCountdownCallback != null) {
            getVerificationCodeView$RunCountdownCallback.startRunCountdown();
        }
    }
}
