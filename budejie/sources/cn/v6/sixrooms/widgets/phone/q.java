package cn.v6.sixrooms.widgets.phone;

final class q implements GetVerificationCodeView$RunCountdownCallback {
    final /* synthetic */ GetVerificationCodeView a;

    q(GetVerificationCodeView getVerificationCodeView) {
        this.a = getVerificationCodeView;
    }

    public final void startRunCountdown() {
        this.a.runCountdown();
    }
}
