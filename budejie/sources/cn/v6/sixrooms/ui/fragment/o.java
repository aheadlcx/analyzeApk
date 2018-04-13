package cn.v6.sixrooms.ui.fragment;

import cn.v6.sixrooms.widgets.phone.HideOrDisplayThePasswordView.OnHideOrDisplayListener;

final class o implements OnHideOrDisplayListener {
    final /* synthetic */ LoginFragment a;

    o(LoginFragment loginFragment) {
        this.a = loginFragment;
    }

    public final void clickCleanButton() {
        this.a.clearPassword();
    }

    public final void isShowPassword(boolean z) {
        this.a.setPasswordType(z);
        LoginFragment.g(this.a).clearFocus();
        LoginFragment.f(this.a).requestFocus();
    }
}
