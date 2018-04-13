package cn.v6.sixrooms.ui.fragment;

import android.text.Editable;
import android.text.TextWatcher;

final class k implements TextWatcher {
    final /* synthetic */ BundlePhoneFragment a;

    k(BundlePhoneFragment bundlePhoneFragment) {
        this.a = bundlePhoneFragment;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence.length() > 0) {
            BundlePhoneFragment.j(this.a);
        } else {
            BundlePhoneFragment.i(this.a);
        }
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
    }
}
