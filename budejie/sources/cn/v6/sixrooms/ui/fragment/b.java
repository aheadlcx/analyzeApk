package cn.v6.sixrooms.ui.fragment;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final class b implements OnFocusChangeListener {
    final /* synthetic */ BundlePhoneFragment a;

    b(BundlePhoneFragment bundlePhoneFragment) {
        this.a = bundlePhoneFragment;
    }

    public final void onFocusChange(View view, boolean z) {
        if (!z || BundlePhoneFragment.g(this.a).getText().toString().trim().length() <= 0) {
            BundlePhoneFragment.i(this.a);
        } else {
            BundlePhoneFragment.j(this.a);
        }
    }
}
