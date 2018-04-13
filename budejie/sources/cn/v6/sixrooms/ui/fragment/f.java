package cn.v6.sixrooms.ui.fragment;

import android.view.View;
import android.view.View.OnClickListener;

final class f implements OnClickListener {
    final /* synthetic */ BundlePhoneFragment a;

    f(BundlePhoneFragment bundlePhoneFragment) {
        this.a = bundlePhoneFragment;
    }

    public final void onClick(View view) {
        if (BundlePhoneFragment.c(this.a)) {
            BundlePhoneFragment.d(this.a);
        }
    }
}
