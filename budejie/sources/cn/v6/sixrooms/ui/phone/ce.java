package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.widgets.phone.HideOrDisplayThePasswordView.OnHideOrDisplayListener;

final class ce implements OnHideOrDisplayListener {
    final /* synthetic */ ResetPasswordActivity a;

    ce(ResetPasswordActivity resetPasswordActivity) {
        this.a = resetPasswordActivity;
    }

    public final void clickCleanButton() {
        this.a.clearPassword();
    }

    public final void isShowPassword(boolean z) {
        this.a.setPasswordType(z);
        this.a.g.requestFocus();
        this.a.hideCleanConfirmPwdView();
    }
}
