package cn.v6.sixrooms.ui.fragment;

import android.text.Editable;
import android.text.TextWatcher;

final class l implements TextWatcher {
    final /* synthetic */ BundlePhoneFragment a;

    l(BundlePhoneFragment bundlePhoneFragment) {
        this.a = bundlePhoneFragment;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.a.cleanPasswordView(charSequence.length() > 0);
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
    }
}
