package cn.v6.sixrooms.ui.fragment;

import cn.v6.sixrooms.widgets.phone.HideOrDisplayThePasswordView.OnHideOrDisplayListener;

final class g implements OnHideOrDisplayListener {
    final /* synthetic */ BundlePhoneFragment a;

    g(BundlePhoneFragment bundlePhoneFragment) {
        this.a = bundlePhoneFragment;
    }

    public final void clickCleanButton() {
        this.a.clearPassword();
        BundlePhoneFragment.e(this.a).hideCleanTag();
    }

    public final void isShowPassword(boolean z) {
        this.a.setPasswordType(z);
        BundlePhoneFragment.f(this.a).requestFocus();
    }
}
