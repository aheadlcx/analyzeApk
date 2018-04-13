package cn.v6.sixrooms.ui.fragment;

import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class d implements DialogListener {
    final /* synthetic */ BundlePhoneFragment a;

    d(BundlePhoneFragment bundlePhoneFragment) {
        this.a = bundlePhoneFragment;
    }

    public final void positive(int i) {
        BundlePhoneFragment.k(this.a).getBundleVerifyCode();
    }

    public final void negative(int i) {
    }
}
