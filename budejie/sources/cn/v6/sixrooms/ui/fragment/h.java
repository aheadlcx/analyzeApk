package cn.v6.sixrooms.ui.fragment;

import android.view.View;
import android.view.View.OnClickListener;

final class h implements OnClickListener {
    final /* synthetic */ BundlePhoneFragment a;

    h(BundlePhoneFragment bundlePhoneFragment) {
        this.a = bundlePhoneFragment;
    }

    public final void onClick(View view) {
        BundlePhoneFragment.f(this.a).requestFocus();
        BundlePhoneFragment.a(this.a).showSoftInput(BundlePhoneFragment.f(this.a), 0);
    }
}
