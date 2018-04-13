package cn.v6.sixrooms.ui.fragment;

import android.view.View;
import android.view.View.OnClickListener;

final class e implements OnClickListener {
    final /* synthetic */ BundlePhoneFragment a;

    e(BundlePhoneFragment bundlePhoneFragment) {
        this.a = bundlePhoneFragment;
    }

    public final void onClick(View view) {
        BundlePhoneFragment.b(this.a).showMenu();
    }
}
