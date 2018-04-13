package cn.v6.sixrooms.ui.fragment;

import android.view.View;
import android.view.View.OnClickListener;

final class i implements OnClickListener {
    final /* synthetic */ BundlePhoneFragment a;

    i(BundlePhoneFragment bundlePhoneFragment) {
        this.a = bundlePhoneFragment;
    }

    public final void onClick(View view) {
        BundlePhoneFragment.g(this.a).requestFocus();
        BundlePhoneFragment.a(this.a).showSoftInput(BundlePhoneFragment.g(this.a), 0);
    }
}
