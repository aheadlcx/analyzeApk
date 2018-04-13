package cn.v6.sixrooms.ui.fragment;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final class c implements OnFocusChangeListener {
    final /* synthetic */ BundlePhoneFragment a;

    c(BundlePhoneFragment bundlePhoneFragment) {
        this.a = bundlePhoneFragment;
    }

    public final void onFocusChange(View view, boolean z) {
        if (!z || BundlePhoneFragment.f(this.a).getText().toString().trim().length() <= 0) {
            BundlePhoneFragment.e(this.a).hideCleanTag();
        } else {
            BundlePhoneFragment.e(this.a).showCleanTag();
        }
    }
}
